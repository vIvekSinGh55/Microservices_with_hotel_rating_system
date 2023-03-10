package com.pcc.user.controller;

import java.util.List;

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

@RestController
@RequestMapping("/users")
public class UserController 
{

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User users = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(users);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping("")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
}
