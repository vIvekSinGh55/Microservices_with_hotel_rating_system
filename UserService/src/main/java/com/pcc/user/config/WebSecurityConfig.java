package com.pcc.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 				// so that we can use security on method level like has role...
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception 
	{
		security.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
		return security.build();
	}
	
	
}
