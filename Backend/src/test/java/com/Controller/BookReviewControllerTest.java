package com.Controller;

import com.Service.BookReviewService;
import com.exception.CustomException;
import com.exception.Response;
import com.model.BookReview;
import com.model.Reviewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookReviewControllerTest {

    @Mock
    private BookReviewService bookReviewService;

    @InjectMocks
    private BookReviewController bookReviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostReview_Success() {
        BookReview review = new BookReview();
        review.setIsbn("12345");
        review.setRating(5);
        review.setComments("Excellent book");

        when(bookReviewService.findByisbn("12345")).thenReturn(null);

        ResponseEntity<?> response = bookReviewController.postReview(review);

        assertEquals(200, response.getStatusCodeValue());
        Response responseBody = (Response) response.getBody();
        assertNotNull(responseBody);
        assertEquals("POSTSUCCESS", responseBody.getCode());
        assertEquals("Book Reviewer added successfully", responseBody.getMessage());
        verify(bookReviewService, times(1)).addBookReview(review);
    }

    @Test
    void testPostReview_AlreadyExists() {
        BookReview review = new BookReview();
        review.setIsbn("12345");

        when(bookReviewService.findByisbn("12345")).thenReturn(review);

        CustomException exception = assertThrows(CustomException.class,
                () -> bookReviewController.postReview(review));

        assertEquals("ADDFAILS", exception.getCode());
        assertEquals("Book Reviewer already exists", exception.getMessage());
        verify(bookReviewService, never()).addBookReview(review);
    }

    @Test
    void testGetAllReviewers_Success() {
        Reviewer reviewer1 = new Reviewer(1, "John Doe", "ABC Corp");
        Reviewer reviewer2 = new Reviewer(2, "Jane Smith", "XYZ Corp");
        List<Reviewer> reviewers = Arrays.asList(reviewer1, reviewer2);

        when(bookReviewService.getAllReviewers("12345")).thenReturn(reviewers);

        ResponseEntity<?> response = bookReviewController.getAllReviewers("12345");

        assertEquals(200, response.getStatusCodeValue());
        List<?> responseBody = (List<?>) response.getBody();
        assertEquals(2, responseBody.size());
        verify(bookReviewService, times(1)).getAllReviewers("12345");
    }

    @Test
    void testUpdateRating_Success() {
        BookReview review = new BookReview();
        review.setIsbn("12345");
        review.setRating(4);

        when(bookReviewService.findByisbn("12345")).thenReturn(review);

        ResponseEntity<?> response = bookReviewController.updateRating("12345", 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        assertEquals(5, review.getRating());
        verify(bookReviewService, times(1)).addBookReview(review);
    }

    @Test
    void testUpdateComment_Success() {
        BookReview review = new BookReview();
        review.setIsbn("12345");
        review.setComments("Good book");

        when(bookReviewService.findByisbn("12345")).thenReturn(review);

        ResponseEntity<?> response = bookReviewController.updateComment("12345", "Excellent read");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        assertEquals("Excellent read", review.getComments());
        verify(bookReviewService, times(1)).addBookReview(review);
    }

    @Test
    void testUpdateRating_NotFound() {
        when(bookReviewService.findByisbn("99999")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> bookReviewController.updateRating("99999", 5));

        assertEquals("Book not found with isbn: 99999", exception.getMessage());
        verify(bookReviewService, never()).addBookReview(any());
    }
}
