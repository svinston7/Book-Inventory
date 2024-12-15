package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inventory {
	@Id
	private int inventoryId;
	private String isbn;
	private int ranks;
	private int purchased;
	
	
	public Inventory(int inventoryId, String isbn, int ranks, int purchased) {
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getRanks() {
		return ranks;
	}
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}
	public int getPurchased() {
		return purchased;
	}
	public void setPurchased(int purchased) {
		this.purchased = purchased;
	}
	

}
