package com.pcc.user.payloads;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // so the we can follow the builder pattern to build the object of this class.
public class ApiResponse 
{

	private String message;
	private boolean success;
	private HttpStatus status;
	
}
