package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;

public class CustomlAuthoritiesExtractor extends FixedAuthoritiesExtractor {

	@Autowired
	UserDao userdao;

	private static final String[] PRINCIPAL_KEYS = new String[] { "user", "username", "userid", "user_id", "login", "name", "id" };

	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {

		String username = null;
		User user = null;

		// Set<Entry<String, Object>> set = map.entrySet();
		// for (Entry<String, Object> entry : set) {
		// System.out.println(entry.getKey() + "======" + entry.getValue());
		// }

		for (String key : PRINCIPAL_KEYS) {
			if (map.containsKey(key)) {
				username = map.get(key).toString();
			}
		}

		if (username != null) {
			System.out.println(username);
			user = userdao.findByUserName(username);

		}

		if (user != null) {
			List<String> list = new ArrayList<>();
			for (Role role : user.getRoles()) {
				list.add(role.getRole_name());
			}
			String[] authorities = (String[]) list.toArray();
			System.out.println(user.getRoles().get(0).getRole_name());
			return AuthorityUtils.createAuthorityList(authorities);
		} else {
			// 默认设置为User
			String authorities = "ROLE_User";
			return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
		}
	}

}
