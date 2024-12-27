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
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.PurchaseLog;

@RestController
@RequestMapping("/api/purchaselog")
@CrossOrigin("*") 
public class PurchaseLogController {

	@Autowired
	PurchaseLogService purchaseLogService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postPurchase(@RequestBody PurchaseLog purchaseLog) throws InvalidInputException {
		try {
			purchaseLogService.addPurchaseLog(purchaseLog);
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "Log added successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }
		
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getPurchase(@PathVariable int userId)throws InvalidInputException, ResourceNotFoundException {
		PurchaseLog log = purchaseLogService.findById(userId);
		return new ResponseEntity<PurchaseLog>(log,HttpStatus.OK);
	}
	
	@PutMapping("/update/inventoryId/{userId}")
	public ResponseEntity<?> updatePurchase(@PathVariable int userId,@RequestBody int inventoryId)throws InvalidInputException, ResourceNotFoundException {
		purchaseLogService.updateInventoryByUserId(userId,inventoryId );
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
}
