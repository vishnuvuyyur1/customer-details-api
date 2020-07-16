package com.customerdetails.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomerDetailsApiException extends RuntimeException{ 

	private static final long serialVersionUID = 1L;

		public CustomerDetailsApiException(String errorMessage , Exception  e) {
	        super(errorMessage, e);
	    }
		
		public CustomerDetailsApiException(String errorMessage) {
	        super(errorMessage);
	    }
}
