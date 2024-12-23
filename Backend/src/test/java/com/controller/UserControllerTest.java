package com.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Controller.UserController;
import com.Service.UserService;
import com.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testRegister() throws Exception {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("password");

        when(userService.register(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("testUser"));

        verify(userService, times(1)).register(any(User.class));
    }

    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("password");

        when(userService.verify(any(User.class))).thenReturn("token");

        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("token"));

        verify(userService, times(1)).verify(any(User.class));
    }

    @Test
    void testUpdateFirstName() throws Exception {
        doNothing().when(userService).updateUserFirstName(anyInt(), anyString());

        mockMvc.perform(put("/api/user/update/firstname/1")
                .param("firstName", "NewName"))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUserFirstName(1, "NewName");
    }
}