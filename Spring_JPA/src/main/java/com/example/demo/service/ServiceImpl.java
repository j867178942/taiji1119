package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.pojo.Customer;
import com.example.demo.repository.UserRepository;

public class ServiceImpl  implements Service1  {
	@Autowired
	private UserRepository dao;

	public List<Customer> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Customer> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<Customer> getByLastname(String lastname) {
		return dao.getByLastname(lastname);
	}

	@Override
	public List<Long> findByFromUserId(int age) {
		return dao.findByFromUserId(age);
	}

	@Override
	public int updateLastname(String lastname) {
		return dao.updateLastname(lastname);
	}

}
