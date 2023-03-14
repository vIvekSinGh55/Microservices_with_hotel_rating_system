package com.pcc.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcc.user.entities.User;
import com.pcc.user.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController 
{

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User users = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(users);
	}
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex)
	{
		logger.info("Fallback is executed because service is down: ", ex.getMessage());
		User user = User.builder()
						.email("dummy@email.com")
						.name("Dummy")
						.about("This user is created dummy because some service is down")
						.userId("dummy123")
						.build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
}
