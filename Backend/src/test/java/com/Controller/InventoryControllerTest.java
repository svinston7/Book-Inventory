package com.Controller;

import com.Service.InventoryService;
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
    void testPostInventory() {
       
        doNothing().when(inventoryService).addInventory(sampleInventory);

        ResponseEntity<?> response = inventoryController.postInventory(sampleInventory);

        assertEquals(201, response.getStatusCodeValue());
        
        com.exception.Response responseBody = (com.exception.Response) response.getBody();
        
        assertEquals("POSTSUCCESS", responseBody.getCode());
        assertEquals("Inventory added successfully", responseBody.getMessage()); 

        verify(inventoryService, times(1)).addInventory(sampleInventory);
    }

    @Test
    void testGetInventory() {
       
        when(inventoryService.findById(1)).thenReturn(sampleInventory);

        ResponseEntity<?> response = inventoryController.getInventory(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleInventory, response.getBody());

        verify(inventoryService, times(1)).findById(1);
    }

    @Test
    void testUpdatePurchased() {
       
        doNothing().when(inventoryService).updatePurchased(1, true);

        ResponseEntity<?> response = inventoryController.putMethodName(1, true);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());

        verify(inventoryService, times(1)).updatePurchased(1, true);
    }
}
