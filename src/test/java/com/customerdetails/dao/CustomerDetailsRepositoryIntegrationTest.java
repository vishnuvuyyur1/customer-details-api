package com.customerdetails.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.customerdetails.data.TestData;
import com.customerdetails.entities.Customer;

/**
 * Integration Testing the repository by establishing a real interaction to the
 * in-memory DataBase. Includes: Two demo test cases
 */
@SpringBootTest
public class CustomerDetailsRepositoryIntegrationTest {
	private final Long TEST_ID = (long) 1;

	@Autowired
	private CustomerDetailsRepository customerRepository;

	@Autowired
	private TestData testData;

	@Test
	@DisplayName("Given an non existing id to fetch a customer from database, we expect the optional holdin the entity to retun false")
	public void testfindByIdNonExistingCustomer() {
		Optional<Customer> foundCustomer = customerRepository.findById(TEST_ID);
		assertThat(foundCustomer.isPresent()).isEqualTo(false);
	}

	@Test
	@DisplayName("Given an existing id to fetch a customer from database, we expect the enity to be exits with the right data of customer")
	public void testFindByIDExistingCustomer() {
		Customer customer = testData.getCustomer();
		Customer savedCustomer = customerRepository.save(customer);
		Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());
		assertThat(foundCustomer.isPresent()).isEqualTo(true);
		assertThat(foundCustomer.get().getFirstName()).isEqualTo("Hello");
	}

	@After
	public void cleanUp() {
		customerRepository.deleteAll();
	}

}
