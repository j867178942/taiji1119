package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Utils.SpringContextUtils;
import com.example.demo.pojo.User;

@SpringBootApplication
public class Demo6Application {
	public static void main(String[] args) {
		SpringApplication.run(Demo6Application.class, args);
	    User  user =(User)	SpringContextUtils.getBean(User.class);
	    System.out.println(user);
	}
}
