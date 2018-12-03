package com.demo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.demo.pojo.Category;
import com.demo.pojo.Item;

public class TestBiac {
	
	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager  = null;
	private EntityTransaction transaction = null;
	
	@Before
	public void init(){
		entityManagerFactory = Persistence.createEntityManagerFactory("test_jpa_2");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	@After
	public void destory(){
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	//使用维护关系的一方是获取信息，或者使用不维护关系的一方获取信息，sql 语句相同
	@Test
	public void testFind2(){
		Category c = entityManager.find(Category.class,1);
		System.out.println(c.getCategoryName());
		
		System.out.println(c.getItems().size());
	}
	
	
	//删除操作
	@Test
	public void testRemove(){
		Category c = entityManager.find(Category.class,7);
		entityManager.remove(c);
	}
	
	
	//使用维护关系的一方是获取信息，默认使用的是延迟加载的方式
	@Test
	public void testFind1(){
		Item i = entityManager.find(Item.class,1);
		System.out.println(i.getItemName());
		
		System.out.println(i.getCategories().size());
	}
	
	//不管先存谁，后存谁，都不会发出update 语句
	@Test
	public void testMany2ManyPersist2() {
		Item i1 = new Item();
		i1.setItemName("i-3");
		
		Item i2 = new Item();
		i2.setItemName("i-4");
		
		Category c1 = new Category();
		c1.setCategoryName("C-3");
		
		Category c2 = new Category();
		c2.setCategoryName("C-4");
		
		//设置对象之间的关系
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);
		
		i2.getCategories().add(c1);
		i2.getCategories().add(c2);
		
		c1.getItems().add(i1);
		c1.getItems().add(i2);
		
		c2.getItems().add(i1);
		c2.getItems().add(i2);
		
		//执行保存
		entityManager.persist(i1);
		entityManager.persist(i2);
		
		entityManager.persist(c1);
		entityManager.persist(c2);
		
	}
	
	
	@Test
	public void testMany2ManyPersist() {
		Item i1 = new Item();
		i1.setItemName("i-1");
		
		Item i2 = new Item();
		i2.setItemName("i-2");
		
		Category c1 = new Category();
		c1.setCategoryName("C-1");
		
		Category c2 = new Category();
		c2.setCategoryName("C-2");
		
		//设置对象之间的关系
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);
		
		i2.getCategories().add(c1);
		i2.getCategories().add(c2);
		
		c1.getItems().add(i1);
		c1.getItems().add(i2);
		
		c2.getItems().add(i1);
		c2.getItems().add(i2);
		
		//执行保存
		entityManager.persist(c1);
		entityManager.persist(c2);
		
		
		entityManager.persist(i1);
		entityManager.persist(i2);
		
		
	}
	
	
	//生成表结构
	@Test
	public void testMany2Many() {
		
	}

}
