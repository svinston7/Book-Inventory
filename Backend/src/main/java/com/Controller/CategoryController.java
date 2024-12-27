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

import com.Service.CategoryService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.Category;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postCategory(@RequestBody Category category) {
		
		try {
			categoryService.addCategory(category);	        
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "Category added successfully"));
	     } catch (Exception e) {
	        // Log the exception for debugging
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }
		}
	
	@GetMapping("/{catId}")
	public ResponseEntity<?> getCat(@PathVariable int catId)  throws InvalidInputException, ResourceNotFoundException {
		Category cat = categoryService.findById(catId);
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
	
	@PutMapping("/update/description/{catId}")
	public ResponseEntity<?> updateCat(@PathVariable int catId,@RequestBody String description) throws InvalidInputException, ResourceNotFoundException {
		Category cat = categoryService.updateDescription(catId, description);
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
}
