package com.pcc.user.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pcc.user.entities.Hotel;
import com.pcc.user.entities.Rating;
import com.pcc.user.entities.User;
import com.pcc.user.exceptions.ResourceNotFoundException;
import com.pcc.user.external.services.HotelService;
import com.pcc.user.repositories.UserRepository;
import com.pcc.user.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
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
		List<User> userList = this.userRepository.findAll();
		List<User> ratingsOfUser = userList.stream().map(user -> {
			Rating[] ratingList = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+ user.getUserId(), Rating[].class);
			// here we converting array to arrayList
			List<Rating> ratings = Arrays.stream(ratingList).toList();
	
			List<Rating> ratingOfHotel = ratings.stream().map(rating ->{
//				Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);// api call to hotel service to get the hotel
				Hotel hotel = this.hotelService.getHotel(rating.getHotelId());
				rating.setHotel(hotel);                  //set the hotel to rating
				return rating;                          // return the rating
			}).collect(Collectors.toList());
			
			user.setRatings(ratingOfHotel);
			return user;
		}).collect(Collectors.toList());
		return ratingsOfUser ;
	}

	
	@Override
	public User getUser(String userId) 
	{
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! :"+ userId));
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+ user.getUserId(),Rating[].class);
		
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//		logger.info("rating:{}",ratings);
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			Hotel hotel = this.hotelService.getHotel(rating.getHotelId());
//			logger.info("response status code: {} ",forEntity.getStatusCode());
			//set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());
//		logger.info("rating:{}",ratingList);
		user.setRatings(ratingList);
		return user;
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
