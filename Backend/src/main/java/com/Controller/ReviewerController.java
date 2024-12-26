package com.Controller;

import java.util.Map;

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
import com.model.Reviewer;

@RestController
@RequestMapping("/api/reviewer")
@CrossOrigin("*")
public class ReviewerController {

	@Autowired
	ReviewerService reviewerService;
	
	
	@PostMapping("/post")
	public ResponseEntity<?> postReviewer(@RequestBody Reviewer reviewer) {
		reviewerService.addReviewer(reviewer);
		
		return new ResponseEntity<>(Map.of("code", "POSTSUCCESS", "message", "Reviewer added successfully"),HttpStatus.OK);
	}
	
	@GetMapping("/employedby/{reviewerId}")
	public ResponseEntity<?> getReviewer(@PathVariable int reviewerId) {
		Reviewer reviewer = reviewerService.findById(reviewerId);
		return new ResponseEntity<>(reviewer,HttpStatus.OK);
	}
	
	@PutMapping("/name/{reviewerId}")
	public ResponseEntity<?> updateName(@PathVariable int reviewerId,@RequestBody String name) {
		String res = reviewerService.updatereviewerFirstName(reviewerId, name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@PutMapping("employedby/{reviewerId}")
	public ResponseEntity<?> updateEmployedBy(@PathVariable int reviewerId,@RequestBody String name) {
		String res = reviewerService.updateReviewerEmployedId(reviewerId, name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
