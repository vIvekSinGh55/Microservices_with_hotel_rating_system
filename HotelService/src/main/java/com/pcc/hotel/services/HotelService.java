package com.pcc.hotel.services;

import java.util.List;

import com.pcc.hotel.entities.Hotel;

public interface HotelService 
{

	//Create 
	Hotel create(Hotel hotel);
	
	// GetAll
	List<Hotel> getAll();
	
	//get single
	Hotel get(String id);
	
}
