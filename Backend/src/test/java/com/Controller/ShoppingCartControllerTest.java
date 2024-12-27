package com.Controller;

import com.Controller.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
 import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.Controller.ShoppingCartController;
import com.Service.ShoppingCartservice;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.Book;
import com.model.ShoppingCart;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartservice shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Test
    public void testPostCart() throws InvalidInputException {
        ShoppingCart cart = new ShoppingCart();
        when(shoppingCartService.addShoppingCart(cart)).thenReturn("Shopping cart added Sucessfully");

        ResponseEntity<?> response = shoppingCartController.postCart(cart);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        //assertEquals("Shopping cart added Sucessfully", response.getBody());

        verify(shoppingCartService, times(1)).addShoppingCart(cart);
    }

    @Test
    public void testGetCart() throws InvalidInputException, ResourceNotFoundException {
    	Book book1 = new Book(null, null, null, 0, null, 0, null);
    	book1.setIsbn("12345");
    	book1.setTitle("Book One");
    	

    	Book book2 = new Book(null, null, null, 0, null, 0, null);
    	book2.setIsbn("67890");
    	book2.setTitle("Book Two");
    	

    	List<Book> books = Arrays.asList(book1, book2);
        when(shoppingCartService.getListOfBook(1)).thenReturn(books);

        ResponseEntity<?> response = shoppingCartController.getCart(1);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());

        verify(shoppingCartService, times(1)).getListOfBook(1);
    }

    @Test
    public void testUpdateCart() throws InvalidInputException, ResourceNotFoundException {
        ResponseEntity<?> response = shoppingCartController.updateCart(1, "99999");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updataed", response.getBody());

        verify(shoppingCartService, times(1)).updateIsbn(1, "99999");
    }
}
