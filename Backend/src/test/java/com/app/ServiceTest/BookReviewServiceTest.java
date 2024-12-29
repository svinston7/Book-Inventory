package com.app.ServiceTest;


import com.Service.BookReviewService;
import com.dao.BookReviewDAO;
import com.model.BookReview;
import com.model.Reviewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
public class BookReviewServiceTest {
 
    @Mock
    private BookReviewDAO bookReviewDAO;
 
    @InjectMocks
    private BookReviewService bookReviewService;
 
    private BookReview bookReview;
    private Reviewer reviewer;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
 
        // Initialize BookReview object
        bookReview = new BookReview();
        bookReview.setId(1);
        bookReview.setIsbn("12345");
        bookReview.setRating(5);
        bookReview.setComments("Great book!");
 
        // Initialize Reviewer object
        reviewer = new Reviewer();
        reviewer.setReviewerId(1);
        reviewer.setName("John Doe");
        reviewer.setEmployedBy("Company XYZ");
 
         // Assuming BookReview has a list of reviewers
    }
 
    @Test
    public void testGetAllBookReviews() {
        // Arrange
        when(bookReviewDAO.findAll()).thenReturn(List.of(bookReview));
 
        // Act
        var bookReviews = bookReviewService.getAll();
 
        // Assert
        assertNotNull(bookReviews);
        assertEquals(1, bookReviews.size());
        assertEquals("12345", bookReviews.get(0).getIsbn());
    }
 
    @Test
    public void testFindByIsbn_Valid() {
        // Arrange
        when(bookReviewDAO.findByIsbn("12345")).thenReturn(bookReview);
 
        // Act
        var result = bookReviewService.findByisbn("12345");
 
        // Assert
        assertNotNull(result);
        assertEquals("12345", result.getIsbn());
    }
 
    @Test
    public void testFindByIsbn_Invalid() {
        // Arrange
        when(bookReviewDAO.findByIsbn("12345")).thenReturn(null);
 
        // Act
        var result = bookReviewService.findByisbn("12345");
 
        // Assert
        assertNull(result);
    }
 
    @Test
    public void testGetAllReviewers() {
        // Arrange
        when(bookReviewDAO.findReviewsByIsbn("12345")).thenReturn(List.of(bookReview));

        // Act
        var reviewersList = bookReviewService.getAllReviewers("12345");

        // Assert
        assertNotNull(reviewersList, "Reviewers list should not be null");
        assertEquals(1, reviewersList.size(), "Reviewers list size should match");
        assertEquals("12345", reviewersList.get(0).getIsbn(), "Book ISBN should match");
    }
 
    @Test
    public void testAddBookReview() {
        // Arrange
        when(bookReviewDAO.save(bookReview)).thenReturn(bookReview);
 
        // Act
        bookReviewService.addBookReview(bookReview);
 
        // Assert
        verify(bookReviewDAO, times(1)).save(bookReview);
    }
}
 
