package com.app.ServiceTest;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.Service.AuthorService;
import com.dao.AuthorDAO;
import com.dao.BookAuthorDAO;
import com.dao.BookDAO;
import com.model.Author;
import com.model.Book;
import com.model.BookAuthor;
 
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
 
    @InjectMocks
    private AuthorService authorService;
 
    @Mock
    private AuthorDAO authorDao;
 
    @Mock
    private BookDAO bookDao;
 
    @Mock
    private BookAuthorDAO bookAuthorDao;
 
    private Author author1;
    private Author author2;
 
    @BeforeEach
    void setUp() {
        author1 = new Author(1, "John", "Doe", "photo1.jpg");
        author2 = new Author(2, "Jane", "Smith", "photo2.jpg");
    }
 
    @Test
    void testGetAllAuthors() {
        when(authorDao.findAll()).thenReturn(Arrays.asList(author1, author2));
 
        List<Author> result = authorService.getAll();
 
        assertEquals(2, result.size());
        verify(authorDao, times(1)).findAll();
    }
 
    @Test
    void testFindById_AuthorExists() {
        when(authorDao.findById(1)).thenReturn(Optional.of(author1));
 
        Author result = authorService.findById(1);
 
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }
 
    @Test
    void testFindById_AuthorNotFound() {
        when(authorDao.findById(1)).thenReturn(Optional.empty());
 
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authorService.findById(1);
        });
 
        assertEquals("author not found with id: 1", exception.getMessage());
    }
 
    @Test
    void testAddAuthor() {
        authorService.addAuthor(author1);
 
        verify(authorDao, times(1)).save(author1);
    }
 
    @Test
    void testRemoveAuthor() {
        authorService.removeAuthor(1);
 
        verify(authorDao, times(1)).deleteById(1);
    }
 
    @Test
    void testUpdateAuthorFirstName() {
        when(authorDao.findById(1)).thenReturn(Optional.of(author1));
 
        String result = authorService.updateAuthorFirstName(1, "Michael");
 
        assertEquals("updated sucessfully", result);
        assertEquals("Michael", author1.getFirstName());
        verify(authorDao, times(1)).save(author1);
    }
 
    @Test
    void testUpdateAuthorLastName() {
        when(authorDao.findById(2)).thenReturn(Optional.of(author2));
 
        String result = authorService.updateAuthorLastName(2, "Johnson");
 
        assertEquals("updated sucessfully", result);
        assertEquals("Johnson", author2.getLastName());
        verify(authorDao, times(1)).save(author2);
    }
 
    @Test
    void testGetBooksByAuthor() {
        BookAuthor bookAuthor1 = new BookAuthor();
        bookAuthor1.setIsbn("123");
        bookAuthor1.setAuthorId(1);
 
        BookAuthor bookAuthor2 = new BookAuthor();
        bookAuthor2.setIsbn("456");
        bookAuthor2.setAuthorId(1);
 
        Book book1 = new Book();
        book1.setIsbn("123");
        book1.setTitle("Java Programming");
 
        Book book2 = new Book();
        book2.setIsbn("456");
        book2.setTitle("Spring Boot Guide");
 
        when(bookAuthorDao.findByAuthorId(1)).thenReturn(Arrays.asList(bookAuthor1, bookAuthor2));
        when(bookDao.findByIsbnIn(Arrays.asList("123", "456"))).thenReturn(Arrays.asList(book1, book2));
 
        List<Book> books = authorService.getBooksByAuthor(1);
 
        assertEquals(2, books.size());
        assertEquals("Java Programming", books.get(0).getTitle());
    }
}