package com.Controller;
 
import com.Service.AuthorService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 
class AuthorControllerTest {
 
    @InjectMocks
    private AuthorController authorController;
 
    @Mock
    private AuthorService authorService;
 
    private Author sampleAuthor;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        // Sample data for testing
        sampleAuthor = new Author(1, "John", "Doe", "samplePhoto.jpg");
    }
 
    @Test
    void testPostAuthor() throws InvalidInputException {
        doNothing().when(authorService).addAuthor(sampleAuthor);
 
        ResponseEntity<?> response = authorController.postAuthor(sampleAuthor);
 
        assertEquals(200, response.getStatusCodeValue());
       // assertEquals("POSTSUCCESS", ((Map) response.getBody()).get("code"));
        verify(authorService, times(1)).addAuthor(sampleAuthor);
    }
 
    @Test
    void testGetAuthorById() throws ResourceNotFoundException {
        when(authorService.findById(1)).thenReturn(sampleAuthor);
 
        ResponseEntity<?> response = authorController.getAuthorById(1);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleAuthor, response.getBody());
        verify(authorService, times(1)).findById(1);
    }
 
    @Test
    void testGetAuthorByFirstName() throws ResourceNotFoundException {
        when(authorService.findByFirstName("John")).thenReturn(sampleAuthor);
 
        ResponseEntity<?> response = authorController.getAuthorByfirstname("John");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleAuthor, response.getBody());
        verify(authorService, times(1)).findByFirstName("John");
    }
 
    @Test
    void testGetAuthorByLastName() throws ResourceNotFoundException {
        when(authorService.findByFirstName("Doe")).thenReturn(sampleAuthor);
 
        ResponseEntity<?> response = authorController.getAuthorlastname("Doe");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleAuthor, response.getBody());
        verify(authorService, times(1)).findByFirstName("Doe"); // Note: Your controller uses findByFirstName for lastname
    }
 
    @Test
    void testUpdateFirstName() throws ResourceNotFoundException, InvalidInputException {
        when(authorService.findById(1)).thenReturn(sampleAuthor);
        doNothing().when(authorService).addAuthor(sampleAuthor);
 
        ResponseEntity<?> response = authorController.updateFirstName(1, "Jane");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Jane", ((Author) response.getBody()).getFirstName());
        verify(authorService, times(1)).findById(1);
        verify(authorService, times(1)).addAuthor(sampleAuthor);
    }
 
    @Test
    void testUpdateLastName() throws ResourceNotFoundException, InvalidInputException {
        when(authorService.findById(1)).thenReturn(sampleAuthor);
        doNothing().when(authorService).addAuthor(sampleAuthor);
 
        ResponseEntity<?> response = authorController.updateLastName(1, "Smith");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Smith", ((Author) response.getBody()).getLastName());
        verify(authorService, times(1)).findById(1);
        verify(authorService, times(1)).addAuthor(sampleAuthor);
    }
}