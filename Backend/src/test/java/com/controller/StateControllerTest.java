package com.Controller;

import com.Service.StateService;
import com.exception.CustomException;
import com.exception.Response;
import com.model.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StateControllerTest {

    @Mock
    private StateService stateService;

    @InjectMocks
    private StateController stateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostState_Success() {
        State state = new State("CA", "California");

        when(stateService.findByCode("CA")).thenReturn(null);  // Simulate state not existing
        doNothing().when(stateService).addState(state);

        ResponseEntity<?> response = stateController.postState(state);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertInstanceOf(Response.class, response.getBody());

        Response body = (Response) response.getBody();
        assertEquals("POSTSUCCESS", body.getCode());
        assertEquals("State added successfully", body.getMessage());

        verify(stateService, times(1)).addState(state);
    }

    @Test
    void testPostState_Failure_AlreadyExists() {
        State state = new State("CA", "California");

        when(stateService.findByCode("CA")).thenReturn(state);

        CustomException exception = assertThrows(CustomException.class, () -> {
            stateController.postState(state);
        });

        assertEquals("ADDFAILS", exception.getCode());
        assertEquals("State already exists", exception.getMessage());
        verify(stateService, never()).addState(any(State.class));
    }

    @Test
    void testGetAllState() {
        List<State> stateList = Arrays.asList(
                new State("CA", "California"),
                new State("NY", "New York")
        );

        when(stateService.getAll()).thenReturn(stateList);

        ResponseEntity<?> response = stateController.getAllState();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stateList, response.getBody());
        verify(stateService, times(1)).getAll();
    }

    @Test
    void testGetState_Success() {
        State state = new State("CA", "California");

        when(stateService.findByCode("CA")).thenReturn(state);

        ResponseEntity<?> response = stateController.getState("CA");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(state, response.getBody());
        verify(stateService, times(1)).findByCode("CA");
    }

    @Test
    void testGetState_NotFound() {
        when(stateService.findByCode("CA")).thenThrow(new RuntimeException("State not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            stateController.getState("CA");
        });

        assertEquals("State not found", exception.getMessage());
        verify(stateService, times(1)).findByCode("CA");
    }

    @Test
    void testUpdateState_Success() {
        State state = new State("CA", "California");

        when(stateService.findByCode("CA")).thenReturn(state);
        doNothing().when(stateService).addState(state);

        ResponseEntity<?> response = stateController.updateState("CA", "New California");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New California", state.getStateName());
        verify(stateService, times(1)).addState(state);
    }
}
