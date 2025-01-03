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

import com.Service.ReviewerService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.Reviewer;

@RestController
@RequestMapping("/api/reviewer")
@CrossOrigin("*")
public class ReviewerController {

	@Autowired
	ReviewerService reviewerService;
	@PostMapping("/post")
	public ResponseEntity<?> postReviewer(@RequestBody Reviewer reviewer) throws InvalidInputException {
		try {
	        
			reviewerService.addReviewer(reviewer);
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "Reviewer added successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }
		
	}
	
	@GetMapping("/employedby/{reviewerId}")
	public ResponseEntity<?> getReviewer(@PathVariable int reviewerId)throws InvalidInputException, ResourceNotFoundException  {
		Reviewer reviewer = reviewerService.findById(reviewerId);
		return new ResponseEntity<>(reviewer,HttpStatus.OK);
	}
	
	@PutMapping("/name/{reviewerId}")

	public ResponseEntity<?> updateName(@PathVariable int reviewerId,@RequestBody String name) throws InvalidInputException, ResourceNotFoundException {
		String res = reviewerService.updatereviewerFirstName(reviewerId, name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@PutMapping("employedby/{reviewerId}")

	public ResponseEntity<?> updateEmployedBy(@PathVariable int reviewerId,@RequestBody String name) throws InvalidInputException, ResourceNotFoundException {

		String res = reviewerService.updateReviewerEmployedId(reviewerId, name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
