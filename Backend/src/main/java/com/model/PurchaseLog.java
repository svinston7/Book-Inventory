package com.model;

public class PurchaseLog {
	private int userId;
	private int inventoryId;
	
	
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
