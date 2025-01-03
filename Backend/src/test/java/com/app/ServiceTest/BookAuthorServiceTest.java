package com.app.ServiceTest;
 
import com.Service.BookAuthorService;
import com.dao.AuthorDAO;
import com.dao.BookAuthorDAO;
import com.dao.BookDAO;
import com.model.Author;
import com.model.Book;
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
    @Mock
    private AuthorDAO AuthorDao;
    
    @Mock
    private BookDAO bookDao;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookAuthor = new BookAuthor();
        bookAuthor.setId(1);
        bookAuthor.setIsbn("12345");
        bookAuthor.setAuthorId(101);
        bookAuthor.setPrimaryAuthor(true);
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
        assertEquals(true, bookAuthors.get(0).getPrimaryAuthor());
    }
 
    @Test
    public void testFindById_Valid() {
        // Arrange
        when(bookAuthorDAO.findById(1)).thenReturn(Optional.of(bookAuthor));
 
        // Act
        var result = bookAuthorService.findById(1);
 
        // Assert
        assertNotNull(result);
        assertEquals(true, result.getPrimaryAuthor());
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
    
    @Test
    public void testGetAuthorDetailsByIsbn() {
        // Arrange
        String isbn = "12345";
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setIsbn(isbn);
        bookAuthor.setAuthorId(101);
        when(bookAuthorDAO.findByIsbn(isbn)).thenReturn(List.of(bookAuthor));
        Author author = new Author(101, "John", "Doe", "photo1.jpg");
        when(AuthorDao.findById(101)).thenReturn(Optional.of(author));

        // Act
        List<Author> authors = bookAuthorService.getAuthorDetailsByIsbn(isbn);

        // Assert
        assertNotNull(authors);
        assertEquals(1, authors.size());
        assertEquals("John", authors.get(0).getFirstName());
    }
    
    @Test
    public void testGetBookDetailsById() {
        // Arrange
        int authorId = 1;
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setIsbn("12345");
        bookAuthor.setAuthorId(authorId);
        when(bookAuthorDAO.findByAuthorId(authorId)).thenReturn(List.of(bookAuthor));
        Book book = new Book();
        book.setIsbn("12345");
        book.setTitle("Sample Book");
        when(bookDao.findByIsbnIn(List.of("12345"))).thenReturn(List.of(book));

        // Act
        List<Book> books = bookAuthorService.getBookDetailsById(authorId);

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Sample Book", books.get(0).getTitle());
    }

}