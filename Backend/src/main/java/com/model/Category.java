package com.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Category {
	@Id
	@Min(value = 1, message = "Category ID must be a positive number")
	private int catId;
	@NotBlank(message = "Category description cannot be null or blank")
    @Size(max = 255, message = "Category description cannot exceed 255 characters")
	
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
