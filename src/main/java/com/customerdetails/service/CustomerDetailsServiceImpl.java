package com.customerdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerdetails.dao.CustomerDetailsRepository;
import com.customerdetails.entities.Address;
import com.customerdetails.entities.Customer;
import com.customerdetails.exception.CustomerDetailsApiException;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService{

	@Autowired
    private CustomerDetailsRepository customerDetailsRepository;
	
	@Override
	public Customer addCustomer(Customer customer) {
		return customerDetailsRepository.save(customer);
	}

	@Override
	public List<Customer> getCustomers() {
		return customerDetailsRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		return getCustomer(id);
	}


	@Override
	public List<Customer> getCustomerByFirstName(String firstName) {
		return customerDetailsRepository.findByFirstName(firstName);
	}


	@Override
	public Address updateCustomerAddress(Long customerId, Address newAddress) {
		Customer existingCustomer = getCustomer(customerId);

		return null;
	}
	
	private Customer getCustomer(Long id) {
		return customerDetailsRepository
		.findById(id)
		.orElseThrow(() -> new CustomerDetailsApiException("Customer does not exist for id :: " + id));
	}

}
