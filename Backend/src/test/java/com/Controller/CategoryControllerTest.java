package com.Controller;

import com.Service.CategoryService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private Category sampleCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleCategory = new Category(1, "Fiction");
    }

    @Test
    void testPostCategory() {
        doNothing().when(categoryService).addCategory(sampleCategory);

        ResponseEntity<?> response = categoryController.postCategory(sampleCategory);

        assertEquals(200, response.getStatusCodeValue());
       // assertEquals(sampleCategory, response.getBody());
        verify(categoryService, times(1)).addCategory(sampleCategory);
    }

    @Test
    void testGetCat() throws InvalidInputException, ResourceNotFoundException {
        when(categoryService.findById(1)).thenReturn(sampleCategory);

        ResponseEntity<?> response = categoryController.getCat(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleCategory, response.getBody());
        verify(categoryService, times(1)).findById(1);
    }

    @Test
    void testUpdateCat() throws InvalidInputException, ResourceNotFoundException {
        when(categoryService.updateDescription(1, "Updated Description")).thenReturn(sampleCategory);

        ResponseEntity<?> response = categoryController.updateCat(1, "Updated Description");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleCategory, response.getBody());
        verify(categoryService, times(1)).updateDescription(1, "Updated Description");
    }
}
