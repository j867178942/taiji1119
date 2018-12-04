package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.pojo.Customer;
import com.example.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJpaApplicationTests {
	@Autowired
	private UserRepository dao;

	@PersistenceContext
	EntityManager em;
	

	//查所有
	@Test
	public void contextLoads() {
		List<Customer> asdasd = dao.findAll();
		for (Customer c : asdasd) {
			System.out.println(c);
		}
	}
	//新增
	@Test
	public void contextLoads2() {
		for(int i =0;i<30;i++) {
			Customer c = new Customer();
			System.out.println(c);
			c.setLastname("test"+i);
	 		c.setAge((int)(Math.random()*100));
	 		System.out.println(c.getAge());
			dao.saveAndFlush(c);
		}
	}
	//id获取
	@Test
	public void contextLoads3() {
	System.out.println(dao.getOne(1));
	}
	
	//排序加分页
	@Test
	public void  test_findAllpage() {
		Order idOrder =new Order(Direction.ASC,"id");
		Order nameOrder = new Order(Direction.DESC,"age");
		Order[] orders = new Order[]{idOrder,nameOrder} ;
		Sort sort =new Sort(orders);
		int page = 1;
		int size = 5;
        PageRequest pageable =new PageRequest(page, size,sort);
        Page<Customer> page1 = dao.findAll(pageable);
        System.out.println(page1.getTotalElements()+"总记录数=");
	}

}
