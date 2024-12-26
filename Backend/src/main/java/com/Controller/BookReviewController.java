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
import com.exception.CustomException;
import com.exception.Response;
import com.model.BookReview;
import com.model.Reviewer;

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
    public ResponseEntity<?> postReview(@RequestBody BookReview review) {
        
        BookReview existingReview = bookReviewService.findByisbn(review.getIsbn());
        
        if (existingReview != null) {
          
            throw new CustomException("ADDFAILS", "Book Reviewer already exists");
        }
        
        
        bookReviewService.addBookReview(review);

        
        Response successResponse = new Response("POSTSUCCESS", "Book Reviewer added successfully");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
	
	@GetMapping("{isbn}")
	public ResponseEntity<?> getAllReviewers(@PathVariable String isbn) {
		List<Reviewer> reviewersList = bookReviewService.getAllReviewers(isbn);
		
		return new ResponseEntity<List<Reviewer>>(reviewersList,HttpStatus.OK);
	}
//	
//	@PutMapping("/update/rating/{isbn}")
//	public ResponseEntity<?> updateRating(@RequestBody String isbn,int rating) {
//		BookReview bookReview = bookReviewService.findByisbn(isbn);
//		bookReview.setRating(rating);
//		bookReviewService.addBookReview(bookReview);
//		return new ResponseEntity<String>("Updated",HttpStatus.OK);
//	}
	@PutMapping("/update/rating/{isbn}")
	public ResponseEntity<?> updateRating(@RequestBody String isbn, int rating) {
	    BookReview bookReview = bookReviewService.findByisbn(isbn);
	    
	    if (bookReview == null) {
	        throw new RuntimeException("Book not found with isbn: " + isbn);
	    }
	    
	    bookReview.setRating(rating);
	    bookReviewService.addBookReview(bookReview);
	    return new ResponseEntity<>("Updated", HttpStatus.OK);
	}

	
	@PutMapping("/update/comment/{isbn}")
	public ResponseEntity<?> updateComment(@RequestBody String isbn,String comment) {
		BookReview bookReview = bookReviewService.findByisbn(isbn);
		bookReview.setComments(comment);
		bookReviewService.addBookReview(bookReview);
		return new ResponseEntity<String>("Updated",HttpStatus.OK);
	}
}
