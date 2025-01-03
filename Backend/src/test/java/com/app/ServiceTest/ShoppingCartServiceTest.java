package com.app.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Service.ShoppingCartservice;
import com.dao.BookDAO;
import com.dao.ShoppingCartDAO;
import com.model.Book;
import com.model.ShoppingCart;

class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartservice shoppingCartService;

    @Mock
    private ShoppingCartDAO shoppingCartDAO;

    @Mock
    private BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserName("sam");
        cart.setIsbn("123-456");

        when(shoppingCartDAO.save(cart)).thenReturn(cart);

        shoppingCartService.addShoppingCart(cart);

        verify(shoppingCartDAO, times(1)).save(cart);
    }

    @Test
    public void testGetListOfBook() {
        // Given
        String username = "testUser";

        // Mocking shopping cart entries
        when(shoppingCartDAO.findByUserName(username))
            .thenReturn(Arrays.asList(
                new ShoppingCart(1, "ISBN123", null, username),
                new ShoppingCart(2, "ISBN456", null, username)
            ));

        // Mocking books
        when(bookDAO.findByIsbnIn(Arrays.asList("ISBN123", "ISBN456")))
            .thenReturn(Arrays.asList(
                new Book("ISBN123", "Book1", "Description1", 1, "1st", 101, "image1.jpg", 0),
                new Book("ISBN456", "Book2", "Description2", 2, "2nd", 102, "image2.jpg", 0)
            ));

        // When
        List<Book> result = shoppingCartService.getListOfBook(username);

        // Then
        assertFalse(result.isEmpty(), "Result list is empty!"); // Ensure the list is not empty
        assertEquals(2, result.size()); // Assert the size of the list
        assertEquals("Book1", result.get(0).getTitle());
        assertEquals("Book2", result.get(1).getTitle());

        // Verifying interactions with DAOs
        verify(shoppingCartDAO, times(1)).findByUserName(username);
        verify(bookDAO, times(1)).findByIsbnIn(Arrays.asList("ISBN123", "ISBN456"));
    }




    @Test
    void testUpdateIsbn() {
        ShoppingCart cart = new ShoppingCart();
        Book book = new Book();
        book.setIsbn("123-OLD");
        cart.setBook(book);
        cart.setUserName("sam");

        when(shoppingCartDAO.findByUserName("sam")).thenReturn(Arrays.asList(cart));

        shoppingCartService.updateIsbn("sam", "456-NEW");

        assertEquals("456-NEW", cart.getBook().getIsbn());
        verify(shoppingCartDAO, times(1)).findByUserName("sam");
        verify(shoppingCartDAO, times(1)).saveAll(Arrays.asList(cart));
    }

    @Test
    void testUpdateIsbnWithEmptyCart() {
        when(shoppingCartDAO.findByUserName("sam")).thenReturn(Arrays.asList());

        shoppingCartService.updateIsbn("sam", "456-NEW");

        verify(shoppingCartDAO, times(1)).findByUserName("sam");
        verify(shoppingCartDAO, never()).saveAll(any());
    }
}