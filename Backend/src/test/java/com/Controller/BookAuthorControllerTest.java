package com.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Service.BookAuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Author;
import com.model.Book;
import com.model.BookAuthor;

import java.util.Arrays;
import java.util.List;

class BookAuthorControllerTest {

    @Mock
    private BookAuthorService bookAuthorService;

    @InjectMocks
    private BookAuthorController bookAuthorController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookAuthorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllBookAuthors() throws Exception {
        BookAuthor bookAuthor1 = new BookAuthor();
        bookAuthor1.setId(1);
        bookAuthor1.setIsbn("1234");
        bookAuthor1.setAuthorId(101);
        bookAuthor1.setPrimaryAuthor(true);

        BookAuthor bookAuthor2 = new BookAuthor();
        bookAuthor2.setId(2);
        bookAuthor2.setIsbn("5678");
        bookAuthor2.setAuthorId(102);
        bookAuthor2.setPrimaryAuthor(false);

        List<BookAuthor> bookAuthors = Arrays.asList(bookAuthor1, bookAuthor2);
        when(bookAuthorService.getAll()).thenReturn(bookAuthors);

        mockMvc.perform(get("/api/bookauthors/getallbookauthors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(bookAuthorService, times(1)).getAll();
    }

    @Test
    void testGetAuthorDetailsByIsbn() throws Exception {
        Author author = new Author(101, "John", "Doe", "image.jpg");
        List<Author> authors = Arrays.asList(author);
        when(bookAuthorService.getAuthorDetailsByIsbn("1234")).thenReturn(authors);

        mockMvc.perform(get("/api/bookauthors/isbn/1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));

        verify(bookAuthorService, times(1)).getAuthorDetailsByIsbn("1234");
    }

    @Test
    void testGetBookAuthorById() throws Exception {
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setId(1);
        bookAuthor.setIsbn("1234");
        bookAuthor.setAuthorId(101);
        bookAuthor.setPrimaryAuthor(true);

        when(bookAuthorService.findById(1)).thenReturn(bookAuthor);

        mockMvc.perform(get("/api/bookauthors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.isbn").value("1234"))
                .andExpect(jsonPath("$.primaryAuthor").value(true));

        verify(bookAuthorService, times(1)).findById(1);
    }

    @Test
    void testGetAuthorById() throws Exception {
        Book book = new Book("1234", "Test Book", "Description", 1, "First Edition", 1, "image.jpg");
        List<Book> books = Arrays.asList(book);
        when(bookAuthorService.getBookDetailsById(101)).thenReturn(books);

        mockMvc.perform(get("/api/bookauthors/authorid/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("1234"))
                .andExpect(jsonPath("$[0].title").value("Test Book"));

        verify(bookAuthorService, times(1)).getBookDetailsById(101);
    }

    @Test
    void testAddBookAuthor() throws Exception {
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setIsbn("1234");
        bookAuthor.setAuthorId(101);
        bookAuthor.setPrimaryAuthor(true);
        
        doNothing().when(bookAuthorService).addBookAuthor(bookAuthor);

        mockMvc.perform(post("/api/bookauthors/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookAuthor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("POSTSUCCESS"))
                .andExpect(jsonPath("$.message").value("BookAuhtor added successfully"));

    }

    @Test
    void testDeleteBookAuthor() throws Exception {
        doNothing().when(bookAuthorService).removeBookAuthor(1);

        mockMvc.perform(delete("/api/bookauthors/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book author deleted successfully"));

        verify(bookAuthorService, times(1)).removeBookAuthor(1);
    }

   

    

}
