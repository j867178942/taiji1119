package com.demo.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name="t_customer")
@Entity
public class Customer {
	
	private Integer id;
	private String lastName;
	private String eamil;
	private int age;
	private Date createdTime;
	private Date birth;
	
	private Set<Order> orders = new HashSet<>();
	
	@JoinColumn(name="customer_id")
	@OneToMany   // fetch=FetchType.EAGER
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id //当前对应表中的主键字段
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="last_name",length=50,nullable=true)
	//nullable=false 当前列必须给值不能为空, 默认 true 可以为空
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEamil() {
		return eamil;
	}
	public void setEamil(String eamil) {
		this.eamil = eamil;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Transient
	public void setInfo(){
		System.out.println("name:"+lastName+",age:"+age);
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", eamil=" + eamil + ", age=" + age + "]";
	}
}
