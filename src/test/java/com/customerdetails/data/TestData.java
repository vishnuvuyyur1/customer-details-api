package com.customerdetails.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.customerdetails.entities.Address;
import com.customerdetails.entities.Customer;
import com.customerdetails.model.AddressType;
/**
 * Test data being shared for unit and integration tests 
 * across multiple tests.
 * 
 */
@Component
public class TestData {

	public Customer getCustomer() {
		Set<Address> addressSet = new HashSet<>();
		addressSet.add(new Address("333 John doe st", "London", "North England", "600345", "UK", AddressType.LIVING));
		Customer customer = new Customer("Hello", "world", 100, addressSet);
		return customer;
	}

	public List<Customer> getCustomers() {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(new Customer("user2@gmail.com", "pwd2", 100,
				Set.of(new Address("333 John doe st", "London", "North England", "600345", "UK", AddressType.LIVING))));
		customerList.add(new Customer("user2@gmail.com", "pwd2", 100, Set.of(new Address("444 adam eve st", "Amsterdam",
				"North Holland", "1296BB", "Netherlands", AddressType.LIVING))));
		customerList.add(new Customer("user2@gmail.com", "pwd2", 100, Set.of(
				new Address("555 john wick st", "Utrecht", "Utrecht", "5349211", "Netherlands", AddressType.LIVING))));
		return customerList;
	}
}
