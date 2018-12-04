package com.example.demo.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name="tcustomer")
@Entity
public class Customer {
	
	private Integer id;
	private String lastname;
	private String eamil;
	private int age;
	private Date birth1;
	private Date birth;
	
	/*@TableGenerator(name="id_generator",
			table="jpa_id_generators",
			pkColumnName="pk_name",
			pkColumnValue="customer_id",
			valueColumnName="pk_value",
			allocationSize=100 //每一次自增几
			)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="id_generator")//主键生成的方式
*/	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id //当前对应表中的主键字段
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getlastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public Date getBirth1() {
		return birth1;
	}
	public void setBirth1(Date birth1) {
		this.birth1 = birth1;
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
		System.out.println("name:"+lastname+",age:"+age);
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastname + ", eamil=" + eamil + ", age=" + age + "]";
	}
}