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
        cart.setUserId(1);
        cart.setIsbn("123-456");

        when(shoppingCartDAO.save(cart)).thenReturn(cart);

        String response = shoppingCartService.addShoppingCart(cart);

        assertEquals("Shopping cart added Sucessfully", response);
        verify(shoppingCartDAO, times(1)).save(cart);
    }

    @Test
    void testGetListOfBook() {
        ShoppingCart cart1 = new ShoppingCart();
        Book book1 = new Book();
        book1.setIsbn("123-ABC");
        cart1.setBook(book1);

        ShoppingCart cart2 = new ShoppingCart();
        Book book2 = new Book();
        book2.setIsbn("456-DEF");
        cart2.setBook(book2);

        List<ShoppingCart> cartList = Arrays.asList(cart1, cart2);
        List<Book> bookList = Arrays.asList(book1, book2);

        when(shoppingCartDAO.findByUserId(1)).thenReturn(cartList);
        when(bookDAO.findByIsbnIn(Arrays.asList("123-ABC", "456-DEF"))).thenReturn(bookList);

        List<Book> result = shoppingCartService.getListOfBook(1);

        assertEquals(2, result.size());
        verify(shoppingCartDAO, times(1)).findByUserId(1);
        verify(bookDAO, times(1)).findByIsbnIn(Arrays.asList("123-ABC", "456-DEF"));
    }

    @Test
    void testUpdateIsbn() {
        ShoppingCart cart = new ShoppingCart();
        Book book = new Book();
        book.setIsbn("123-OLD");
        cart.setBook(book);
        cart.setUserId(1);

        when(shoppingCartDAO.findByUserId(1)).thenReturn(Arrays.asList(cart));

        shoppingCartService.updateIsbn(1, "456-NEW");

        assertEquals("456-NEW", cart.getBook().getIsbn());
        verify(shoppingCartDAO, times(1)).findByUserId(1);
        verify(shoppingCartDAO, times(1)).saveAll(Arrays.asList(cart));
    }

    @Test
    void testUpdateIsbnWithEmptyCart() {
        when(shoppingCartDAO.findByUserId(1)).thenReturn(Arrays.asList());

        shoppingCartService.updateIsbn(1, "456-NEW");

        verify(shoppingCartDAO, times(1)).findByUserId(1);
        verify(shoppingCartDAO, never()).saveAll(any());
    }
}