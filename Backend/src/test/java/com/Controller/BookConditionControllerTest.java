package com.Controller;
 
import com.Service.BookConditionService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.BookCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 
class BookConditionControllerTest {
 
    @InjectMocks
    private BookConditionController bookConditionController;
 
    @Mock
    private BookConditionService conditionService;
 
    private BookCondition sampleCondition;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        // Sample data for testing
        sampleCondition = new BookCondition(1, "Good", "Full description of condition", 12.99);
    }
 
    @Test
    void testPostCondition() throws InvalidInputException {
        doNothing().when(conditionService).addBookCondition(sampleCondition);
 
        ResponseEntity<?> response = bookConditionController.postCondtion(sampleCondition);
 
        assertEquals(200, response.getStatusCodeValue());
        //assertEquals(sampleCondition, response.getBody());
        verify(conditionService, times(1)).addBookCondition(sampleCondition);
    }
 
    @Test
    void testGetCondition() throws InvalidInputException {
        when(conditionService.getBookCondition(1)).thenReturn(sampleCondition);
 
        ResponseEntity<?> response = bookConditionController.getCondition(1);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleCondition, response.getBody());
        verify(conditionService, times(1)).getBookCondition(1);
    }
 
    @Test
    void testUpdatePrice() throws InvalidInputException, ResourceNotFoundException {
        doNothing().when(conditionService).updatePrice(1, 15.99);
 
        ResponseEntity<?> response = bookConditionController.updatePrice(1, 15.99);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(conditionService, times(1)).updatePrice(1, 15.99);
    }
 
    @Test
    void testUpdateDescription() throws InvalidInputException, ResourceNotFoundException {
        doNothing().when(conditionService).updateDescription(1, "Updated description");
 
        ResponseEntity<?> response = bookConditionController.updateDescription(1, "Updated description");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(conditionService, times(1)).updateDescription(1, "Updated description");
    }
 
    @Test
    void testUpdateFullDescription() throws InvalidInputException, ResourceNotFoundException {
        doNothing().when(conditionService).updateFullDescription(1, "Updated full description");
 
        ResponseEntity<?> response = bookConditionController.updateFull(1, "Updated full description");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
        verify(conditionService, times(1)).updateFullDescription(1, "Updated full description");
    }
}