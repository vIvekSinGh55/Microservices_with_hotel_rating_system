package com.pcc.hotel.controller;

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

import com.pcc.hotel.entities.Hotel;
import com.pcc.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController 
{

	@Autowired
	private HotelService hotelService;
	

	//create 
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
	// get Single
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}
	
	// get All
	@GetMapping("")
	public ResponseEntity<List<Hotel>> getAllHotel()
	{
		return ResponseEntity.ok(hotelService.getAll());
	}
	
	
}
