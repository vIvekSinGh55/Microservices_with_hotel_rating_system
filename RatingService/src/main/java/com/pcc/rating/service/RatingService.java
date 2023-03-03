package com.pcc.rating.service;

import java.util.List;

import com.pcc.rating.entities.Rating;

public interface RatingService {
	
	
	//create rating
	Rating create(Rating rating);
	
	
	//get all ratings
	List<Rating> getRatings();
	
	
	//get all by userId
	List<Rating> getRatingByUserId(String userId);
	
	
	//get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);
	

}