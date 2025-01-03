package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.BookReviewService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.BookReview;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/bookreview")
@CrossOrigin("*")
public class BookReviewController {

	@Autowired
	BookReviewService bookReviewService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postReview(@RequestBody BookReview review) throws InvalidInputException  {
		 try {
		        bookReviewService.addBookReview(review);
		        return ResponseEntity.ok(new Response("POSTSUCCESS", "Review added successfully"));
		    } catch (Exception e) {
		        // Log the exception for debugging
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(new Response("ADDFAILS", "An unexpected error occurred"));
		    }
		 }
	
	@GetMapping("{isbn}")
	public ResponseEntity<?> getAllReviewers(@PathVariable String isbn) throws InvalidInputException, ResourceNotFoundException  {
		List<BookReview> reviewersList = bookReviewService.getAllReviewers(isbn);
		
		return new ResponseEntity<List<BookReview>>(reviewersList,HttpStatus.OK);
	}
	
	@PutMapping("/update/rating/{isbn}")
	public ResponseEntity<?> updateRating(@RequestBody String isbn,@RequestBody int rating) throws InvalidInputException, ResourceNotFoundException  {
		BookReview bookReview = bookReviewService.findByisbn(isbn);
		bookReview.setRating(rating);
		bookReviewService.addBookReview(bookReview);
		return new ResponseEntity<String>("Updated",HttpStatus.OK);
	}
	
	@PutMapping("/update/comment/{isbn}")
	public ResponseEntity<?> updateComment(@RequestBody String isbn,@RequestBody String comment) throws InvalidInputException, ResourceNotFoundException  {
		BookReview bookReview = bookReviewService.findByisbn(isbn);
		bookReview.setComments(comment);
		bookReviewService.addBookReview(bookReview);
		return new ResponseEntity<String>("Updated",HttpStatus.OK);
	}
}
