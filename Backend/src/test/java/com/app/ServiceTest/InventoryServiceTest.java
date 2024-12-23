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

        assertTrue(inventory.getPurchased());
        verify(inventoryDAO, times(1)).findById(1);
        verify(inventoryDAO, times(1)).save(inventory);
    }
}

//package com.app.ServiceTest;
//
//import com.Service.InventoryService;
//import com.dao.InventoryDAO;
//import com.model.Inventory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class InventoryServiceTest {
//
//    @Mock
//    private InventoryDAO inventoryDao;
//
//    @InjectMocks
//    private InventoryService inventoryService;
//
//    private Inventory inventory;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        inventory = new Inventory();
//    }
//
//    @Test
//    public void testGetAllInventory() {
//        // Arrange
//        when(inventoryDao.findAll()).thenReturn(List.of(inventory));
//
//        // Act
//        List<Inventory> result = inventoryService.getAll();
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(1, result.get(0).getInventoryId());
//    }
//
////    @Test
////    public void testFindById_Valid() {
////        // Arrange
////        when(inventoryDao.findById(1)).thenReturn(Optional.of(inventory));
////
////        // Act
////        Inventory result = inventoryService.findById(1);
////
////        // Assert
////        assertNotNull(result);
////        assertEquals(1, result.getInventoryId());
////    }
//
//    @Test
//    public void testFindById_Invalid() {
//        // Arrange
//        when(inventoryDao.findById(1)).thenReturn(Optional.empty());
//
//        // Act and Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> inventoryService.findById(1));
//        assertEquals("Inventory not found with id: 1", exception.getMessage());
//    }
//
//    @Test
//    public void testAddInventory() {
//        // Arrange
//        when(inventoryDao.save(inventory)).thenReturn(inventory);
//
//        // Act
//        inventoryService.addInventory(inventory);
//
//        // Assert
//        verify(inventoryDao, times(1)).save(inventory);
//    }
//
//    @Test
//    public void testRemoveInventory() {
//        // Arrange
//        doNothing().when(inventoryDao).deleteById(1);
//
//        // Act
//        inventoryService.removeInventory(1);
//
//        // Assert
//        verify(inventoryDao, times(1)).deleteById(1);
//    }
//
//    @Test
//    public void testUpdatePurchased_Valid() {
//        // Arrange
//        when(inventoryDao.findById(1)).thenReturn(Optional.of(inventory));
//        when(inventoryDao.save(inventory)).thenReturn(inventory);
//
//        // Act
//        inventoryService.updatePurchased(1, true);
//
//        // Assert
//        assertTrue(inventory.getPurchased());
//        verify(inventoryDao, times(1)).save(inventory);
//    }
//
//  //  @Test
////    public void testUpdatePurchased_Invalid() {
////        // Arrange
////        when(inventoryDao.findById(1)).thenReturn(Optional.empty());
////
////        // Act and Assert
////        Exception exception = assertThrows(RuntimeException.class, () -> inventoryService.updatePurchased(1, true));
////        assertEquals("Inventory not found with id: 1", exception.getMessage());
////    }
//    @Test
//    public void testUpdatePurchased_Invalid() {
//        // Arrange
//        when(inventoryDao.findById(1)).thenReturn(Optional.empty());
//
//        // Act and Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            Inventory inv = inventoryService.findById(1);
//            if (inv == null) {
//                throw new RuntimeException("Inventory not found with id: 1");
//            }
//            inv.setPurchased(true);
//        });
//        assertEquals("Inventory not found with id: 1", exception.getMessage());
//    }
//}

//package com.app.ServiceTest;
//import com.Service.InventoryService;
//import com.dao.InventoryDAO;
//import com.model.Inventory;
//import com.model.Book;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
// 
//import java.util.List;
//import java.util.Optional;
// 
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
// 
//public class InventoryServiceTest {
// 
//    @Mock
//    private InventoryDAO inventoryDAO;
// 
//    @InjectMocks
//    private InventoryService inventoryService;
// 
//    private Inventory inventory;
//    private Book book;
// 
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
// 
//        // Initialize Book and Inventory objects
//        book = new Book();
//        book.setIsbn("12345");
//        book.setTitle("Test Book");
//        book.setDescription("A test book");
//        book.setCategoryId(1);
//        book.setEdition("First");
//        book.setPublisherId(1);
// 
//        // Initialize Inventory with book
//        inventory = new Inventory(1, book.getIsbn(), 10, false);
//        inventory.setBook(book);  // Explicitly set the book object
//    }
// 
//   // @Test
//    public void testGetAllInventories() {
//        // Arrange
//        when(inventoryDAO.findAll()).thenReturn(List.of(inventory));
// 
//        // Act
//        List<Inventory> inventories = inventoryService.getAll();
// 
//        // Assert
//        assertNotNull(inventories);
//        assertEquals(1, inventories.size());
//        assertEquals("12345", inventories.get(0).getBook().getIsbn());  // Validate isbn from book
//    }
// 
//   // @Test
//    public void testFindById_Valid() {
//        // Arrange
//        when(inventoryDAO.findById(1)).thenReturn(Optional.of(inventory));
// 
//        // Act
//        Inventory result = inventoryService.findById(1);
// 
//        // Assert
//        assertNotNull(result);
//        assertEquals("12345", result.getBook().getIsbn());
//        assertEquals("Test Book", result.getBook().getTitle());
//    }
// 
//   // @Test
//    public void testFindById_Invalid() {
//        // Arrange
//        when(inventoryDAO.findById(1)).thenReturn(Optional.empty());
// 
//        // Act and Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> inventoryService.findById(1));
// 
//        assertEquals("Inventory not found with id: 1", exception.getMessage());
//    }
// 
//   // @Test
//    public void testRemoveInventory() {
//        // Arrange
//        doNothing().when(inventoryDAO).deleteById(1);
// 
//        // Act
//        inventoryService.removeInventory(1);
// 
//        // Assert
//        verify(inventoryDAO, times(1)).deleteById(1);
//    }
// 
//   // @Test
//    public void testAddInventory() {
//        // Arrange
//        when(inventoryDAO.save(inventory)).thenReturn(inventory);
// 
//        // Act
//        inventoryService.addInventory(inventory);
// 
//        // Assert
//        verify(inventoryDAO, times(1)).save(inventory);
//    }
// 
//    //@Test
//    public void testUpdatePurchased_Valid() {
//        // Arrange
//        when(inventoryDAO.findById(1)).thenReturn(Optional.of(inventory));
// 
//        // Act
//        inventoryService.updatePurchased(1, true);
// 
//        // Assert
//        assertTrue(inventory.getPurchased());
//        verify(inventoryDAO, times(1)).save(inventory);
//    }
// 
//    @Test
//    public void testUpdatePurchased_Invalid() {
//        // Arrange
//        when(inventoryDAO.findById(1)).thenReturn(Optional.empty());
// 
//        // Act and Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> inventoryService.updatePurchased(1, true));
// 
//        assertEquals("Inventory not found with id: 1", exception.getMessage());
//    }
//}
