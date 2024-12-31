package com.app.ServiceTest;

import com.Service.InventoryService;
import com.dao.InventoryDAO;
import com.model.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private InventoryDAO inventoryDAO;

    @InjectMocks
    private InventoryService inventoryService;

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setIsbn("12345");
        inventory.setRanks(10);
        inventory.setPurchased(false);
    }

    @Test
    void testGetAll() {
        // Assuming the DAO method returns a list of inventory items
        when(inventoryDAO.findAll()).thenReturn(List.of(inventory));

        var inventoryList = inventoryService.getAll();

        assertNotNull(inventoryList);
        assertEquals(1, inventoryList.size());
        verify(inventoryDAO, times(1)).findAll();
    }

    @Test
    void testFindById_InventoryExists() {
        when(inventoryDAO.findById(1)).thenReturn(Optional.of(inventory));

        Inventory foundInventory = inventoryService.findById(1);

        assertNotNull(foundInventory);
        assertEquals(1, foundInventory.getInventoryId());
        verify(inventoryDAO, times(1)).findById(1);
    }

    @Test
    void testFindById_InventoryNotFound() {
        when(inventoryDAO.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            inventoryService.findById(1);
        });

        assertEquals("Inventory not found with id: 1", thrown.getMessage());
        verify(inventoryDAO, times(1)).findById(1);
    }

    @Test
    void testAddInventory() {
        when(inventoryDAO.save(any(Inventory.class))).thenReturn(inventory);

        inventoryService.addInventory(inventory);

        verify(inventoryDAO, times(1)).save(inventory);
    }

    @Test
    void testRemoveInventory() {
        doNothing().when(inventoryDAO).deleteById(1);

        inventoryService.removeInventory(1);

        verify(inventoryDAO, times(1)).deleteById(1);
    }

    @Test
    void testUpdatePurchased() {
        when(inventoryDAO.findById(1)).thenReturn(Optional.of(inventory));
        when(inventoryDAO.save(any(Inventory.class))).thenReturn(inventory);

        inventoryService.updatePurchased(1, true);

        assertTrue(inventory.isPurchased());
        verify(inventoryDAO, times(1)).findById(1);
        verify(inventoryDAO, times(1)).save(inventory);
    }
}


