package com.Controller;

import com.Service.InventoryService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InventoryControllerTest {

    @InjectMocks
    private InventoryController inventoryController;

    @Mock
    private InventoryService inventoryService;

    private Inventory sampleInventory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize a sample Inventory object
        sampleInventory = new Inventory();
        sampleInventory.setInventoryId(1);
        sampleInventory.setIsbn("123456789");
        sampleInventory.setRanks(5);
        sampleInventory.setPurchased(false);
    }

    @Test
    void testPostInventory() throws InvalidInputException {
        // Mock service behavior
        doNothing().when(inventoryService).addInventory(sampleInventory);

        // Call the method
        ResponseEntity<?> response = inventoryController.postInventory(sampleInventory);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue());
       // assertEquals("Sucess", response.getBody());

        // Verify that the service method was called once
        verify(inventoryService, times(1)).addInventory(sampleInventory);
    }

    @Test
    void testGetInventory() throws InvalidInputException, ResourceNotFoundException {
        // Mock service behavior
        when(inventoryService.findById(1)).thenReturn(sampleInventory);

        // Call the method
        ResponseEntity<?> response = inventoryController.getInventory(1);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleInventory, response.getBody());

        // Verify that the service method was called once
        verify(inventoryService, times(1)).findById(1);
    }

    @Test
    void testUpdatePurchased() throws InvalidInputException, ResourceNotFoundException {
        // Mock service behavior
        doNothing().when(inventoryService).updatePurchased(1, true);

        // Call the method
        ResponseEntity<?> response = inventoryController.putMethodName(1, true);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());

        // Verify that the service method was called once
        verify(inventoryService, times(1)).updatePurchased(1, true);
    }
}
