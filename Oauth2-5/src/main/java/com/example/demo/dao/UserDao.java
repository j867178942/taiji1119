package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.User;

/**
 * @author andyzhao
 */
@Mapper
public interface UserDao {
	User findByUserName(String user_name);
	
	User findByMobile(String user_name);

	// @Secured({ "ROLE_ADMIN", "ROLE_USER" })
	List<User> listAll();
}
