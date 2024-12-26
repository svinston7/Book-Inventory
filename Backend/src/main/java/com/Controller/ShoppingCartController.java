package com.Controller;

import java.util.List;

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

import com.Service.ShoppingCartservice;
import com.model.Book;
import com.model.ShoppingCart;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartservice cartservice;

	@PostMapping("/post")
	public ResponseEntity<?> postCart(@RequestBody ShoppingCart cart){
		String res = cartservice.addShoppingCart(cart);
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<?> getCart(@PathVariable int userid){
		List<Book> bookList = cartservice.getListOfBook(userid);
		return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
	}
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<?> updateCart(@PathVariable int userid,@RequestBody String isbn){
		cartservice.updateIsbn(userid,isbn );
		return new ResponseEntity< >("Updataed",HttpStatus.OK);
	}
	
}
