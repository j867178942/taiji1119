package cn.com.taiji.mongoweb.service;


import cn.com.taiji.mongoweb.model.User;
import com.mongodb.DBObject;
import org.springframework.data.domain.Pageable;


import org.springframework.data.geo.Point;
import java.util.List;

public interface UserService {

	List<User> findAll();

	User getUser(Integer id);

	void update(User user);

	void insert(User user);
	
	void insertAll(List<User> users);

	void remove(Integer id);
	
	List<User> findByPage(User user, Pageable pageable);

	List<DBObject> geo(String collection, DBObject query, Point point, int limit, long maxDistance);

	List<DBObject> withinPolygon(String collection, String locationField,
								 List<double[]> polygon, DBObject fields, DBObject query, int limit);
}
