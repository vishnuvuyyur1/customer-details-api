package com.customerdetails.service;

import java.util.List;

import com.customerdetails.entities.Address;
import com.customerdetails.entities.Customer;

/**
 * Interface specifying the operations offered by this service
 *
 */
public interface ICustomerDetailsService {

	public Customer addCustomer(final Customer customer);

	public List<Customer> getCustomers();

	public Customer updateCustomerAddress(final Long customerId, final Address address);

	public Customer getCustomerById(final Long id);

	public List<Customer> getCustomersByFirstName(final String firstName);

}
