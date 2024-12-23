package com.app.ServiceTest;

import com.Service.PurchaseLogService;
import com.dao.PurchaseLogDAO;
import com.model.Inventory;
import com.model.PurchaseLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PurchaseLogServiceTest {

    @Mock
    private PurchaseLogDAO purchaseLogDao;

    @InjectMocks
    private PurchaseLogService purchaseLogService;

    private PurchaseLog purchaseLog;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Inventory inventory = new Inventory(1, "12345", 5, false);
        purchaseLog = new PurchaseLog();
        purchaseLog.setId(1);
        purchaseLog.setUserId(101);
        purchaseLog.setInventory(inventory);
        purchaseLog.setInventoryId(1);
    }

    @Test
    public void testGetAllPurchaseLogs() {
        // Arrange
        when(purchaseLogDao.findAll()).thenReturn(List.of(purchaseLog));

        // Act
        List<PurchaseLog> result = purchaseLogService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getUserId());
    }

    @Test
    public void testFindById_Valid() {
        // Arrange
        when(purchaseLogDao.findById(1)).thenReturn(Optional.of(purchaseLog));

        // Act
        PurchaseLog result = purchaseLogService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(101, result.getUserId());
    }

    @Test
    public void testFindById_Invalid() {
        // Arrange
        when(purchaseLogDao.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> purchaseLogService.findById(1));
        assertEquals("PurchaseLog not found with id: 1", exception.getMessage());
    }

    @Test
    public void testAddPurchaseLog() {
        // Arrange
        when(purchaseLogDao.save(purchaseLog)).thenReturn(purchaseLog);

        // Act
        purchaseLogService.addPurchaseLog(purchaseLog);

        // Assert
        verify(purchaseLogDao, times(1)).save(purchaseLog);
    }

    @Test
    public void testRemovePurchaseLog() {
        // Arrange
        doNothing().when(purchaseLogDao).deleteById(1);

        // Act
        purchaseLogService.removePurchaseLog(1);

        // Assert
        verify(purchaseLogDao, times(1)).deleteById(1);
    }

    @Test
    public void testFindByUserId_Valid() {
        // Arrange
        when(purchaseLogDao.findByUserId(101)).thenReturn(purchaseLog);

        // Act
        PurchaseLog result = purchaseLogService.findByUserId(101);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testFindByUserId_Invalid() {
        // Arrange
        when(purchaseLogDao.findByUserId(101)).thenReturn(null);

        // Act
        PurchaseLog result = purchaseLogService.findByUserId(101);

        // Assert
        assertNull(result);
    }

    @Test
    public void testUpdateInventoryByUserId_Valid() {
        // Arrange
        when(purchaseLogDao.findByUserId(101)).thenReturn(purchaseLog);

        // Act
        String result = purchaseLogService.updateInventoryByUserId(101, 2);

        // Assert
        assertEquals("Successfully updated", result);
        assertEquals(2, purchaseLog.getInventoryId());
    }

    @Test
    public void testUpdateInventoryByUserId_Invalid() {
        // Arrange
        when(purchaseLogDao.findByUserId(101)).thenReturn(null);

        // Act
        String result = purchaseLogService.updateInventoryByUserId(101, 2);

        // Assert
        assertEquals("Inventory with id not found", result);
    }
}
