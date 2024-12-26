package com.Controller;

import com.Service.BookConditionService;
import com.exception.CustomException;
import com.exception.Response;
import com.model.BookCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookConditionControllerTest {

    @InjectMocks
    private BookConditionController bookConditionController;

    @Mock
    private BookConditionService bookConditionService;

    private BookCondition sampleCondition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleCondition = new BookCondition(1, "Good", "Slightly used, minimal wear", 15.0);
    }

    @Test
    void testPostCondition_Success() {
        when(bookConditionService.getBookCondition(1)).thenReturn(null);
        doNothing().when(bookConditionService).addBookCondition(sampleCondition);

        ResponseEntity<Response> response = bookConditionController.postCondition(sampleCondition);

        assertEquals(201, response.getStatusCodeValue());
        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("POSTSUCCESS", responseBody.getCode());
        assertEquals("Book Condition added successfully", responseBody.getMessage());
        verify(bookConditionService, times(1)).addBookCondition(sampleCondition);
    }

    @Test
    void testPostCondition_Failure() {
        when(bookConditionService.getBookCondition(1)).thenReturn(sampleCondition);

        CustomException exception = assertThrows(CustomException.class, () -> {
            bookConditionController.postCondition(sampleCondition);
        });

        assertEquals("ADDFAILS", exception.getCode());
        assertEquals("Book condition already exists", exception.getMessage());
        verify(bookConditionService, never()).addBookCondition(sampleCondition);
    }

    @Test
    void testGetCondition() {
        when(bookConditionService.getBookCondition(1)).thenReturn(sampleCondition);

        ResponseEntity<?> response = bookConditionController.getCondition(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleCondition, response.getBody());
        verify(bookConditionService, times(1)).getBookCondition(1);
    }

    @Test
    void testUpdatePrice() {
        doNothing().when(bookConditionService).updatePrice(1, 20.0);

        ResponseEntity<?> response = bookConditionController.updatePrice(1, 20.0);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(bookConditionService, times(1)).updatePrice(1, 20.0);
    }

    @Test
    void testUpdateDescription() {
        doNothing().when(bookConditionService).updateDescription(1, "Excellent");

        ResponseEntity<?> response = bookConditionController.updateDescription(1, "Excellent");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(bookConditionService, times(1)).updateDescription(1, "Excellent");
    }

    @Test
    void testUpdateFullDescription() {
        doNothing().when(bookConditionService).updateFullDescription(1, "Like new with no wear");

        ResponseEntity<?> response = bookConditionController.updateFull(1, "Like new with no wear");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(bookConditionService, times(1)).updateFullDescription(1, "Like new with no wear");
    }
}
