package com.pcc.gateway.model;

import java.util.Collection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponse 
{

	private String userId;
	
	private String accessToken;
	
	private String refreshToken;
	
	private long expireAt;
	
	private Collection<String> authorities;
	
}
