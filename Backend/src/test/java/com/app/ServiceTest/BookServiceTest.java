package com.app.ServiceTest;

import com.Service.BookService;
import com.dao.BookDAO;
import com.model.Book;
import com.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookDAO bookDao;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up Publisher
        publisher = new Publisher();
        publisher.setPublisherId(1);
        publisher.setName("Test Publisher");
        publisher.setCity("Test City");
        publisher.setStateCode("TC");

        // Set up Book
        book = new Book();
        book.setIsbn("12345");
        book.setTitle("Test Book");
        book.setDescription("This is a test book");
        book.setCategoryId(1);
        book.setEdition("First");
        book.setPublisherId(1);  // Here we use publisherId directly as in your original code.
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        when(bookDao.findAll()).thenReturn(List.of(book));

        // Act
        List<Book> books = bookService.getAll();

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    public void testFindByIsbn_Valid() {
        // Arrange
        when(bookDao.findById("12345")).thenReturn(Optional.of(book));

        // Act
        Book result = bookService.findByIsbn("12345");

        // Assert
        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
    }

    @Test
    public void testFindByIsbn_Invalid() {
        // Arrange
        when(bookDao.findById("12345")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.findByIsbn("12345");
        });
        assertEquals("Book not found with isbn: 12345", exception.getMessage());
    }

    @Test
    public void testAddBook() {
        // Arrange
        when(bookDao.save(book)).thenReturn(book);

        // Act
        bookService.addBook(book);

        // Assert
        verify(bookDao, times(1)).save(book);
    }

    @Test
    public void testUpdateBook() {
        // Arrange
        Book updatedBook = new Book();
        updatedBook.setCategoryId(2);
        updatedBook.setEdition("Second");
        updatedBook.setTitle("Updated Book");
        updatedBook.setPublisherId(2);  // Use a new publisherId to simulate an update

        when(bookDao.findById("12345")).thenReturn(Optional.of(book));

        // Act
        bookService.updateBook("12345", updatedBook);

        // Assert
        assertEquals("Updated Book", book.getTitle());
        assertEquals(2, book.getCategoryId());
        assertEquals(2, book.getPublisherId());  // Ensure that publisherId is updated
    }

    @Test
    public void testRemoveBook() {
        // Arrange
        when(bookDao.existsById("12345")).thenReturn(true);

        // Act
        bookService.removeBook("12345");

        // Assert
        verify(bookDao, times(1)).deleteById("12345");
    }

    @Test
    public void testFindByTitle() {
        // Arrange
        when(bookDao.findByTitle("Test Book")).thenReturn(book);

        // Act
        Book result = bookService.findByTitle("Test Book");

        // Assert
        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
    }

    @Test
    public void testFindByPublisherId() {
        // Arrange
        when(bookDao.findByPublisherId(1)).thenReturn(List.of(book));

        // Act
        List<Book> result = bookService.findByPublisherId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getPublisherId());  // Verify publisherId in the result
    }
    
    @Test
    public void testGetAllBooks_EmptyList() {
        // Arrange
        when(bookDao.findAll()).thenReturn(List.of());

        // Act
        List<Book> books = bookService.getAll();

        // Assert
        assertNotNull(books);
        assertTrue(books.isEmpty(), "The list should be empty when no books are found");
    }

}
