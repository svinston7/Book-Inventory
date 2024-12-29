package com.Controller;

import com.Controller.ReviewerController;
import com.Service.ReviewerService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.Reviewer;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void testPostReviewer() throws InvalidInputException {
        // Arrange
        Reviewer reviewer = new Reviewer(1, "John Doe", "ABC Corp");
        doNothing().when(reviewerService).addReviewer(reviewer); 
        // Act
        ResponseEntity<?> response = reviewerController.postReviewer(reviewer);

        // Assert the status code
        assertEquals(200, response.getStatusCodeValue(), "Status code should be 200");

        // Assert the response body contains the expected success message
//        assertEquals("Reviewer added successfully", response.getBody(), "Message should be 'Reviewer added successfully'");

        // Verify that the reviewerService.addReviewer method was called exactly once
        verify(reviewerService, times(1)).addReviewer(reviewer);
    }


    @Test
    void testGetReviewer() throws InvalidInputException, ResourceNotFoundException {
        Reviewer reviewer = new Reviewer(1, "John Doe", "ABC Corp");

        when(reviewerService.findById(1)).thenReturn(reviewer);

        ResponseEntity<?> response = reviewerController.getReviewer(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reviewer, response.getBody());
        verify(reviewerService, times(1)).findById(1);
    }

    @Test
    void testUpdateName() throws InvalidInputException, ResourceNotFoundException {
        when(reviewerService.updatereviewerFirstName(1, "Jane Doe"))
                .thenReturn("updated successfully");

        ResponseEntity<?> response = reviewerController.updateName(1, "Jane Doe");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("updated successfully", response.getBody());
        verify(reviewerService, times(1)).updatereviewerFirstName(1, "Jane Doe");
    }

    @Test
    void testUpdateEmployedBy() throws ResourceNotFoundException, InvalidInputException {
        when(reviewerService.updateReviewerEmployedId(1, "New Corp"))
                .thenReturn("updated successfully");

        ResponseEntity<?> response = reviewerController.updateEmployedBy(1, "New Corp");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("updated successfully", response.getBody());
        verify(reviewerService, times(1)).updateReviewerEmployedId(1, "New Corp");
    }
}
