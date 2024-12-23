package com.Controller;

import com.Service.BookReviewService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookReviewControllerTest {

    @InjectMocks
    private BookReviewController bookReviewController;

    @Mock
    private BookReviewService bookReviewService;

    private BookReview sampleReview;
    private Reviewer sampleReviewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleReview = new BookReview();
        sampleReviewer = new Reviewer(0, "John Doe", "john@example.com");
    }

    @Test
    void testPostReview() {
        doNothing().when(bookReviewService).addBookReview(sampleReview);

        ResponseEntity<?> response = bookReviewController.postReview(sampleReview);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Success", response.getBody());
        verify(bookReviewService, times(1)).addBookReview(sampleReview);
    }

    @Test
    void testGetAllReviewers() {
        List<Reviewer> reviewers = Arrays.asList(sampleReviewer);
        when(bookReviewService.getAllReviewers("1234567890")).thenReturn(reviewers);

        ResponseEntity<?> response = bookReviewController.getAllReviewers("1234567890");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reviewers, response.getBody());
        verify(bookReviewService, times(1)).getAllReviewers("1234567890");
    }

    @Test
    void testUpdateRating() {
        when(bookReviewService.findByisbn("1234567890")).thenReturn(sampleReview);
        doNothing().when(bookReviewService).addBookReview(any(BookReview.class));

        ResponseEntity<?> response = bookReviewController.updateRating("1234567890", 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(bookReviewService, times(1)).findByisbn("1234567890");
        verify(bookReviewService, times(1)).addBookReview(sampleReview);
        assertEquals(5, sampleReview.getRating());
    }

    @Test
    void testUpdateComment() {
        when(bookReviewService.findByisbn("1234567890")).thenReturn(sampleReview);
        doNothing().when(bookReviewService).addBookReview(any(BookReview.class));

        ResponseEntity<?> response = bookReviewController.updateComment("1234567890", "Amazing book!");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(bookReviewService, times(1)).findByisbn("1234567890");
        verify(bookReviewService, times(1)).addBookReview(sampleReview);
        assertEquals("Amazing book!", sampleReview.getComments());
    }
}
