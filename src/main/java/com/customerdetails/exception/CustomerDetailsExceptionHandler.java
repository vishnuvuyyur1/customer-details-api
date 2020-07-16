package com.customerdetails.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.customerdetails.model.ApiResponse;

@ControllerAdvice
public class CustomerDetailsExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String FAILURE ="Failed";

    private static final Logger LOGGER=LoggerFactory.getLogger(CustomerDetailsExceptionHandler.class);

	  
	  @ExceptionHandler(ConstraintViolationException.class)
	  public final ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		    ApiResponse<?> exceptionResponse = new ApiResponse<Object>(HttpStatus.BAD_REQUEST.value(), FAILURE, ex.getMessage());
			    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);		  
	  }
	  	  
	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		 StringBuffer sb =  new StringBuffer();
		  ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        sb.append(fieldName+"::"+ errorMessage);
		    });
		  ApiResponse<?> exceptionResponse = new ApiResponse<Object>(HttpStatus.BAD_REQUEST.value(),FAILURE, sb);
		    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);	
		}
	  
	  @ExceptionHandler(IllegalStateException.class)
	  public final ResponseEntity<?> handleIllegalStateException(ConstraintViolationException ex, WebRequest request){
		    ApiResponse<?> exceptionResponse = new ApiResponse<Object>(HttpStatus.BAD_REQUEST.value(),FAILURE, ex.getMessage());
			    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);		  
	  }
	  
	  @ExceptionHandler(CustomerDetailsApiException.class)
	  public final ResponseEntity<?> handleCustomerDetailsApiException(CustomerDetailsApiException ex, WebRequest request) {
		  ApiResponse<?> exceptionResponse = new ApiResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(),FAILURE, ex.getMessage());
		    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);	
	  }
	  	  
	  	  
}


