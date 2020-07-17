package com.customerdetails.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.customerdetails.data.TestData;
import com.customerdetails.service.CustomerDetailsService;

/**
 * Unit Testing the controller with a stand alone setup using MockMVC to mimic
 * an API call to the controller along with mock a service.
 * Includes: Two demo test cases
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerDetailsService customerService;

	private TestData testData;

	private static final String TEST_ENDPOINT_CUSTOMERS = "/api/v1/customers";

	@BeforeEach
	void setUp() {
		testData = new TestData();
	}

	@Test
	@DisplayName("Given a call to an API end poin to get all customers, we expect 200 Status with the actual size of customers provided by our mock service test data")
	void getAllCustomersTest() throws Exception {
		given(customerService.getCustomers()).willReturn(testData.getCustomers());
		MvcResult mvcResult = mockMvc.perform(get(TEST_ENDPOINT_CUSTOMERS)).andExpect(request().asyncStarted())
				.andDo(MockMvcResultHandlers.log()).andReturn();

		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(testData.getCustomers().size())));

		Mockito.verify(customerService, Mockito.times(1)).getCustomers();
	}

}
