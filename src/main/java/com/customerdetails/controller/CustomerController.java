package com.customerdetails.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.customerdetails.entities.Address;
import com.customerdetails.entities.Customer;
import com.customerdetails.exception.CustomerDetailsApiException;
import com.customerdetails.model.ApiResponse;
import com.customerdetails.service.CustomerDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	private static final String SUCCESS="Success";

	@Autowired
	private CustomerDetailsServiceImpl customerService;

	@GetMapping("/customers")
	public CompletableFuture<ApiResponse<?>> getAllCustomers() {
		try {
			return CompletableFuture.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS ,customerService.getCustomers()));
		} catch (Exception ex) {
			throw new CustomerDetailsApiException(ex.getMessage(), ex);
		}
	}

	@GetMapping("/customers/{id}")
	public CompletableFuture<ApiResponse<?>> getCustomersById(@PathVariable(value = "id") Long customerId)
			throws CustomerDetailsApiException {
		try {
			return CompletableFuture.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS,customerService.getCustomerById(customerId)));
		} catch (Exception ex) {
			throw new CustomerDetailsApiException(ex.getMessage(), ex);
		}
	}

	@PostMapping("/customers")
	public CompletableFuture<ApiResponse<?>> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			return CompletableFuture.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS,customerService.addCustomer(customer)));
		} catch (Exception ex) {
			throw new CustomerDetailsApiException(ex.getMessage(), ex);
		}

	}

	@PutMapping("/customers/{id}/address")
	public CompletableFuture<ApiResponse<?>> updateCustomerAddress(@PathVariable(value = "id") Long customerId,
			@Valid @RequestBody Address address) {
		try {
			return CompletableFuture.supplyAsync(() -> new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, customerService.updateCustomerAddress(customerId, address)));
		} catch (Exception ex) {
			throw new CustomerDetailsApiException(ex.getMessage(), ex);
		}
	}
}
