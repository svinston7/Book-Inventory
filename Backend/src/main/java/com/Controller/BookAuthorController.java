package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Service.BookAuthorService;
import com.model.Author;
import com.model.BookAuthor;

@RestController
@RequestMapping("/api/bookauthors")
public class BookAuthorController {

    @Autowired
    private BookAuthorService bookAuthorService;

    // Get all book authors
    @GetMapping("/getallbookauthors")
    public ResponseEntity<List<BookAuthor>> getAllBookAuthors() {
        List<BookAuthor> bookAuthors = bookAuthorService.getAll();
        return new ResponseEntity<>(bookAuthors, HttpStatus.OK);
    }
    
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> getAuthorDetailsByIsbn(@PathVariable String isbn){
    	Author author=bookAuthorService.getAuthorDetailsByIsbn(isbn);
    	return new ResponseEntity<>(author,HttpStatus.OK);
    }
    // Get a book author by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookAuthor> getBookAuthorById(@PathVariable int id) {
        try {
            BookAuthor bookAuthor = bookAuthorService.findById(id);
            return new ResponseEntity<>(bookAuthor, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new book author
    @PostMapping("/post")
    public ResponseEntity<String> addBookAuthor(@RequestBody BookAuthor bookAuthor) {
        bookAuthorService.addBookAuthor(bookAuthor);
        return new ResponseEntity<>("Book author added successfully", HttpStatus.CREATED);
    }
 
    // Delete a book author by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookAuthor(@PathVariable int id) {
        try {
            bookAuthorService.removeBookAuthor(id);
            return new ResponseEntity<>("Book author deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Book author not found", HttpStatus.NOT_FOUND);
        }
    }
}
