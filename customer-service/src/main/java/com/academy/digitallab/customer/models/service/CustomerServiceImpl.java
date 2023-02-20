package com.academy.digitallab.customer.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.digitallab.customer.models.entity.Customer;
import com.academy.digitallab.customer.models.entity.Region;
import com.academy.digitallab.customer.models.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> findCustomerAll() {
		return customerRepository.findAll();
	}

	public List<Customer> findCustomersByRegion(Region region) {
		return customerRepository.findByRegion(region);
	}

	public Customer createCustomer(Customer customer) {

		Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
		if (customerDB != null) {
			return customerDB;
		}

		customer.setState("CREATED");
		customerDB = customerRepository.save(customer);
		return customerDB;
	}

	public Customer updateCustomer(Customer customer) {
		Customer customerDB = getCustomer(customer.getId());
		if (customerDB == null) {
			return null;
		}
		customerDB.setFirstName(customer.getFirstName());
		customerDB.setLastName(customer.getLastName());
		customerDB.setEmail(customer.getEmail());
		customerDB.setPhotoUrl(customer.getPhotoUrl());

		return customerRepository.save(customerDB);
	}

	public Customer deleteCustomer(Customer customer) {
		Customer customerDB = getCustomer(customer.getId());
		if (customerDB == null) {
			return null;
		}
		customer.setState("DELETED");
		return customerRepository.save(customer);
	}


	public Customer getCustomer(Long id) {
		return customerRepository.findById(id).orElse(null);
	}
}