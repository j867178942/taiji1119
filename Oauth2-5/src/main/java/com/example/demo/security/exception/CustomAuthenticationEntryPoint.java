package com.example.demo.security.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.demo.domain.ResultDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		ObjectMapper mapper = new ObjectMapper();
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(0);
		resultDTO.setData(authException.getMessage());
		resultDTO.setMessage(authException.getMessage());
		logger.error("CustomAuthenticationEntryPoint", authException);
		response.getWriter().append(mapper.writeValueAsString(resultDTO));
	}
}