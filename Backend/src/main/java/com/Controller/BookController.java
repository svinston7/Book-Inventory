package com.Controller;

import java.util.List;
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

import com.Service.BookService;
import com.exception.CustomException;
import com.exception.Response;
import com.model.Book;

@RestController
@RequestMapping("/api/book")
@CrossOrigin("*")
public class BookController {

	
	@Autowired
	BookService bookService;
	
	 @PostMapping("/post")
	    public ResponseEntity<Response> postBook(@RequestBody Book book) {
	        // Check if book already exists by ISBN or title
	        if (bookService.findByIsbn(book.getIsbn()) != null || bookService.findByTitle(book.getTitle()) != null) {
	            throw new CustomException("ADDFAILS", "Book already exists");
	        }

	        // Add book if not found
	        bookService.addBook(book);

	        // Return success response
	        Response response = new Response("POSTSUCCESS", "Book added successfully");
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    } 
	
	@GetMapping("")
	public ResponseEntity<?>  getAllBooks() {
		List<Book>bookList = bookService.getAll();
		return new ResponseEntity<List<Book>> (bookList,HttpStatus.OK);
	}
	
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<?>  getByisbn(@PathVariable String isbn) {
		Book book = bookService.findByIsbn(isbn);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<?> getByTitle(@PathVariable String title){
		Book book = bookService.findByTitle(title);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	@GetMapping("/publisherId/{publisherId}")
	public ResponseEntity<?> getByPublisherId(@PathVariable int publisherId){
		List<Book> book = bookService.findByPublisherId(publisherId);
		return new ResponseEntity <List<Book>>(book,HttpStatus.OK);
	}
	
	@PutMapping("/update/{isbn}")
	public ResponseEntity<?>  putByIsbn(@PathVariable String isbn,Book book) {
		bookService.updateBook(isbn, book);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	
}
