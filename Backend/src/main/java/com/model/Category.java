package com.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
	private int catId;
	private String catDescription;
	

	
	public Category(int catId, String catDescription) {
		super();
		this.catId = catId;
		this.catDescription = catDescription;
	}
	
	public Category() {
		super();
	}

	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatDescription() {
		return catDescription;
	}
	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}
	
	

}
