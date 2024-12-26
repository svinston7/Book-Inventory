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
import com.model.Category;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
		
		return new ResponseEntity<> (category,HttpStatus.CREATED);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<?> getCat(@PathVariable int catId){
		Category cat = categoryService.findById(catId);
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
	
	@PutMapping("/update/description/{catId}")
	public ResponseEntity<?> updateCat(@PathVariable int catId,@RequestBody String description){
		Category cat = categoryService.updateDescription(catId, description);
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
}
