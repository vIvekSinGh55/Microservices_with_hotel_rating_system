package com.pcc.hotel.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pcc.hotel.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{

//	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex)
//	{
//		String message = ex.getMessage();
//		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
//		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
//		
//	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException ex)
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
	}
	
}
