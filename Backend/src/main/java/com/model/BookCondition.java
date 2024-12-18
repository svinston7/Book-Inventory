package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BookCondition {
	@Id
	private int ranks;
	private String description;
	private String fullDescription;
	private double price;
	
	public BookCondition() {}
	
	public BookCondition(int ranks, String description, String fullDescription, double price) {
		super();
		this.ranks = ranks;
		this.description = description;
		this.fullDescription = fullDescription;
		this.price = price;
	}
	
	
	public int getRanks() {
		return ranks;
	}
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookCondition [ranks=" + ranks + ", description=" + description + ", fullDescription=" + fullDescription
				+ ", price=" + price + "]";
	}
	
	
}
