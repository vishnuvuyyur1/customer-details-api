package com.customerdetails.model;

public enum ErrorMessage {

	ERROR_GET("Unexpected error while getting customer information"),
	ERROR_POST("Unexpected error while creating a customer"),
	ERROR_UPDATE("Unexpected error while updating a customer"), ERROR_NOT_FOUND("Customer does not exist");

	private String value;

	ErrorMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
