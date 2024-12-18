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
    @JoinColumn(name = "ISBN")
    private Book book;
	
	private int ranks;
	private boolean purchased;
	
	
	public Inventory(int inventoryId, Book isbn, int ranks, boolean purchased) {
		super();
		this.inventoryId = inventoryId;
		this.book = isbn;
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
	

}
