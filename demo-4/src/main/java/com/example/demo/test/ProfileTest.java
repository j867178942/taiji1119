package com.example.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.Demo4Application;
import com.example.demo.pojo.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Demo4Application.class)
@ActiveProfiles("col")
public class ProfileTest {

	@Autowired
	private Student stu;
	
	@Test
	public void studentTest(){
		stu.study();
	}
}
