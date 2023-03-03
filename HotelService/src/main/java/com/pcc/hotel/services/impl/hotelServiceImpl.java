package com.pcc.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcc.hotel.entities.Hotel;
import com.pcc.hotel.exceptions.ResourceNotFoundException;
import com.pcc.hotel.repository.HotelRepository;
import com.pcc.hotel.services.HotelService;

@Service
public class hotelServiceImpl implements HotelService
{
	
	@Autowired
	private HotelRepository hotelRepository;
	
	
	@Override
	public Hotel create(Hotel hotel) 
	{
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() 
	{
		return hotelRepository.findAll();	
	}

	@Override
	public Hotel get(String id) 
	{
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!" + id));
	}

}
