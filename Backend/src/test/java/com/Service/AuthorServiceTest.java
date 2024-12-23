package com.Service;

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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorDAO authorDao;

    @Mock
    private BookDAO bookDao;

    @Mock
    private BookAuthorDAO bookauthorDao;

    private Author sampleAuthor;
    private Book sampleBook;
    private BookAuthor sampleBookAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize sample entities with necessary data
        sampleAuthor = new Author(1, "John", "Doe", null);

        sampleBook = new Book();
        sampleBook.setTitle("Sample Book");
        sampleBook.setIsbn("12345");

        sampleBookAuthor = new BookAuthor();
        sampleBookAuthor.setIsbn("12345");
    }

    @Test
    void testGetAllAuthors() {
        when(authorDao.findAll()).thenReturn(Arrays.asList(sampleAuthor));

        List<Author> authors = authorService.getAll();

        assertEquals(1, authors.size());
        assertEquals("John", authors.get(0).getFirstName());
        verify(authorDao, times(1)).findAll();
    }

    @Test
    void testFindAuthorById() {
        when(authorDao.findById(1)).thenReturn(Optional.of(sampleAuthor));

        Author author = authorService.findById(1);

        assertNotNull(author);
        assertEquals("John", author.getFirstName());
        verify(authorDao, times(1)).findById(1);
    }

    @Test
    void testFindAuthorById_NotFound() {
        when(authorDao.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> authorService.findById(2));

        assertEquals("author not found with id: 2", exception.getMessage());
        verify(authorDao, times(1)).findById(2);
    }

    @Test
    void testAddAuthor() {
        // No need for doNothing; directly call save
        authorService.addAuthor(sampleAuthor);

        verify(authorDao, times(1)).save(sampleAuthor);
    }

    @Test
    void testRemoveAuthor() {
        doNothing().when(authorDao).deleteById(1);

        authorService.removeAuthor(1);

        verify(authorDao, times(1)).deleteById(1);
    }

    @Test
    void testFindByFirstName() {
        when(authorDao.findByFirstName("John")).thenReturn(sampleAuthor);

        Author author = authorService.findByFirstName("John");

        assertNotNull(author);
        assertEquals("Doe", author.getLastName());
        verify(authorDao, times(1)).findByFirstName("John");
    }

    @Test
    void testFindByLastName() {
        when(authorDao.findByLastName("Doe")).thenReturn(sampleAuthor);

        Author author = authorService.findByLastName("Doe");

        assertNotNull(author);
        assertEquals("John", author.getFirstName());
        verify(authorDao, times(1)).findByLastName("Doe");
    }

    @Test
    void testUpdateAuthorFirstName() {
        when(authorDao.findById(1)).thenReturn(Optional.of(sampleAuthor));

        String response = authorService.updateAuthorFirstName(1, "Jane");

        assertEquals("updated sucessfully", response);
        verify(authorDao, times(1)).findById(1);
        verify(authorDao, times(1)).save(sampleAuthor);
        assertEquals("Jane", sampleAuthor.getFirstName());
    }

    @Test
    void testUpdateAuthorFirstName_NotFound() {
        when(authorDao.findById(2)).thenReturn(Optional.empty());

        String response = authorService.updateAuthorFirstName(2, "Jane");

        assertEquals("author withJane not found", response);
        verify(authorDao, times(1)).findById(2);
        verify(authorDao, times(0)).save(any());
    }

    @Test
    void testUpdateAuthorLastName() {
        when(authorDao.findById(1)).thenReturn(Optional.of(sampleAuthor));

        String response = authorService.updateAuthorLastName(1, "Smith");

        assertEquals("updated sucessfully", response);
        verify(authorDao, times(1)).findById(1);
        verify(authorDao, times(1)).save(sampleAuthor);
        assertEquals("Smith", sampleAuthor.getLastName());
    }

    @Test
    void testUpdateAuthorLastName_NotFound() {
        when(authorDao.findById(2)).thenReturn(Optional.empty());

        String response = authorService.updateAuthorLastName(2, "Smith");

        assertEquals("author with Smithnot found", response);
        verify(authorDao, times(1)).findById(2);
        verify(authorDao, times(0)).save(any());
    }

    @Test
    void testGetBooksByAuthor() {
        when(bookauthorDao.findByAuthorId(1)).thenReturn(Arrays.asList(sampleBookAuthor));
        when(bookDao.findByIsbnIn(Arrays.asList("12345"))).thenReturn(Arrays.asList(sampleBook));

        List<Book> books = authorService.getBooksByAuthor(1);

        assertEquals(1, books.size());
        assertEquals("Sample Book", books.get(0).getTitle());
        verify(bookauthorDao, times(1)).findByAuthorId(1);
        verify(bookDao, times(1)).findByIsbnIn(Arrays.asList("12345"));
    }
}
