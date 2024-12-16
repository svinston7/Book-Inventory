package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PurchaseLog {
	@Id
	private int inventoryId;
	
	private int userId;
	
	
	public PurchaseLog(int userId, int inventoryId) {
		super();
		this.userId = userId;
		this.inventoryId = inventoryId;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	
	
}
