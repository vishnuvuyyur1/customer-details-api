package com.customerdetails.service;

import java.util.List;

import com.customerdetails.entities.Address;
import com.customerdetails.entities.Customer;

public interface ICustomerDetailsService {

	public Customer addCustomer(Customer customer);

	public List<Customer> getCustomers();

	public Customer updateCustomerAddress(Long customerId, Address address);

	public Customer getCustomerById(Long id);

	public List<Customer> getCustomersByFirstName(String firstName);

}
