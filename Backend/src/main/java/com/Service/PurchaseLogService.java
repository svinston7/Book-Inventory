package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PurchaseLogDAO;
import com.dao.UserDAO;
import com.model.PurchaseLog;
import com.model.User;
@Service
public class PurchaseLogService {
	@Autowired
	PurchaseLogDAO purchaselogDao;
	
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
}
