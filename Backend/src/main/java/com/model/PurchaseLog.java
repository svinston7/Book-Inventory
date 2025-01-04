package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class PurchaseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotNull(message = "User ID cannot be null.")
    @Min(value = 1, message = "User ID must be a positive number.")
    private int userId;

    @NotNull(message = "Inventory ID cannot be null.")
    @Min(value = 1, message = "Inventory ID must be a positive number.")
    private int inventoryId;

    @ManyToOne
    @NotNull(message = "Inventory must not be null.")
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
