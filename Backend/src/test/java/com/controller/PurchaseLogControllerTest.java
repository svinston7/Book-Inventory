package com.Controller;

import com.Service.PurchaseLogService;
import com.model.PurchaseLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PurchaseLogControllerTest {

    @InjectMocks
    private PurchaseLogController purchaseLogController;

    @Mock
    private PurchaseLogService purchaseLogService;

    private PurchaseLog samplePurchaseLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize a sample PurchaseLog object
        samplePurchaseLog = new PurchaseLog();
        samplePurchaseLog.setId(1);
        samplePurchaseLog.setUserId(1);
        samplePurchaseLog.setInventoryId(100);
    }

    @Test
    void testPostPurchase() {
        
    	doNothing().when(purchaseLogService).addPurchaseLog(samplePurchaseLog);

      
        ResponseEntity<?> response = purchaseLogController.postPurchase(samplePurchaseLog);

      
        assertEquals(200, response.getStatusCodeValue());

       
        verify(purchaseLogService, times(1)).addPurchaseLog(samplePurchaseLog);
    }

    @Test
    void testGetPurchaseByUserId() {
        
        when(purchaseLogService.findById(1)).thenReturn(samplePurchaseLog);

        ResponseEntity<?> response = purchaseLogController.getPurchase(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePurchaseLog, response.getBody());

       
        verify(purchaseLogService, times(1)).findById(1);
    }

    @Test
    void testUpdatePurchaseInventoryId() {
        when(purchaseLogService.updateInventoryByUserId(1, 200)).thenReturn("Successfully updated");

        ResponseEntity<?> response = purchaseLogController.updatePurchase(1, 200);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());

       
        verify(purchaseLogService, times(1)).updateInventoryByUserId(1, 200);
    }

    @Test
    void testUpdatePurchaseInventoryIdNotFound() {
       
        when(purchaseLogService.updateInventoryByUserId(2, 200)).thenReturn("Inventory with id not found");

        
        ResponseEntity<?> response = purchaseLogController.updatePurchase(2, 200);

        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());

        verify(purchaseLogService, times(1)).updateInventoryByUserId(2, 200);
    }
}
