package com.pcc.rating.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcc.rating.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>
{

	//Custom finder method
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
