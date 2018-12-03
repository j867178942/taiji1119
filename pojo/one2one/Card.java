package com.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="t_card")
@Entity
public class Card {
	private Integer id;
	private String mname;
	
	private Person person;
	
	//对于不需要维护对象之间关系的对象（没有外键的一方），使用@OneToOne，
	//建议设置mappedBy属性，放弃维护关系，有利于数据库执行的效率
	@OneToOne(mappedBy="person")
	public Person getDept() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
}
