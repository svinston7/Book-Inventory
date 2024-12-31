package com.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Service.BookService;
import com.Service.ShoppingCartservice;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.Book;
import com.model.ShoppingCart;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartservice cartservice;
	@Autowired
	BookService bookservice;
	
	
	@PostMapping("/post")
	public ResponseEntity<String> addToCart(@RequestBody ShoppingCart shoppingCart) {
	    if (shoppingCart.getBook() == null && shoppingCart.getIsbn() != null) {
	        // Fetch the Book entity by ISBN
	        Book book = bookservice.findByIsbn(shoppingCart.getIsbn());
	        if (book == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
	        }
	        shoppingCart.setBook(book);
	    }
	    cartservice.addShoppingCart(shoppingCart);
	    return ResponseEntity.ok("Item added to cart");
	}
	@GetMapping("/{username}")
	public ResponseEntity<?> getCart(@PathVariable String username)throws InvalidInputException, ResourceNotFoundException {
		List<Book> bookList = cartservice.getListOfBook(username);
		return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
	}
	
	@PutMapping("/update/{username}")
	public ResponseEntity<?> updateCart(@PathVariable String username,@RequestBody String isbn)throws InvalidInputException, ResourceNotFoundException {
		cartservice.updateIsbn(username,isbn );
		return new ResponseEntity< >("Updataed",HttpStatus.OK);
	}
	
	@PostMapping("/remove")
	public ResponseEntity<String> removeCartItem(@RequestBody Map<String, String> payload)throws InvalidInputException, ResourceNotFoundException {
	    String username = payload.get("userName");
	    String isbn = payload.get("isbn");

	    if (username == null || isbn == null) {
	        return ResponseEntity.badRequest().body("Missing username or ISBN");
	    }

	    cartservice.removecart(username, isbn);
	    return new ResponseEntity<>("Item removed from cart for user " + username,HttpStatus.OK);
	}
	
}
