package com.app.ServiceTest;

import com.Service.StateService;
import com.dao.StateDAO;
import com.model.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StateServiceTest {

    @Mock
    private StateDAO stateDao;

    @InjectMocks
    private StateService stateService;

    private State state;

    @BeforeEach
    void setUp() {
        state = new State("NY", "New York");
    }

    @Test
    void testGetAll() {
        // Add a mock behavior
        when(stateDao.findAll()).thenReturn(List.of(state));

        // Call the method
        List<State> states = stateService.getAll();

        // Verify the result
        assertNotNull(states);
        assertEquals(1, states.size());
        assertEquals("NY", states.get(0).getStateCode());
    }

    @Test
    void testFindByCode() {
        // Mock the behavior
        when(stateDao.findById("NY")).thenReturn(Optional.of(state));

        // Call the method
        State foundState = stateService.findByCode("NY");

        // Verify the result
        assertNotNull(foundState);
        assertEquals("NY", foundState.getStateCode());
    }

    @Test
    void testFindByCode_StateNotFound() {
        // Mock the behavior
        when(stateDao.findById("XYZ")).thenReturn(Optional.empty());

        // Call the method and verify exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            stateService.findByCode("XYZ");
        });

        assertEquals("State not found with code: XYZ", exception.getMessage());
    }

    @Test
    void testUpdateStateName() {
        // Mock the behavior
        when(stateDao.findById("NY")).thenReturn(Optional.of(state));
        when(stateDao.save(state)).thenReturn(state);

        // Call the method
        String result = stateService.updateStateName("NY", "New York Updated");

        // Verify the result
        assertEquals("state name updated Sucessfully", result);
        assertEquals("New York Updated", state.getStateName());
    }

//    @Test
//    void testRemoveState() {
//        // Mock the behavior
//        when(stateDao.findById("NY")).thenReturn(Optional.of(state));
//
//        // Call the method
//        stateService.removeState("NY");
//
//        // Verify the interactions
//        verify(stateDao, times(1)).deleteById("NY");
//    }
    @Test
    void testRemoveState_StateNotFound() {
        // Mock the behavior for when the state is not found
        when(stateDao.findById("NY")).thenReturn(Optional.empty());

        // Call the method and verify exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            stateService.removeState("NY");
        });

        assertEquals("State not found with code: NY", exception.getMessage());

        // Verify deleteById is not called
        verify(stateDao, never()).deleteById("NY");
    }


    @Test
    void testAddState() {
        // Mock the behavior
        when(stateDao.save(state)).thenReturn(state);

        // Call the method
        stateService.addState(state);

        // Verify the interaction
        verify(stateDao, times(1)).save(state);
    }
}

//package com.app.ServiceTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.Service.StateService;
//import com.dao.StateDAO;
//import com.model.State;
//
//class StateServiceTest {
//
//    @InjectMocks
//    private StateService stateService;
//
//    @Mock
//    private StateDAO stateDao;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAll() {
//        List<State> states = Arrays.asList(
//            new State("TX", "Texas"),
//            new State("CA", "California")
//        );
//        
//        when(stateDao.findAll()).thenReturn(states);
//        
//        List<State> result = stateService.getAll();
//        
//        assertEquals(2, result.size());
//        verify(stateDao, times(1)).findAll();
//    }
//
//    @Test
//    void testFindByCode_Success() {
//        State state = new State("NY", "New York");
//        
//        when(stateDao.findById("NY")).thenReturn(Optional.of(state));
//        
//        State result = stateService.findByCode("NY");
//        
//        assertNotNull(result);
//        assertEquals("New York", result.getStateName());
//        verify(stateDao, times(1)).findById("NY");
//    }
//
//    @Test
//    void testFindByCode_NotFound() {
//        when(stateDao.findById("FL")).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            stateService.findByCode("FL");
//        });
//
//        assertEquals("State not found with code: FL", exception.getMessage());
//        verify(stateDao, times(1)).findById("FL");
//    }
//
//    @Test
//    void testUpdateStateName_Success() {
//        State state = new State("WA", "Washington");
//        
//        when(stateDao.findById("WA")).thenReturn(Optional.of(state));
//        
//        String result = stateService.updateStateName("WA", "Washington DC");
//        
//        assertEquals("state name updated Sucessfully", result);
//        assertEquals("Washington DC", state.getStateName());
//        verify(stateDao, times(1)).save(state);
//    }
//    @Test
//    void testUpdateStateName_NotFound() {
//        when(stateDao.findById("NV")).thenReturn(Optional.empty());
//        
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            stateService.updateStateName("NV", "Nevada");
//        });
//
//        assertEquals("State not found with code: NV", exception.getMessage());
//        verify(stateDao, times(1)).findById("NV");
//        verify(stateDao, never()).save(any(State.class));
//    }
//
//
////    @Test
////    void testUpdateStateName_NotFound() {
////        when(stateDao.findById("NV")).thenReturn(Optional.empty());
////        
////        Exception exception = assertThrows(NullPointerException.class, () -> {
////            stateService.updateStateName("NV", "Nevada");
////        });
////
////        assertNull(exception.getMessage());
////        verify(stateDao, times(1)).findById("NV");
////        verify(stateDao, never()).save(any(State.class));
////    }
//
//    @Test
//    void testRemoveState() {
//        doNothing().when(stateDao).deleteById("TX");
//        
//        stateService.removeState("TX");
//        
//        verify(stateDao, times(1)).deleteById("TX");
//    }
//
//    @Test
//    void testAddState() {
//        State state = new State("OR", "Oregon");
//        
//        when(stateDao.save(state)).thenReturn(state);
//        
//        stateService.addState(state);
//        
//        verify(stateDao, times(1)).save(state);
//    }
//}
