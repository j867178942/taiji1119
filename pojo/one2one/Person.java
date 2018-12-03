package com.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="t_person")
@Entity
public class Person {
	private Integer id;
	private String dname;
	
	private Card card;
	
	//注意：一对一关联关系，需要添加unique=true
	@JoinColumn(name="card_id",unique=true)
	@OneToOne
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.Card = card;
	}
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
}
