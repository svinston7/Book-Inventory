package com.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Service.PurchaseLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.PurchaseLog;

class PurchaseLogControllerTest {

    @Mock
    private PurchaseLogService purchaseLogService;

    @InjectMocks
    private PurchaseLogController purchaseLogController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseLogController).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    void testPostPurchase() throws Exception {
        // Arrange
        PurchaseLog purchaseLog = new PurchaseLog();
        doNothing().when(purchaseLogService).addPurchaseLog(any(PurchaseLog.class));

        // Act and Assert
        mockMvc.perform(post("/api/purchaselog/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchaseLog)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("POSTSUCCESS"))
                .andExpect(jsonPath("$.message").value("Log added successfully"));

        verify(purchaseLogService, times(1)).addPurchaseLog(any(PurchaseLog.class));
    }


    @Test
    void testGetPurchase() throws Exception {
        PurchaseLog purchaseLog = new PurchaseLog();
        purchaseLog.setId(1);
        purchaseLog.setUserId(101);
        purchaseLog.setInventoryId(202);

        when(purchaseLogService.findById(1)).thenReturn(purchaseLog);

        mockMvc.perform(get("/api/purchaselog/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(101))
                .andExpect(jsonPath("$.inventoryId").value(202));

        verify(purchaseLogService, times(1)).findById(1);
    }

    @Test
    void testUpdatePurchase() throws Exception {
        // Arrange: Mock the service method
        when(purchaseLogService.updateInventoryByUserId(101, 303)).thenReturn("Updated");

        // Act & Assert: Perform the PUT request and verify the response
        mockMvc.perform(put("/api/purchaselog/update/inventoryId/101")
                .contentType(MediaType.APPLICATION_JSON) // Set content type as JSON
                .content("303")) // Send the inventoryId in the request body
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(content().string("Updated")); // Assert the response content

        // Verify the service interaction
        verify(purchaseLogService, times(1)).updateInventoryByUserId(101, 303);
    }

}
