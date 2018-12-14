package cn.com.taiji.mongoweb.daoimpl;


import cn.com.taiji.mongoweb.dao.UserDao;
import cn.com.taiji.mongoweb.model.User;
import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import org.springframework.data.geo.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	/**
	 * 由springboot自动注入，默认配置会产生mongoTemplate这个bean
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 查找全部
	 */
	@Override
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}

	/**
	 * 根据id得到对象
	 */
	@Override
	public User getUser(Integer id) {

		// mongoTemplate.geoNear()
		
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), User.class);
	}

	/**
	 * 插入一个用户
	 */
	@Override
	public void insert(User user) {
		mongoTemplate.insert(user);
	}

	/**
	 * 根据id删除一个用户
	 */
	@Override
	public void remove(Integer id) {
		Criteria criteria = Criteria.where("id").is(id);  
        Query query = new Query(criteria);
		mongoTemplate.remove(query,User.class);
	}

	/**
	 * 分页查找
	 * 
	 * user代表过滤条件
	 * 
	 * pageable代表分页bean
	 */
	@Override
	public List<User> findByPage(User user, Pageable pageable) {
		Query query = new Query();
		if (user != null && user.getName() != null) {
			//模糊查询
			query = new Query(Criteria.where("name").regex("^" + user.getName()));
		}
		List<User> list = mongoTemplate.find(query.with(pageable), User.class);
		return list;
	}

	/**
	 * 根据id更新
	 */
	@Override
	public void update(User user) {
		Criteria criteria = Criteria.where("id").is(user.getId());
		Query query = new Query(criteria);
		Update update = Update.update("name", user.getName()).set("age", user.getAge());
		mongoTemplate.updateMulti(query, update, User.class);
	}

	/**
	 * 插入一个集合
	 */
	@Override
	public void insertAll(List<User> users) {
		mongoTemplate.insertAll(users);
	}


	@Override
	public List<DBObject> geoNear(String collection, DBObject query, Point point, int limit, long maxDistance) {
		if(query==null)
			query = new BasicDBObject();

		List<DBObject> pipeLine = new ArrayList<>();
		BasicDBObject aggregate = new BasicDBObject("$geoNear",
				new BasicDBObject("near",new BasicDBObject("type","Point").append("coordinates",new double[]{point.getX(), point.getY()}))
						.append("distanceField","dist.calculated")
						.append("query", new BasicDBObject())
						.append("num", 5)
						.append("maxDistance", maxDistance)
						.append("spherical",true)
		);
		pipeLine.add(aggregate);
		//Cursor cursor=mongoTemplate.getCollection(collection).aggregate(pipeLine, AggregationOptions.builder().build());
		Cursor cursor=mongoTemplate.getCollection(collection).aggregate(pipeLine, AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build());
		List<DBObject> list = new LinkedList<>();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	@Override
	public List<DBObject> withinPolygon(String collection, String locationField,
										List<double[]> polygon, DBObject fields, DBObject query, int limit) {
		if(query==null)
			query = new BasicDBObject();

		List<List<double[]>> polygons = new LinkedList<>();
		polygons.add(polygon);
		query.put(locationField, new BasicDBObject("$geoWithin",
				new BasicDBObject("$geometry",
						new BasicDBObject("type","Polygon")
								.append("coordinates",polygons))));
		System.out.println("withinPolygon:{}"+query.toString());
		return mongoTemplate.getCollection(collection).find(query, fields).limit(limit).toArray();
	}


}
