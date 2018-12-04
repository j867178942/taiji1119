package com.example.demo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.pojo.Customer;

public interface Service1 {
	public List<Customer> findAll();
	public List<Customer> findById(Integer id);
	public List<Customer> getByLastname(String lastname);
    List<Long> findByFromUserId(@Param("age") int age);
    public int updateLastname(@Param("lastname") String lastname);
}
