package cn.com.taiji.mongoweb.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @id这个注解来对应mongo的_id这个字段
	@Id
	private String _id;
	private int id;
	private String name;
	private int age;

	public User(String _id, int id, String name, int age) {
		this._id = _id;
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public User(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
		super();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "User [_id=" + _id + ", id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
