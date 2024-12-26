package com.Controller;

import com.Service.ReviewerService;
import com.model.Reviewer;
import com.exception.CustomException;
import com.exception.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

class ReviewerControllerTest {

    @Mock
    private ReviewerService reviewerService;

    @InjectMocks
    private ReviewerController reviewerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostReviewer_Success() {
        Reviewer reviewer = new Reviewer(1, "John Doe", "ABC Corp");

        when(reviewerService.findById(1)).thenReturn(null);  // Simulate reviewer not existing
        doNothing().when(reviewerService).addReviewer(reviewer);

        ResponseEntity<?> response = reviewerController.postReviewer(reviewer);

        assertEquals(201, response.getStatusCodeValue());  // 201 for CREATED
        assertInstanceOf(Response.class, response.getBody());  // Assert as Response, not Map
        
        Response body = (Response) response.getBody();
        assertEquals("POSTSUCCESS", body.getCode());
        assertEquals("Reviewer added successfully", body.getMessage());
        
        verify(reviewerService, times(1)).addReviewer(reviewer);
    }



    @Test
    void testPostReviewer_AlreadyExists() {
        Reviewer reviewer = new Reviewer(1, "John Doe", "ABC Corp");

        when(reviewerService.findById(1)).thenReturn(reviewer);

        CustomException exception = assertThrows(CustomException.class, () -> {
            reviewerController.postReviewer(reviewer);
        });

        assertEquals("ADDFAILS", exception.getCode());
        assertEquals("Reviewer already exists", exception.getMessage());
        verify(reviewerService, never()).addReviewer(any());
    }

    @Test
    void testGetReviewer() {
        Reviewer reviewer = new Reviewer(1, "John Doe", "ABC Corp");

        when(reviewerService.findById(1)).thenReturn(reviewer);

        ResponseEntity<?> response = reviewerController.getReviewer(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reviewer, response.getBody());
        verify(reviewerService, times(1)).findById(1);
    }

    @Test
    void testUpdateName() {
        when(reviewerService.updatereviewerFirstName(1, "Jane Doe"))
                .thenReturn("updated successfully");

        ResponseEntity<?> response = reviewerController.updateName(1, "Jane Doe");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("updated successfully", response.getBody());
        verify(reviewerService, times(1)).updatereviewerFirstName(1, "Jane Doe");
    }

    @Test
    void testUpdateEmployedBy() {
        when(reviewerService.updateReviewerEmployedId(1, "New Corp"))
                .thenReturn("updated successfully");

        ResponseEntity<?> response = reviewerController.updateEmployedBy(1, "New Corp");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("updated successfully", response.getBody());
        verify(reviewerService, times(1)).updateReviewerEmployedId(1, "New Corp");
    }
}
