package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.PurchaseLogService;
import com.model.PurchaseLog;

@RestController
@RequestMapping("/api/purchaselog")
@CrossOrigin("*") 
public class PurchaseLogController {

	@Autowired
	PurchaseLogService purchaseLogService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postPurchase(@RequestBody PurchaseLog purchaseLog) {
		purchaseLogService.addPurchaseLog(purchaseLog);
		
		return new ResponseEntity<>(purchaseLog,HttpStatus.OK);
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getPurchase(@PathVariable int userId){
		PurchaseLog log = purchaseLogService.findById(userId);
		return new ResponseEntity<PurchaseLog>(log,HttpStatus.OK);
	}
	
	@PutMapping("/update/inventoryId/{userId}")
	public ResponseEntity<?> updatePurchase(@PathVariable int userId,int inventoryId){
		purchaseLogService.updateInventoryByUserId(userId,inventoryId );
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
}
