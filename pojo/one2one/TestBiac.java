package com.demo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.demo.pojo.Person;
import com.demo.pojo.Card;


public class TestBiac {

	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	private EntityTransaction transaction = null;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("test_one2one");
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
	public void testPersist(){
		Person p = new Person();
		p.setMname("jin");
		
		Card d = new Card();
		d.setDname("card");
		
		//设置关系
		d.setPerson(p);
		p.setCard(d);
		
		entityManager.persist(p);
		entityManager.persist(d);
		
		
	}
	
	
	// 生成表结构
	@Test
	public void testOne2One_Shuang() {

	}

}
