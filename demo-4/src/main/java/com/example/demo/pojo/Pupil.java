package com.example.demo.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Configuration
@PropertySource(value="classpath:application.yml")
@ConfigurationProperties(prefix="Pupil")
@Order(1)
public class Pupil implements Student {
	@Value("name")
	private String name;
	@Value("age")
	private String age;
	@Override
	public String study() {
		System.out.println(this.name+"===study"+"====age="+this.age);
		return this.name+"===study";
	}
}
