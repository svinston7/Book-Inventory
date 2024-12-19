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
import com.model.Book;

@RestController
@RequestMapping("/api/book")
@CrossOrigin("*")
public class BookController {

	
	@Autowired
	BookService bookService;
	
	
	@PostMapping("/post")
	public ResponseEntity<?> postBook(@RequestBody Book book){
		try {
			bookService.addBook(book);
			return new ResponseEntity<>(Map.of("code", "POSTSUCCESS", "message", "Book added successfully"),HttpStatus.OK);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>( Map.of("code","ADDFAILS","message","Book already exist"),HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	}
	
	@GetMapping("")
	public ResponseEntity<?>  getAllBooks() {
		List<Book>bookList = bookService.getAll();
		return new ResponseEntity<List<Book>> (bookList,HttpStatus.OK);
	}
	
	@GetMapping("/{isbn}")
	public ResponseEntity<?>  getByisbn(@PathVariable String isbn) {
		Book book = bookService.findByIsbn(isbn);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<?> getByTitle(@PathVariable String title){
		Book book = bookService.findByTitle(title);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	@GetMapping("/{publisherId}")
	public ResponseEntity<?> getByPublisherId(@PathVariable String publisherId){
		Book book = bookService.findByPublisherId(publisherId);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	@PutMapping("/update/{isbn}")
	public ResponseEntity<?>  putByIsbn(@PathVariable String isbn,Book book) {
		bookService.updateBook(isbn, book);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	
}
