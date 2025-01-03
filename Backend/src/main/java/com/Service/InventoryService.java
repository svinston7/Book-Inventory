package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InventoryDAO;
import com.model.Inventory;

@Service
public class InventoryService {
	@Autowired
	InventoryDAO inventoryDao;
	
	public List<Inventory> getAll(){
		return inventoryDao.findAll();
	}
	
	public Inventory findById(int id) {
	    return inventoryDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
	}

	public void removeInventory(int id) {
		inventoryDao.deleteById(id);
	}
	public void addInventory(Inventory inventory) {
		inventoryDao.save(inventory);
	}
	public void updatePurchased(int inventoryId,boolean purchased) {
		Inventory inventory=inventoryDao.findById(inventoryId).orElse(null);
		inventory.setPurchased(purchased);
		inventoryDao.save(inventory);
		
	}
}
