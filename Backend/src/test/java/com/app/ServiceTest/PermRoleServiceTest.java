package com.app.ServiceTest;

import com.Service.PermRoleService;
import com.dao.PermRoleDAO;
import com.model.PermRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PermRoleServiceTest {

    @Mock
    private PermRoleDAO permRoleDao;

    @InjectMocks
    private PermRoleService permRoleService;

    private PermRole permRole;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        permRole = new PermRole(1, "Admin");
    }

    @Test
    public void testGetAllPermRoles() {
        // Arrange
        when(permRoleDao.findAll()).thenReturn(List.of(permRole));

        // Act
        List<PermRole> result = permRoleService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Admin", result.get(0).getPermRole());
    }
    @Test
    public void testGetAllPermRoles_EmptyList() {
        when(permRoleDao.findAll()).thenReturn(List.of());

        List<PermRole> result = permRoleService.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty(), "Expected empty list");
    }

    @Test
    public void testFindById_Valid() {
        // Arrange
        when(permRoleDao.findById(1)).thenReturn(Optional.of(permRole));

        // Act
        PermRole result = permRoleService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getRoleNumber());
        assertEquals("Admin", result.getPermRole());
    }

    @Test
    public void testFindById_Invalid() {
        // Arrange
        when(permRoleDao.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> permRoleService.findById(1));
        assertEquals("PermRole not found with id: 1", exception.getMessage());
    }

    @Test
    public void testAddPermRole() {
        // Arrange
        when(permRoleDao.save(permRole)).thenReturn(permRole);

        // Act
        permRoleService.addPermRole(permRole);

        // Assert
        verify(permRoleDao, times(1)).save(permRole);
    }

    @Test
    public void testRemovePermRole() {
        // Arrange
        doNothing().when(permRoleDao).deleteById(1);

        // Act
        permRoleService.removePermrole(1);

        // Assert
        verify(permRoleDao, times(1)).deleteById(1);
    }
    @Test
    public void testUpdatePermRole() {
        // Arrange
        PermRole permRole = new PermRole(1, "Admin");
        when(permRoleDao.findById(1)).thenReturn(Optional.of(permRole));

        // Act
        permRoleService.updatePermRole(1, "SuperAdmin");

        // Assert
        assertEquals("SuperAdmin", permRole.getPermRole());
        verify(permRoleDao, times(1)).save(permRole);
    }

    


}
