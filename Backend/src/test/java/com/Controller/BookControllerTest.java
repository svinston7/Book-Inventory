package com.Controller;

import com.Service.BookService;
import com.exception.CustomException;
import com.exception.Response;
import com.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleBook = new Book();
        sampleBook.setIsbn("123-456");
        sampleBook.setTitle("Effective Java");
        sampleBook.setDescription("Java Best Practices");
        sampleBook.setCategoryId(1);
        sampleBook.setEdition("3rd Edition");
        sampleBook.setPublisherId(100);
    }

    @Test
    void testPostBook_Success() {
        when(bookService.findByIsbn("123-456")).thenReturn(null);
        when(bookService.findByTitle("Effective Java")).thenReturn(null);
        doNothing().when(bookService).addBook(sampleBook);

        ResponseEntity<Response> response = bookController.postBook(sampleBook);

        assertEquals(201, response.getStatusCodeValue());
        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("POSTSUCCESS", responseBody.getCode());
        assertEquals("Book added successfully", responseBody.getMessage());
        verify(bookService, times(1)).addBook(sampleBook);
    }

    @Test
    void testPostBook_Failure() {
        when(bookService.findByIsbn("123-456")).thenReturn(sampleBook);

        CustomException exception = assertThrows(CustomException.class, () -> {
            bookController.postBook(sampleBook);
        });

        assertEquals("ADDFAILS", exception.getCode());
        assertEquals("Book already exists", exception.getMessage());
        verify(bookService, never()).addBook(sampleBook);
    }

    @Test
    void testGetAllBooks() {
        when(bookService.getAll()).thenReturn(Collections.singletonList(sampleBook));

        ResponseEntity<?> response = bookController.getAllBooks();

        assertEquals(200, response.getStatusCodeValue());
        List<?> books = (List<?>) response.getBody();
        assertNotNull(books);
        assertEquals(1, books.size());
        verify(bookService, times(1)).getAll();
    }

    @Test
    void testGetByIsbn() {
        when(bookService.findByIsbn("123-456")).thenReturn(sampleBook);

        ResponseEntity<?> response = bookController.getByisbn("123-456");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).findByIsbn("123-456");
    }

    @Test
    void testGetByTitle() {
        when(bookService.findByTitle("Effective Java")).thenReturn(sampleBook);

        ResponseEntity<?> response = bookController.getByTitle("Effective Java");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).findByTitle("Effective Java");
    }

    @Test
    void testGetByPublisherId() {
        when(bookService.findByPublisherId(100)).thenReturn(Collections.singletonList(sampleBook));

        ResponseEntity<?> response = bookController.getByPublisherId(100);

        assertEquals(200, response.getStatusCodeValue());
        List<?> books = (List<?>) response.getBody();
        assertNotNull(books);
        assertEquals(1, books.size());
        verify(bookService, times(1)).findByPublisherId(100);
    }

    @Test
    void testUpdateBook() {
        when(bookService.findByIsbn("123-456")).thenReturn(sampleBook);
        doNothing().when(bookService).updateBook("123-456", sampleBook);

        ResponseEntity<?> response = bookController.putByIsbn("123-456", sampleBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).updateBook("123-456", sampleBook);
    }
}