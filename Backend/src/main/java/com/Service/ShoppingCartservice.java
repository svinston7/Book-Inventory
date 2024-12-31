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
@Autowired
BookDAO bookDao;
public void addShoppingCart(ShoppingCart shoppingcart) {
	
	shoppingcartDao.save(shoppingcart);
}
public List<Book> getListOfBook(String username) {
    // Fetching the list of ShoppingCart objects associated with the given username
    List<ShoppingCart> shoppingcart = shoppingcartDao.findByUserName(username);
    
    // Creating a list of ISBNs from the shopping cart entries
    List<String> isbns = shoppingcart.stream()
        .map(ShoppingCart::getIsbn) // Directly using getIsbn() to fetch the ISBN
        .collect(Collectors.toList());
    
    // Fetching books by ISBNs
    List<Book> books = bookDao.findByIsbnIn(isbns);
    
    // Returning the list of books
    return books;
}

public void updateIsbn(String username,String newIsbn) {
	List<ShoppingCart> cart=shoppingcartDao.findByUserName(username);
	if(cart!=null && !cart.isEmpty()) {
		for(ShoppingCart c:cart) {
			if(c.getBook()!=null) {
				c.getBook().setIsbn(newIsbn);
				
			}
		}
		
		shoppingcartDao.saveAll(cart);
		
	}
	 
}
public void removecart(String username, String isbn) {
    // Fetch the user's shopping cart entries
    List<ShoppingCart> cartItems = shoppingcartDao.findByUserName(username);

    if (cartItems != null && !cartItems.isEmpty()) {
        // Find the cart item with the given ISBN
        ShoppingCart itemToRemove = null;

        for (ShoppingCart item : cartItems) {
            if (item.getIsbn().equals(isbn)) {
                itemToRemove = item;
                break;
            }
        }

        // Remove the item if found
        if (itemToRemove != null) {
            shoppingcartDao.delete(itemToRemove);
            System.out.println("Item with ISBN " + isbn + " removed from cart.");
        } else {
            System.out.println("Item with ISBN " + isbn + " not found in cart.");
        }
    } else {
        System.out.println("No items found in the cart for username: " + username);
    }
}
}
