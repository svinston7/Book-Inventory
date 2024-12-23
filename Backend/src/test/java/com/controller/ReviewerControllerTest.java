package com.Controller;

import com.Controller.ReviewerController;
import com.Service.ReviewerService;
import com.model.Reviewer;
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
    void testPostReviewer() {
        Reviewer reviewer = new Reviewer(1, "John Doe", "ABC Corp");

        doNothing().when(reviewerService).addReviewer(reviewer);

        ResponseEntity<?> response = reviewerController.postReviewer(reviewer);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Map);
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("POSTSUCCESS", body.get("code"));
        assertEquals("Reviewer added successfully", body.get("message"));
        verify(reviewerService, times(1)).addReviewer(reviewer);
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
