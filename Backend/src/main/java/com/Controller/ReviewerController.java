package com.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<?> getReviewer(@RequestBody int id) {
		Reviewer reviewer = reviewerService.findById(id);
		return new ResponseEntity<>(reviewer,HttpStatus.OK);
	}
	
	@PutMapping("/name/{reviewerId}")
	public ResponseEntity<?> updateName(@RequestBody int id,String name) {
		String res = reviewerService.updatereviewerFirstName(id, name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@PutMapping("employedby/{reviewerId}")
	public ResponseEntity<?> updateEmployedBy(@RequestBody int id,String name) {
		String res = reviewerService.updateReviewerEmployedId(id, name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
