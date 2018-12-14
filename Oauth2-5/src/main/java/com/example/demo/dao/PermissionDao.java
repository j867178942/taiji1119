package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Permission;

/**
 * Created by yangyibo on 17/1/20.
 */
@Mapper
public interface PermissionDao {
	public List<Permission> findAll();

	public List<Permission> findByAdminUserId(String userId);
}
