package com.pcc.user.services;

import java.util.List;

import com.pcc.user.entities.User;

public interface UserService 
{

	//user operations
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given userId
	User getUser(String userId);
	
	//TODO: Delete
	void deleteUser(String userId);
	
	//TODO: Update
	User updateUser(User user, String userId);
	
}
