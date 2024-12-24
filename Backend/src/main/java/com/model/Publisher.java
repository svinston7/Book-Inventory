package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Publisher {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publisherId;
	private String name;
	private String city;
	private String stateCode;
	
	
	public Publisher(int publisherId, String name, String city, String stateCode) {
		super();
		this.publisherId = publisherId;
		this.name = name;
		this.city = city;
		this.stateCode = stateCode;
	}
	
	
	public Publisher() {
		super();
	}


	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	  
	
	
	
}
