package com.demo.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="t_item")
@Entity
public class Item {
	private Integer id;
	private String itemName;
	
	private Set<Category> categories = new HashSet<>();

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@JoinTable(name="item_category",
			joinColumns={@JoinColumn(name="item_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="category_id",referencedColumnName="id")})
	@ManyToMany(cascade={CascadeType.REMOVE})
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	
}
