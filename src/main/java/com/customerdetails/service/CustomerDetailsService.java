package com.customerdetails.service;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerdetails.dao.CustomerDetailsRepository;
import com.customerdetails.entities.Address;
import com.customerdetails.entities.Customer;
import com.customerdetails.exception.CustomerDetailsApiException;

@Service
public class CustomerDetailsService implements ICustomerDetailsService{

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
	public List<Customer> getCustomersByFirstName(String firstName) {
		return customerDetailsRepository.findByFirstName(firstName);
	}

	@Override
	public Customer updateCustomerAddress(Long customerId, Address newAddress) {
		Customer existingCustomer = getCustomer(customerId);
		Set<Address> updatedAddress = existingCustomer.getAddresses().stream()
			    .map(existingAddress -> existingAddress.getAddressType().toString().equals(newAddress.getAddressType().toString()) ? updateAddress(newAddress,existingAddress) : existingAddress)
			    .collect(toSet());
		existingCustomer.getAddresses().clear();
		existingCustomer.getAddresses().addAll(updatedAddress);
		return customerDetailsRepository.save(existingCustomer);
	}
	
	private Address updateAddress(Address newAddress, Address existing) {
		newAddress.setCustomer(existing.getCustomer());
		newAddress.setId(existing.getId());
		return newAddress;
	}
	
	private Customer getCustomer(Long id) {
		return customerDetailsRepository
		.findById(id)
		.orElseThrow(() -> new CustomerDetailsApiException("Customer does not exist for id :: " + id));
	}

}
