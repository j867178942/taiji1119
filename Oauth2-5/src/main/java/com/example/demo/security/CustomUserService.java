package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PermissionDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.Permission;
import com.example.demo.domain.User;

/**
 * 自定义UserDetailsService 接口
 */
@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		// 重写loadUserByUsername 方法获得 userdetails 类型用户
		User user = userDao.findByUserName(username);
		if (user == null) {
			User mobileUser = userDao.findByMobile(username);
			if (mobileUser == null) {
				throw new UsernameNotFoundException("用户不存在");
			} else {
				return new org.springframework.security.core.userdetails.User(mobileUser.getLogin_name(), mobileUser.getPassword(),
						getGrantedAuthorities(mobileUser));
			}
		} else {
			return new org.springframework.security.core.userdetails.User(user.getLogin_name(), user.getPassword(), getGrantedAuthorities(user));
		}
		// List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
		// List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		// for (Permission permission : permissions) {
		// if (permission != null && permission.getPermission_name() != null) {
		// GrantedAuthority grantedAuthority = new
		// SimpleGrantedAuthority(permission.getPermission_name());
		// grantedAuthorities.add(grantedAuthority);
		// }
		// }

	}

	List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Permission permission : permissions) {
			if (permission != null && permission.getPermission_name() != null) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermission_name());
				grantedAuthorities.add(grantedAuthority);
			}
		}
		return grantedAuthorities;
	}

}
