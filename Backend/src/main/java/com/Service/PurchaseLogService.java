package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InventoryDAO;
import com.dao.PurchaseLogDAO;
import com.dao.UserDAO;
import com.model.*;
import com.model.PurchaseLog;
import com.model.User;

@Service
public class PurchaseLogService {
	@Autowired
	PurchaseLogDAO purchaselogDao;
	@Autowired
	InventoryDAO inventoryDao;
	
	public List<PurchaseLog> getAll(){
		return purchaselogDao.findAll();
	}
	
	public PurchaseLog findById(int id) {
	    return purchaselogDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("PurchaseLog not found with id: " + id));
	}

	public void removePurchaseLog(int id) {
		purchaselogDao.deleteById(id);
	}
	public void addPurchaseLog(PurchaseLog purchaselog) {
		purchaselogDao.save(purchaselog);
		
		
	}
	
	public PurchaseLog findByUserId(int userId) {
		return purchaselogDao.findByUserId(userId);
	}
	
	
	public String updateInventoryByUserId(int userId, int inventoryId) {
        PurchaseLog purchase=purchaselogDao.findByUserId(userId);
        
       
        
        if(purchase==null) {
        return "Inventory with id not found";
        }
        else {
        	purchase.setInventoryId(inventoryId);
        	purchaselogDao.save(purchase);
        }
          	
        		return "Successfully updated";
    }
}
