package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.InventoryService;
import com.exception.CustomException;
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
    public ResponseEntity<?> postInventory(@RequestBody Inventory entity) {
       
        Inventory existingInventory = inventoryService.getAll().stream()
                .filter(inventory -> inventory.getBook().getIsbn().equals(entity.getBook().getIsbn()))
                .findFirst()
                .orElse(null);

        if (existingInventory != null) {
            
            throw new CustomException("ADDFAILS", "Inventory already exists");
        }

        
        inventoryService.addInventory(entity);

      
        Response successResponse = new Response("POSTSUCCESS", "Inventory added successfully");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }	

	
	@GetMapping("{inventoryId}")
	public ResponseEntity<?> getInventory(@PathVariable int inventoryId) {
		Inventory inventory= inventoryService.findById(inventoryId);
		return new ResponseEntity<>(inventory,HttpStatus.OK);
	}
	
	@PutMapping("/update/purchased/{inventoryId}")
	public ResponseEntity<?> putMethodName(@PathVariable int inventoryId, @RequestBody boolean purchased) {
		
		inventoryService.updatePurchased(inventoryId, purchased);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
}
