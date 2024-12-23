package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inventory {
	@Id
	@GeneratedValue
	private int inventoryId;
	
	@ManyToOne
    private Book book;
	
	private String isbn;
	
	private int ranks;
	private boolean purchased;
	
	
	public Inventory() {
		super();
		this.inventoryId = inventoryId;
		this.isbn = isbn;
		this.ranks = ranks;
		this.purchased = purchased;
	}
	
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Book getIsbn() {
		return book;
	}
	public void setIsbn(Book isbn) {
		this.book = isbn;
	}
	public int getRanks() {
		return ranks;
	}
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}
	public boolean getPurchased() {
		return purchased;
	}
	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	

}
