package com.example.demo.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomMobileAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private String validateCode;

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDetails userDetails;

		try {
			userDetails = userService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			throw new BadCredentialsException("用户名不存在");
		}
		if (passwordEncoder.matches(password, userDetails.getPassword())) {
			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
			return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
		} else {
			if (password.equals(validateCode)) {
				Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
				return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
			} else {
				throw new BadCredentialsException("验证失败");
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
