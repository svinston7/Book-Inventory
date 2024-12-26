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

import com.Service.AuthorService;
import com.model.Author;

@RequestMapping("/api/author")
@RestController
@CrossOrigin("*")
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postAuthor(@RequestBody Author author){
		authorService.addAuthor(author);
		return new ResponseEntity<>(Map.of("code","POSTSUCCESS"),HttpStatus.OK);
	}
	
	@GetMapping("/authorid/{authorId}")
	public ResponseEntity<?> getAuthorById(@PathVariable int authorId){
		Author author = authorService.findById(authorId);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
		
	}
	
	@GetMapping("/firstname/{firstname}")
	public ResponseEntity<?> getAuthorByfirstname(@PathVariable String firstname){
		Author author = authorService.findByFirstName(firstname);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
		
	}
	@GetMapping("/lastname/{lastname}")
	public ResponseEntity<?> getAuthorlastname(@PathVariable String lastname){
		Author author = authorService.findByFirstName(lastname);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
		
	}
	
	@PutMapping("/firstname/{authorId}")
	public ResponseEntity<?> updateFirstName(@PathVariable int authorId,@RequestBody String firstname){
		Author author = authorService.findById(authorId);
		author.setFirstName(firstname);
		authorService.addAuthor(author);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
		
	}
	
	@PutMapping("/lastname/{authorId}")
	public ResponseEntity<?> updateLastName(@PathVariable int authorId,@RequestBody String lastname){
		Author author = authorService.findById(authorId);
		author.setLastName(lastname);
		authorService.addAuthor(author);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
		
	}
	
}
