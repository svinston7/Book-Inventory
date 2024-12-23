package com.app.ServiceTest;
 
import com.Service.BookAuthorService;
import com.dao.BookAuthorDAO;
import com.model.BookAuthor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
public class BookAuthorServiceTest {
 
    @Mock
    private BookAuthorDAO bookAuthorDAO;
 
    @InjectMocks
    private BookAuthorService bookAuthorService;
 
    private BookAuthor bookAuthor;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookAuthor = new BookAuthor();
        bookAuthor.setId(1);
        bookAuthor.setIsbn("12345");
        bookAuthor.setAuthorId(101);
        bookAuthor.setPrimaryAuthor("John Doe");
    }
 
    @Test
    public void testGetAllBookAuthors() {
        // Arrange
        when(bookAuthorDAO.findAll()).thenReturn(List.of(bookAuthor));
 
        // Act
        var bookAuthors = bookAuthorService.getAll();
 
        // Assert
        assertNotNull(bookAuthors);
        assertEquals(1, bookAuthors.size());
        assertEquals("John Doe", bookAuthors.get(0).getPrimaryAuthor());
    }
 
    @Test
    public void testFindById_Valid() {
        // Arrange
        when(bookAuthorDAO.findById(1)).thenReturn(Optional.of(bookAuthor));
 
        // Act
        var result = bookAuthorService.findById(1);
 
        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getPrimaryAuthor());
    }
 
    @Test
    public void testFindById_Invalid() {
        // Arrange
        when(bookAuthorDAO.findById(1)).thenReturn(Optional.empty());
 
        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookAuthorService.findById(1);
        });
        assertEquals("Bookauthor not found with id: 1", exception.getMessage());
    }
 
    @Test
    public void testAddBookAuthor() {
        // Arrange
        when(bookAuthorDAO.save(bookAuthor)).thenReturn(bookAuthor);
 
        // Act
        bookAuthorService.addBookAuthor(bookAuthor);
 
        // Assert
        verify(bookAuthorDAO, times(1)).save(bookAuthor);
    }
 
    @Test
    public void testRemoveBookAuthor() {
        // Arrange
        when(bookAuthorDAO.existsById(1)).thenReturn(true);
 
        // Act
        bookAuthorService.removeBookAuthor(1);
 
        // Assert
        verify(bookAuthorDAO, times(1)).deleteById(1);
    }
}