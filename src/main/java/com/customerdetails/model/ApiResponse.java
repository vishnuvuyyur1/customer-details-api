package com.customerdetails.model;

import lombok.Data;

@Data
public class ApiResponse<T> {

	  private int statusCode;
	  private String message;
	  private T data;
	
	  public ApiResponse(int statusCode, String message, T data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}  
	 
}
