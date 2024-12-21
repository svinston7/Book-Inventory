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
	public ResponseEntity<?> postCondtion(@RequestBody BookCondition bookCondition ) {
		
		conditionService.addBookCondition(bookCondition);
		return new ResponseEntity<>(bookCondition,HttpStatus.CREATED);
	}
	
	@GetMapping("/{ranks}")
	public ResponseEntity<?> getCondition(@PathVariable int ranks) {
		BookCondition bookCondition = conditionService.getBookCondition(ranks);
		return new ResponseEntity<>(bookCondition,HttpStatus.OK);
	}
	
	@PutMapping("/update/price/{ranks}")
	public ResponseEntity<?> updatePrice(@PathVariable int ranks,double price) {
		conditionService.updatePrice(ranks, price);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
	
	@PutMapping("/update/price/{ranks}")
	public ResponseEntity<?> updateDescription(@PathVariable int ranks,String desc) {
		conditionService.updateDescription(ranks, desc);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
	@PutMapping("/update/price/{ranks}")
	public ResponseEntity<?> updateFull(@PathVariable int ranks,String desc) {
		conditionService.updateFullDescription(ranks, desc);
		return new ResponseEntity<>("Updated",HttpStatus.OK);
	}
	
	
}
