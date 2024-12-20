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
	
	public List<PurchaseLog> findByUserId(int userId) {
		return purchaselogDao.findByUserId(userId);
	}
	
	
//	public String updateInventoryByUserId(Integer userId, Integer inventoryId) {
//        List<PurchaseLog> purchaselog=purchaselogDao.findByUserId(userId);
//        if(purchaselog.isEmpty()) {
//        	return "No purchase log found for user with user id"+userId;
//        	
//        }
//        Inventory inventory=inventoryDao.findById(inventoryId).orElse(null);
//        
//        if(inventory==null) {
//        return "Inventory with id not found";
//        }
//        
//        for(PurchaseLog purchase:purchaselog) {
//        	//purchaselog.setInventory()
//        }
//        
//        //incomplete
//    }
}
