package com.customerdetails.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customerdetails.entities.Customer;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT * FROM CUSTOMER where first_name = :firstName;", nativeQuery = true)
	public List<Customer> findByFirstName(@Param("firstName") String firstName);
}
