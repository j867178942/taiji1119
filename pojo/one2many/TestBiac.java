package com.demo.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.demo.pojo.Customer;
import com.demo.pojo.Order;

public class TestBiac {

	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	private EntityTransaction transaction = null;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("test_jpa_2");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	@After
	public void destory() {
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void testUpdate(){
		Customer c = entityManager.find(Customer.class, 8);
		c.getOrders().iterator().next().setOrderName("o-XXXX-8");
	}
	
	
	//默认是延迟加载，可以使用@one2Many 的fetch 属性，改变加载策略
	@Test
	public void testOne2ManyFind(){
		Customer c = entityManager.find(Customer.class, 8);
		System.out.println(c.getLastName());
		System.out.println(c.getOrders().size());
	}

	// 生成表结构
	@Test
	public void testOne2ManyPersist() {
		Customer c = new Customer();
		c.setAge(1);
		c.setBirth(new Date());
		c.setCreatedTime(new Date());
		c.setEamil("丁丁3@qq.com");
		c.setLastName("丁丁3");
		
		Order o1 = new Order();
		o1.setOrderName("o-aa-3");
		
		Order o2 = new Order();
		o2.setOrderName("o-aa-3");
		
		//设置关联关系
		c.getOrders().add(o1);
		c.getOrders().add(o2);
		
		//执行保存操作
		entityManager.persist(o1);
		entityManager.persist(o2);
		entityManager.persist(c);// 先有的客户，再有的订单
		
	}

	// 生成表结构
	@Test
	public void testOne2Many() {

	}

}
