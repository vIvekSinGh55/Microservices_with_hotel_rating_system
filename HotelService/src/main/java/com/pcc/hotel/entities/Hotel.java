package com.pcc.hotel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hotels")
public class Hotel 
{

	@Id
	@Column(name = "hote_ID")
	private String id;
	
	@Column( name="hotel_name" )
	private String name;
	
	@Column( name="hotel_location" )
	private String location;
	
	@Column( name="hotel_about" )
	private String about;
	
}
