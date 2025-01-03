package com.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Service.BookService;
import com.Service.ShoppingCartservice;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.Book;
import com.model.ShoppingCart;

@ExtendWith(MockitoExtension.class)
class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartservice cartservice;

    @Mock
    private BookService bookservice;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Test
    public void testAddToCart_WithBook() throws InvalidInputException {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.setBook(new Book("ISBN123", "Book1", "Description1", 1, "1st", 101, "image.jpg", 0));

        doNothing().when(cartservice).addShoppingCart(cart);

        // Act
        ResponseEntity<String> response = shoppingCartController.addToCart(cart);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item added to cart", response.getBody());
        verify(cartservice, times(1)).addShoppingCart(cart);
        verify(bookservice, never()).findByIsbn(any());
    }

    @Test
    public void testAddToCart_WithISBN() throws InvalidInputException {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.setIsbn("ISBN123");
        Book book = new Book("ISBN123", "Book1", "Description1", 1, "1st", 101, "image.jpg", 0);

        when(bookservice.findByIsbn("ISBN123")).thenReturn(book);
        doNothing().when(cartservice).addShoppingCart(cart);

        // Act
        ResponseEntity<String> response = shoppingCartController.addToCart(cart);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item added to cart", response.getBody());
        assertEquals(book, cart.getBook());

        verify(bookservice, times(1)).findByIsbn("ISBN123");
        verify(cartservice, times(1)).addShoppingCart(cart);
    }

    @Test
    public void testAddToCart_BookNotFound() throws InvalidInputException {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.setIsbn("ISBN999");

        when(bookservice.findByIsbn("ISBN999")).thenReturn(null);

        // Act
        ResponseEntity<String> response = shoppingCartController.addToCart(cart);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found", response.getBody());

        verify(bookservice, times(1)).findByIsbn("ISBN999");
        verify(cartservice, never()).addShoppingCart(any());
    }
 @Test
    public void testGetCart_Success() throws InvalidInputException, ResourceNotFoundException {
        // Arrange
        String username = "testUser";
        List<Book> bookList = Arrays.asList(
                new Book("ISBN123", "Book1", "Description1", 1, "1st", 101, "image.jpg", 0),
                new Book("ISBN456", "Book2", "Description2", 2, "2nd", 102, "image2.jpg", 0)
        );

        when(cartservice.getListOfBook(username)).thenReturn(bookList);

        // Act
        ResponseEntity<?> response = shoppingCartController.getCart(username);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookList, response.getBody());
        verify(cartservice, times(1)).getListOfBook(username);
    }

    @Test
    public void testGetCart_EmptyList() throws InvalidInputException, ResourceNotFoundException {
        // Arrange
        String username = "testUser";
        when(cartservice.getListOfBook(username)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = shoppingCartController.getCart(username);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
        verify(cartservice, times(1)).getListOfBook(username);
    }

     @Test
    public void testUpdateCart_Success() throws InvalidInputException, ResourceNotFoundException {
        // Arrange
        String username = "testUser";
        String isbn = "ISBN123";

        doNothing().when(cartservice).updateIsbn(username, isbn);

        // Act
        ResponseEntity<?> response = shoppingCartController.updateCart(username, isbn);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updataed", response.getBody());
        verify(cartservice, times(1)).updateIsbn(username, isbn);
    }

    @Test
    public void testRemoveCartItem_Success() throws InvalidInputException, ResourceNotFoundException {
        // Arrange
        Map<String, String> payload = new HashMap<>();
        payload.put("userName", "testUser");
        payload.put("isbn", "ISBN123");

        doNothing().when(cartservice).removecart("testUser", "ISBN123");

        // Act
        ResponseEntity<String> response = shoppingCartController.removeCartItem(payload);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item removed from cart for user testUser", response.getBody());
        verify(cartservice, times(1)).removecart("testUser", "ISBN123");
    }

    @Test
    public void testRemoveCartItem_MissingParameters() throws InvalidInputException, ResourceNotFoundException {
        // Arrange
        Map<String, String> payload = new HashMap<>();
        payload.put("userName", "testUser");

        // Act
        ResponseEntity<String> response = shoppingCartController.removeCartItem(payload);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Missing username or ISBN", response.getBody());
        verify(cartservice, never()).removecart(any(), any());
    }
}
