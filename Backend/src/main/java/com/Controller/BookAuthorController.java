package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Service.BookAuthorService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.Author;
import com.model.Book;
import com.model.BookAuthor;

@RestController
@RequestMapping("/api/bookauthors")
public class BookAuthorController {

    @Autowired
    private BookAuthorService bookAuthorService;

    // Get all book authors
    @GetMapping("/getallbookauthors")

    public ResponseEntity<List<BookAuthor>> getAllBookAuthors() throws InvalidInputException, ResourceNotFoundException{
        List<BookAuthor> bookAuthors = bookAuthorService.getAll();
        return new ResponseEntity<>(bookAuthors, HttpStatus.OK);
    }
    
    @GetMapping("/isbn/{isbn}")	
    public ResponseEntity<?> getAuthorDetailsByIsbn(@PathVariable String isbn)throws InvalidInputException, ResourceNotFoundException{
    	List<Author> author=bookAuthorService.getAuthorDetailsByIsbn(isbn);
    	return new ResponseEntity<>(author,HttpStatus.OK);
    }
    // Get a book author by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookAuthor> getBookAuthorById(@PathVariable int id) throws InvalidInputException, ResourceNotFoundException{
        try {
            BookAuthor bookAuthor = bookAuthorService.findById(id);
            return new ResponseEntity<>(bookAuthor, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("authorid/{authorid}")
    public ResponseEntity<?> getAuthorById(@PathVariable int authorid) throws InvalidInputException, ResourceNotFoundException{
        try {
            List<Book > books = bookAuthorService.getBookDetailsById(authorid);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new book author
    @PostMapping("/post")
    public ResponseEntity<?> addBookAuthor(@RequestBody BookAuthor bookAuthor)throws InvalidInputException {
		try {
			bookAuthorService.addBookAuthor(bookAuthor);
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "BookAuhtor added successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }
		
		
	}
    // Delete a book author by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookAuthor(@PathVariable int id) throws InvalidInputException, ResourceNotFoundException{
        try {
            bookAuthorService.removeBookAuthor(id);
            return new ResponseEntity<>("Book author deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Book author not found", HttpStatus.NOT_FOUND);
        }
    }
}
