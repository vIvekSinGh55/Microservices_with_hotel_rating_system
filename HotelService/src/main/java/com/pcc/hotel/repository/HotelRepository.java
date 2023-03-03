package com.pcc.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcc.hotel.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String>
{

}
