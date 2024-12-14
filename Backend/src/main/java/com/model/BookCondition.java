package com.model;

public class BookCondition {
	private int ranks;
	private String description;
	private String fullDescription;
	private int price;
	
	
	
	public BookCondition(int ranks, String description, String fullDescription, int price) {
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
