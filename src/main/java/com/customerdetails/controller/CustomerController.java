package com.customerdetails.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerdetails.entities.Address;
import com.customerdetails.entities.Customer;
import com.customerdetails.exception.CustomerDetailsApiException;
import com.customerdetails.model.ApiResponse;
import com.customerdetails.model.ErrorMessage;
import com.customerdetails.service.CustomerDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller layer to handle HTTP requests
 *
 */
@Slf4j
@RestController
@RequestMapping("/management/api/v1")
public class CustomerController {

	private static final String SUCCESS = "Success";
	private static final String REQUEST_PARAM_FIRSTNAME = "firstName";
	private static final String PATH_CUSTOMERS = "/customers";
	private static final String PATH_CUSTOMER_BY_ID = "/customers/{id}";
	private static final String PATH_CUSTOMER_ADDRESS = "/customers/{id}/address";

	@Autowired
	private CustomerDetailsService customerService;

	@GetMapping(PATH_CUSTOMERS)
	public CompletableFuture<ApiResponse<?>> getAllCustomers() {
		try {
			List<Customer> customersList = customerService.getCustomers();
			return CompletableFuture
					.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, customersList));
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new CustomerDetailsApiException(ErrorMessage.ERROR_GET.getValue(), ex);
		}
	}

	@GetMapping(PATH_CUSTOMER_BY_ID)
	public CompletableFuture<ApiResponse<?>> getCustomerById(@PathVariable(value = "id") Long customerId)
			throws CustomerDetailsApiException {
		Customer customer = customerService.getCustomerById(customerId);
		try {
			return CompletableFuture.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, customer));
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new CustomerDetailsApiException(ErrorMessage.ERROR_GET.getValue(), ex);
		}
	}

	@GetMapping(value = PATH_CUSTOMERS, params = REQUEST_PARAM_FIRSTNAME)
	public CompletableFuture<ApiResponse<?>> getCustomersByFirstName(
			@RequestParam(name = REQUEST_PARAM_FIRSTNAME, required = true) String firstName)
			throws CustomerDetailsApiException {
		try {
			List<Customer> customers = customerService.getCustomersByFirstName(firstName);
			return CompletableFuture.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, customers));
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new CustomerDetailsApiException(ErrorMessage.ERROR_GET.getValue(), ex);
		}
	}

	@PostMapping(PATH_CUSTOMERS)
	public CompletableFuture<ApiResponse<?>> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			return CompletableFuture.supplyAsync(
					() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, customerService.addCustomer(customer)));
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new CustomerDetailsApiException(ErrorMessage.ERROR_POST.getValue(), ex);
		}
	}

	@PutMapping(PATH_CUSTOMER_ADDRESS)
	public CompletableFuture<ApiResponse<?>> updateCustomerAddress(@PathVariable(value = "id") Long customerId,
			@Valid @RequestBody Address address) {
		try {
			Customer updated = customerService.updateCustomerAddress(customerId, address);
			return CompletableFuture.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, updated));
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new CustomerDetailsApiException(ErrorMessage.ERROR_UPDATE.getValue(), ex);
		}
	}
}
