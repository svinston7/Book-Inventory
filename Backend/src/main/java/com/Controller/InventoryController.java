package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.InventoryService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.Inventory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postInventory(@RequestBody Inventory entity)  throws InvalidInputException {
		
		try {
				        
			inventoryService.addInventory(entity);
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "Inventory added successfully"));
	     } catch (Exception e) {
	        // Log the exception for debugging
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }	}
	
	@GetMapping("{inventoryId}")
	public ResponseEntity<?> getInventory(@PathVariable int inventoryId) throws InvalidInputException, ResourceNotFoundException  {
		Inventory inventory= inventoryService.findById(inventoryId);
		return new ResponseEntity<>(inventory,HttpStatus.OK);
	}
	
	@PutMapping("/update/purchased/{inventoryId}")
	public ResponseEntity<?> putMethodName(@PathVariable int inventoryId, @RequestBody boolean purchased) throws InvalidInputException, ResourceNotFoundException  {
		
		inventoryService.updatePurchased(inventoryId, purchased);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
}
