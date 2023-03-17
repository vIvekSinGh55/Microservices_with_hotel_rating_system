package com.pcc.rating.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcc.rating.entities.Rating;
import com.pcc.rating.exceptions.ResourceNotFoundException;
import com.pcc.rating.repository.RatingRepository;
import com.pcc.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	
	@Autowired
	RatingRepository ratingRepository;
	
	
	@Override
	public Rating create(Rating rating) 
	{
		return this.ratingRepository.save(rating);
	}

	
	@Override
	public List<Rating> getRatings() 
	{
		return this.ratingRepository.findAll();
	}

	
	@Override
	public List<Rating> getRatingByUserId(String userId) throws ResourceNotFoundException
	{
		List<Rating> ratingsOfUsers = this.ratingRepository.findByUserId(userId);
	
		return ratingsOfUsers;
	}

	
	@Override
	public List<Rating> getRatingByHotelId(String hotelId) 
	{
		List<Rating> ratingsOfHotels = this.ratingRepository.findByHotelId(hotelId);
//		if(ratingsOfHotels.isEmpty())
//		{
//			throw new ResourceNotFoundException("hotel with given id not found !!" + hotelId);
//		}
		
		return ratingsOfHotels;
		
	}

	
}
