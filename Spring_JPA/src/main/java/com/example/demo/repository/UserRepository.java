package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.pojo.Customer;

public interface UserRepository extends JpaRepository<Customer,Integer>, JpaSpecificationExecutor<Customer>  {
	public List<Customer> findById(Integer id);
	public List<Customer> getByLastname(String lastname);
	@Query("select count(id) from Customer where age =:age")
    List<Long> findByFromUserId(@Param("age") int age);
	@Modifying
	@Query("update Customer c set c.age=7 where c.lastname=:lastname")
    public int updateLastname(@Param("lastname") String lastname);
}
