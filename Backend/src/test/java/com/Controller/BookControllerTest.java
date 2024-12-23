package com.Controller;

import com.Service.BookService;
import com.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    }

    @Test
    void testPostBookSuccess() {
        doNothing().when(bookService).addBook(sampleBook);

        ResponseEntity<?> response = bookController.postBook(sampleBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("code", "POSTSUCCESS", "message", "Book added successfully"), response.getBody());
        verify(bookService, times(1)).addBook(sampleBook);
    }

    @Test
    void testPostBookFailure() {
        doThrow(new RuntimeException("Duplicate book")).when(bookService).addBook(sampleBook);

        ResponseEntity<?> response = bookController.postBook(sampleBook);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(Map.of("code", "ADDFAILS", "message", "Book already exist"), response.getBody());
        verify(bookService, times(1)).addBook(sampleBook);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.getAll()).thenReturn(books);

        ResponseEntity<?> response = bookController.getAllBooks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).getAll();
    }

    @Test
    void testGetByIsbn() {
        when(bookService.findByIsbn("1234567890")).thenReturn(sampleBook);

        ResponseEntity<?> response = bookController.getByisbn("1234567890");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).findByIsbn("1234567890");
    }

    @Test
    void testGetByTitle() {
        when(bookService.findByTitle("Sample Book")).thenReturn(sampleBook);

        ResponseEntity<?> response = bookController.getByTitle("Sample Book");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).findByTitle("Sample Book");
    }

    @Test
    void testGetByPublisherId() {
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.findByPublisherId(1)).thenReturn(books);

        ResponseEntity<?> response = bookController.getByPublisherId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).findByPublisherId(1);
    }

    @Test
    void testPutByIsbn() {
        doNothing().when(bookService).updateBook("1234567890", sampleBook);

        ResponseEntity<?> response = bookController.putByIsbn("1234567890", sampleBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).updateBook("1234567890", sampleBook);
    }
}
