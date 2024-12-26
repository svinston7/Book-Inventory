package com.Controller;

import com.Service.PermRoleService;
import com.model.PermRole;
import com.exception.Response;
import com.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;

class PermRoleControllerTest {

    @InjectMocks
    private PermRoleController permRoleController;

    @Mock
    private PermRoleService permRoleService;

    private PermRole sampleRole;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleRole = new PermRole();
        sampleRole.setRoleNumber(1);
        sampleRole.setPermRole("ADMIN");
    }

    @Test
    void testPostRole() {
        when(permRoleService.getAll()).thenReturn(List.of());
        ResponseEntity<?> response = permRoleController.postRole(sampleRole);

        assertEquals(201, response.getStatusCodeValue());
        Response responseBody = (Response) response.getBody();
        assertEquals("POSTSUCCESS", responseBody.getCode());
        assertEquals("Perm Role added successfully", responseBody.getMessage());

        verify(permRoleService, times(1)).addPermRole(sampleRole);
    }

    @Test
    void testPostRoleAlreadyExists() {
       
        when(permRoleService.getAll()).thenReturn(List.of(sampleRole));

        CustomException thrown = assertThrows(CustomException.class, () -> {
            permRoleController.postRole(sampleRole);
        });

        assertEquals("ADDFAILS", thrown.getCode());
        assertEquals("Perm Role already exists", thrown.getMessage());
        verify(permRoleService, never()).addPermRole(sampleRole);
    }

    @Test
    void testGetRole() {
      
        when(permRoleService.findById(1)).thenReturn(sampleRole);
        ResponseEntity<?> response = permRoleController.getRole(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleRole, response.getBody());
        verify(permRoleService, times(1)).findById(1);
    }

    @Test
    void testUpdateRole() {
        when(permRoleService.findById(1)).thenReturn(sampleRole);
        doNothing().when(permRoleService).addPermRole(any(PermRole.class));
        ResponseEntity<?> response = permRoleController.updateRole(1, "USER");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Success", response.getBody());
        verify(permRoleService, times(1)).addPermRole(any(PermRole.class));
    }

    @Test
    void testUpdateRoleNotFound() {
        when(permRoleService.findById(1)).thenThrow(new RuntimeException("PermRole not found"));
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            permRoleController.updateRole(1, "USER");
        });

        assertEquals("PermRole not found", thrown.getMessage());
        verify(permRoleService, never()).addPermRole(any(PermRole.class));
    }
}
