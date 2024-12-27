package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.BookConditionService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.BookCondition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/bookcondition")
@CrossOrigin("*")
public class BookConditionController {

	@Autowired
	BookConditionService conditionService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postCondtion(@RequestBody BookCondition bookCondition )throws InvalidInputException {
		try {
			conditionService.addBookCondition(bookCondition);
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "BookCondition added successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }
		
	}
	
	@GetMapping("/{ranks}")
	public ResponseEntity<?> getCondition(@PathVariable int ranks) throws InvalidInputException{
		BookCondition bookCondition = conditionService.getBookCondition(ranks);
		return new ResponseEntity<>(bookCondition,HttpStatus.OK);
	}
	
	@PutMapping("/update/price/{ranks}")
	public ResponseEntity<?> updatePrice(@PathVariable int ranks,@RequestBody double price)throws InvalidInputException, ResourceNotFoundException {
		conditionService.updatePrice(ranks, price);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
	
	@PutMapping("/update/description/{ranks}")
	public ResponseEntity<?> updateDescription(@PathVariable int ranks,@RequestBody String desc)throws InvalidInputException, ResourceNotFoundException {
		conditionService.updateDescription(ranks, desc);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
	@PutMapping("/update/fulldescription/{ranks}")
	public ResponseEntity<?> updateFull(@PathVariable int ranks,@RequestBody String desc)throws InvalidInputException, ResourceNotFoundException {
		conditionService.updateFullDescription(ranks, desc);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
	
	
}
