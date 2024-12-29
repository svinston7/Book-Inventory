package com.Controller;

import com.Service.PermRoleService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.PermRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PermRoleControllerTest {

    @InjectMocks
    private PermRoleController permRoleController;

    @Mock
    private PermRoleService permRoleService;

    private PermRole samplePermRole;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize a sample PermRole object
        samplePermRole = new PermRole(1, "ADMIN");
    }

    @Test
    void testPostRole() throws InvalidInputException {
        // Mock the service behavior
        // The service method addPermRole doesn't return a value, so no need to mock a return value.
        doNothing().when(permRoleService).addPermRole(samplePermRole);

        // Call the controller method
        ResponseEntity<?> response = permRoleController.postRole(samplePermRole);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue());
       // assertEquals(samplePermRole, response.getBody());

        // Verify that addPermRole was called once
        verify(permRoleService, times(1)).addPermRole(samplePermRole);
    }

    @Test
    void testGetRole() throws InvalidInputException, ResourceNotFoundException {
        // Mock the service behavior
        when(permRoleService.findById(1)).thenReturn(samplePermRole);

        // Call the controller method
        ResponseEntity<?> response = permRoleController.getRole(1);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePermRole, response.getBody());

        // Verify that findById was called once
        verify(permRoleService, times(1)).findById(1);
    }

    @Test
    void testUpdateRole() throws InvalidInputException, ResourceNotFoundException {
        // Mock the service behavior
        when(permRoleService.findById(1)).thenReturn(samplePermRole);
        doNothing().when(permRoleService).addPermRole(any(PermRole.class));

        // Call the controller method
        ResponseEntity<?> response = permRoleController.updateRole(1, "USER");

        // Assert the response
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Success", response.getBody());

        // Verify that addPermRole was called once
        verify(permRoleService, times(1)).addPermRole(any(PermRole.class));

        // Verify that the role was updated to "USER"
        assertEquals("USER", samplePermRole.getPermRole());
    }
}
