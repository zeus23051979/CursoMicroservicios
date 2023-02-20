package com.academy.digitallab.customer.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.digitallab.customer.models.entity.Customer;
import com.academy.digitallab.customer.models.entity.Region;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByNumberID(String numberID);

	public List<Customer> findByLastName(String lastName);

	public List<Customer> findByRegion(Region region);
	
}