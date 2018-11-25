package com.example.demo.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Configuration
@PropertySource(value="classpath:application.yml")
@ConfigurationProperties(prefix="CollegeStudent")
public class Teacher {
	@Value(value = "teacher")
	private String name;
	@Value(value = "100")
	private String age;
}
