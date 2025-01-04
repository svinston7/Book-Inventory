package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class BookCondition {
	@Id
	@PositiveOrZero(message = "ranks must be a positive number or zero")
	
	private int ranks;
	@NotBlank(message = "description cannot be null or blank")
    @Size(max = 100, message = "Description should not exceed 100 characters")
	
	private String description;
	@NotBlank(message = "full Description cannot be null or blank")
    @Size(max = 500, message = "Full description should not exceed 500 characters")
	
	private String fullDescription;
	@DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
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
