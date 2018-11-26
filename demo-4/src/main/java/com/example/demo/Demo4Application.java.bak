package com.example.demo;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import com.example.demo.pojo.CollegeStudent;
import com.example.demo.pojo.Pupil;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;

@SpringBootApplication
@RestController
public class Demo4Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo4Application.class, args);
	}
	//学生接口，有两个实现，一个大学生一个小学生，其中有学习的方法
	
	@Autowired
	private Pupil stu;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping("/study")
    public String getName() {
		System.out.println(stu);
		return "123";
    }
	
	@Bean
	public ApplicationListener<ApplicationEvent> helloListener() {
		final String HELLO_URL = "/hello";
		return (ApplicationEvent ae) -> {
			if (ae instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent arhe = (ServletRequestHandledEvent) ae;
				if (arhe.getRequestUrl().equals(HELLO_URL)) {
					System.out.println("visit hello");
				}
			}
		};
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args ->{
			System.out.println("list bean:" + ctx.getBeanDefinitionCount());
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for(String beanName :beanNames) {
				System.out.println(beanName);
			}
		};
	}
	
}
