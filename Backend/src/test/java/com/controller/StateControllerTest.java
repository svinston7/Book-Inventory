package com.controller;

import com.Controller.StateController;
import com.Service.StateService;
import com.model.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StateService stateService;

    @InjectMocks
    private StateController stateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stateController).build();
    }

    @Test
    void testPostState() throws Exception {
        State state = new State();
        state.setStateCode("CA");
        state.setStateName("California");

        doNothing().when(stateService).addState(any(State.class));

        mockMvc.perform(post("/api/state/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"stateCode\":\"CA\", \"stateName\":\"California\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stateCode").value("CA"))
                .andExpect(jsonPath("$.stateName").value("California"));

        verify(stateService, times(1)).addState(any(State.class));
    }

    @Test
    void testGetAllState() throws Exception {
        List<State> states = Arrays.asList(
                new State("CA", "California"),
                new State("NY", "New York")
        );

        when(stateService.getAll()).thenReturn(states);

        mockMvc.perform(get("/api/state"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stateCode").value("CA"))
                .andExpect(jsonPath("$[0].stateName").value("California"))
                .andExpect(jsonPath("$[1].stateCode").value("NY"))
                .andExpect(jsonPath("$[1].stateName").value("New York"));

        verify(stateService, times(1)).getAll();
    }

    @Test
    void testGetState() throws Exception {
        State state = new State("CA", "California");

        when(stateService.findByCode("CA")).thenReturn(state);

        mockMvc.perform(get("/api/state/CA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stateCode").value("CA"))
                .andExpect(jsonPath("$.stateName").value("California"));

        verify(stateService, times(1)).findByCode("CA");
    }

    @Test
    void testUpdateState() throws Exception {
        State state = new State("CA", "California");

        when(stateService.findByCode("CA")).thenReturn(state);
        doNothing().when(stateService).addState(any(State.class));

        mockMvc.perform(put("/api/state/update/CA")
                .param("stateName", "New California"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stateCode").value("CA"))
                .andExpect(jsonPath("$.stateName").value("New California"));

        verify(stateService, times(1)).findByCode("CA");
        verify(stateService, times(1)).addState(any(State.class));
    }
}
