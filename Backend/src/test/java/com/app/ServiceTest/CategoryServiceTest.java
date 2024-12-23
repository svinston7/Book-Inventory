package com.app.ServiceTest;
 
 
import com.Service.CategoryService;
import com.dao.CategoryDAO;
import com.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
public class CategoryServiceTest {
 
    @Mock
    private CategoryDAO categoryDAO;
 
    @InjectMocks
    private CategoryService categoryService;
 
    private Category category;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
 
        // Initialize Category object
        category = new Category(1, "Fiction");
    }
 
    @Test
    public void testGetAllCategories() {
        // Arrange
        when(categoryDAO.findAll()).thenReturn(List.of(category));
 
        // Act
        var categories = categoryService.getAll();
 
        // Assert
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Fiction", categories.get(0).getCatDescription());
    }
 
    @Test
    public void testFindById_Valid() {
        // Arrange
        when(categoryDAO.findById(1)).thenReturn(Optional.of(category));
 
        // Act
        var result = categoryService.findById(1);
 
        // Assert
        assertNotNull(result);
        assertEquals("Fiction", result.getCatDescription());
    }
 
    @Test
    public void testFindById_Invalid() {
        // Arrange
        when(categoryDAO.findById(1)).thenReturn(Optional.empty());
 
        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> categoryService.findById(1));
 
        assertEquals("Category not found with id: 1", exception.getMessage());
    }
 
    @Test
    public void testRemoveCategory() {
        // Arrange
        doNothing().when(categoryDAO).deleteById(1);
 
        // Act
        categoryService.removeCategory(1);
 
        // Assert
        verify(categoryDAO, times(1)).deleteById(1);
    }
 
    @Test
    public void testAddCategory() {
        // Arrange
        when(categoryDAO.save(category)).thenReturn(category);
 
        // Act
        categoryService.addCategory(category);
 
        // Assert
        verify(categoryDAO, times(1)).save(category);
    }
 
    @Test
    public void testUpdateDescription_Valid() {
        // Arrange
        when(categoryDAO.findById(1)).thenReturn(Optional.of(category));
        String newDescription = "Science Fiction";
 
        // Act
        Category updatedCategory = categoryService.updateDescription(1, newDescription);
 
        // Assert
        assertNotNull(updatedCategory);
        assertEquals("Science Fiction", updatedCategory.getCatDescription());
        verify(categoryDAO, times(1)).save(updatedCategory);
    }
 
    @Test
    public void testUpdateDescription_CategoryNotFound() {
        // Arrange
        when(categoryDAO.findById(1)).thenReturn(Optional.empty());
 
        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> categoryService.updateDescription(1, "New Description"));
 
        assertEquals("Category with ID 1 not found.", exception.getMessage());
    }
}
