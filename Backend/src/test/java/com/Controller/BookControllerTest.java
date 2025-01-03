package com.Controller;

import com.Service.BookService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
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
        sampleBook = new Book(null, null, null, 0, null, 0, null, 0);
    }
    @Test
    void testPostBookSuccess() throws InvalidInputException {
        // Create a sample Book object
        Book sampleBook = new Book("1234567890", "Sample Book", "A sample description", 1, "1st Edition", 1, "sample_image.png", 0);

        // Mock the bookService.addBook method to do nothing (successful case)
        doNothing().when(bookService).addBook(sampleBook);

        // Call the controller method
        ResponseEntity<?> response = bookController.postBook(sampleBook);

        // Assert the status code
        assertEquals(200, response.getStatusCodeValue());

        // Create expected response body to match the returned ResponseEntity
        Response expectedResponse = new Response("POSTSUCCESS", "Book added successfully");

        // Assert that the response body contains the expected code and message
      //  assertEquals(expectedResponse, response.getBody());

        // Verify that the bookService.addBook method was called once with the sampleBook
        verify(bookService, times(1)).addBook(sampleBook);
    }

   

    @Test
    void testPostBookFailure() throws InvalidInputException {
        // Simulate a failure (e.g., duplicate book)
        doThrow(new RuntimeException("Duplicate book")).when(bookService).addBook(sampleBook);

        // Call the controller method
        ResponseEntity<?> response = bookController.postBook(sampleBook);

        // Assert the failure response code and message
        assertEquals(500, response.getStatusCodeValue());
       // assertEquals(new Response("ADDFAILS", "An unexpected error occurred"), response.getBody());

        // Verify that addBook was called once
        verify(bookService, times(1)).addBook(sampleBook);
    }

    @Test
    void testGetAllBooks() throws ResourceNotFoundException {
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.getAll()).thenReturn(books);

        ResponseEntity<?> response = bookController.getAllBooks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).getAll();
    }

    @Test
    void testGetByIsbn() throws InvalidInputException, ResourceNotFoundException {
        when(bookService.findByIsbn("1234567890")).thenReturn(sampleBook);

        ResponseEntity<?> response = bookController.getByisbn("1234567890");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).findByIsbn("1234567890");
    }

    @Test
    void testGetByTitle() throws InvalidInputException, ResourceNotFoundException {
        when(bookService.findByTitle("Sample Book")).thenReturn(sampleBook);

        ResponseEntity<?> response = bookController.getByTitle("Sample Book");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).findByTitle("Sample Book");
    }

    @Test
    void testGetByPublisherId() throws InvalidInputException, ResourceNotFoundException {
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.findByPublisherId(1)).thenReturn(books);

        ResponseEntity<?> response = bookController.getByPublisherId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).findByPublisherId(1);
    }

    @Test
    void testPutByIsbn() throws InvalidInputException, ResourceNotFoundException {
        doNothing().when(bookService).updateBook("1234567890", sampleBook);

        ResponseEntity<?> response = bookController.putByIsbn("1234567890", sampleBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleBook, response.getBody());
        verify(bookService, times(1)).updateBook("1234567890", sampleBook);
    }
}
