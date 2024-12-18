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
	
	@ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "InventoryID")
    private Inventory inventory;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
	
	
}
