package com.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDAO;
import com.dao.ShoppingCartDAO;
import com.model.Book;
import com.model.ShoppingCart;

@Service
public class ShoppingCartservice {
@Autowired
ShoppingCartDAO shoppingcartDao;
BookDAO bookDao;
public String addShoppingCart(ShoppingCart shoppingcart) {
	shoppingcartDao.save(shoppingcart);
	return "Shopping cart added Sucessfully";
}
public List<Book> getListOfBook(int userId){
	List<ShoppingCart> shoppingcart=shoppingcartDao.findByUserId(userId);
	List<String> isbn=shoppingcart.stream().map(cart->cart.getBook().getIsbn()).collect(Collectors.toList());
	return bookDao.findByIsbnIn(isbn);
}
public void updateIsbn(int userid,String newIsbn) {
	List<ShoppingCart> cart=shoppingcartDao.findByUserId(userid);
	if(cart!=null && !cart.isEmpty()) {
		for(ShoppingCart c:cart) {
			if(c.getBook()!=null) {
				c.getBook().setIsbn(newIsbn);
				
			}
		}
		
		shoppingcartDao.saveAll(cart);
		
	}
	 
}
}
