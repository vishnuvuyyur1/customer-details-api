package com.customerdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customerdetails.dao.CustomerDetailsRepository;
import com.customerdetails.data.TestData;
import com.customerdetails.entities.Customer;
/**
 * Unit Testing the service class with Stand alone setup 
 * with pure mocking and no integration.
 * Includes: Two demo test cases
 */
@ExtendWith(MockitoExtension.class)
public class CustomerDetailsServiceTest {

	@InjectMocks
	private CustomerDetailsService customerDetailsService;

	@Mock
	private CustomerDetailsRepository customerDetailsRepository;

	private TestData testData;

	@BeforeEach
	void setUp() {
		testData = new TestData();
	}

	@Test
	@DisplayName("Given an operation from service to get all customer, we expect service to invoke the respository to get the data")
	public void getCustomersTest() {
		given(customerDetailsRepository.findAll()).willReturn(testData.getCustomers());
		List<Customer> expected = customerDetailsService.getCustomers();
		assertEquals(expected, testData.getCustomers());
		Mockito.verify(customerDetailsRepository, Mockito.times(1)).findAll();
	}

	@Test
	@DisplayName("Given an operation from service to get customer based on ID, we expect service to invoke the respository to get the data")
	public void getCustomerByIdTest() {
		final Long id = 1L;
		given(customerDetailsRepository.findById(id)).willReturn(Optional.of(testData.getCustomer()));
		Customer expected = customerDetailsService.getCustomerById(id);
		assertEquals(expected, testData.getCustomer());
		Mockito.verify(customerDetailsRepository, Mockito.times(1)).findById(Mockito.anyLong());
	}
}
