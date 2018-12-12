package com.example.demo.domain;


import java.io.Serializable;

public class Yanshengma implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Yanshengma() {
		
	}
	public String getYanzhengma() {
		return yanzhengma;
	}
	public void setYanzhengma(String yanzhengma) {
		this.yanzhengma = yanzhengma;
	}
	private String yanzhengma ;

}

