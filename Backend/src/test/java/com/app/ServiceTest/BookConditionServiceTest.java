package com.app.ServiceTest;
 
import com.Service.BookConditionService;
import com.dao.BookConditionDAO;
import com.model.BookCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.List;
 
public class BookConditionServiceTest {
 
    @Mock
    private BookConditionDAO bookConditionDAO;
 
    @InjectMocks
    private BookConditionService bookConditionService;
 
    private BookCondition bookCondition;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookCondition = new BookCondition(1, "New", "Brand new condition", 19.99);
    }
 
    @Test
    public void testGetAllBookConditions() {
        // Arrange
        when(bookConditionDAO.findAll()).thenReturn(List.of(bookCondition));
 
        // Act
        var bookConditions = bookConditionService.getAll();
 
        // Assert
        assertNotNull(bookConditions);
        assertEquals(1, bookConditions.size());
        assertEquals("New", bookConditions.get(0).getDescription());
    }
 
    @Test
    public void testGetBookCondition_ValidRank() {
        // Arrange
        when(bookConditionDAO.findByRanks(1)).thenReturn(bookCondition);
 
        // Act
        var result = bookConditionService.getBookCondition(1);
 
        // Assert
        assertNotNull(result);
        assertEquals("New", result.getDescription());
    }
 
    @Test
    public void testGetBookCondition_InvalidRank() {
        // Arrange
        when(bookConditionDAO.findByRanks(1)).thenReturn(null);
 
        // Act
        var result = bookConditionService.getBookCondition(1);
 
        // Assert
        assertNull(result);
    }
 
    @Test
    public void testUpdateDescription() {
        // Arrange
        String newDescription = "Used, but in good condition";
        when(bookConditionDAO.findByRanks(1)).thenReturn(bookCondition);
 
        // Act
        bookConditionService.updateDescription(1, newDescription);
 
        // Assert
        verify(bookConditionDAO, times(1)).findByRanks(1);
        assertEquals(newDescription, bookCondition.getDescription());
    }
 
    @Test
    public void testUpdateFullDescription() {
        // Arrange
        String newFullDescription = "Slight wear, but no significant damage.";
        when(bookConditionDAO.findByRanks(1)).thenReturn(bookCondition);
 
        // Act
        bookConditionService.updateFullDescription(1, newFullDescription);
 
        // Assert
        verify(bookConditionDAO, times(1)).findByRanks(1);
        assertEquals(newFullDescription, bookCondition.getFullDescription());
    }
 
    @Test
    public void testUpdatePrice() {
        // Arrange
        double newPrice = 25.99;
        when(bookConditionDAO.findByRanks(1)).thenReturn(bookCondition);
 
        // Act
        bookConditionService.updatePrice(1, newPrice);
 
        // Assert
        verify(bookConditionDAO, times(1)).findByRanks(1);
        assertEquals(newPrice, bookCondition.getPrice());
    }
 
    @Test
    public void testAddBookCondition() {
        // Arrange
        when(bookConditionDAO.save(bookCondition)).thenReturn(bookCondition);
 
        // Act
        bookConditionService.addBookCondition(bookCondition);
 
        // Assert
        verify(bookConditionDAO, times(1)).save(bookCondition);
    }
}