package com.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
	private int catId;
	private String catDescription;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> books;
	
	
	public Category(int catId, String catDescription) {
		super();
		this.catId = catId;
		this.catDescription = catDescription;
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
