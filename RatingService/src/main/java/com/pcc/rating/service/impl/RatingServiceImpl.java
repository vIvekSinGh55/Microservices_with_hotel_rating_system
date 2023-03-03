package com.pcc.rating.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcc.rating.entities.Rating;
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
	public List<Rating> getRatingByUserId(String userId) 
	{
		return this.ratingRepository.findByUserId(userId);
	}

	
	@Override
	public List<Rating> getRatingByHotelId(String hotelId) 
	{
		return this.ratingRepository.findByHotelId(hotelId);
	}

	
}
