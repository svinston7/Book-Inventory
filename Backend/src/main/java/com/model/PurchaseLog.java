package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PurchaseLog {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private int userId;
	private int inventoryId;
	
	@ManyToOne
    private Inventory inventory;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
