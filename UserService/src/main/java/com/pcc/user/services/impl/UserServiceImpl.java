package com.pcc.user.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcc.user.entities.User;
import com.pcc.user.exceptions.ResourceNotFoundException;
import com.pcc.user.repositories.UserRepository;
import com.pcc.user.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User saveUser(User user) 
	{
		String random = UUID.randomUUID().toString();
		user.setUserId(random);
		return userRepository.save(user);
	}

	
	@Override
	public List<User> getAllUser() 
	{
		return userRepository.findAll();
	}

	
	@Override
	public User getUser(String userId) 
	{
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! :"+ userId));
	}
	
	@Override
	public void deleteUser(String userId)
	{
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! :"+ userId));
		this.userRepository.delete(user);
	}


	@Override
	public User updateUser(User user, String userId) {
		User userOne = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! :"+ userId));
		userOne.setName(user.getName());
		userOne.setEmail(user.getEmail());
		userOne.setAbout(user.getAbout());
//		User updatedUser = userOne.builder().name(user.getName()).email(user.getEmail()).about(user.getAbout()).build();
		
		return this.userRepository.save(userOne);
	}

}