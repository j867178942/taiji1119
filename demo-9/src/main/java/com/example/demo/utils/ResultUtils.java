package com.example.demo.utils;

import java.io.Serializable;

public class ResultUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Result success() {
		
		return new Result("1" ,"操作成功", "") ;
		
	}
	
	
	public static Result error() {
		
		return new Result("-1","操作成功", "") ;
		
	}
}
