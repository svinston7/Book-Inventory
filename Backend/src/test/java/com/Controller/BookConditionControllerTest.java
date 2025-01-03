package com.Controller;
 
import com.Service.BookConditionService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.BookCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
class BookConditionControllerTest {
 
    @InjectMocks
    private BookConditionController bookConditionController;
 
    @Mock
    private BookConditionService conditionService;
 
    private BookCondition sampleCondition;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookConditionController).build();
        objectMapper = new ObjectMapper();
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