package com.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

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
                .contentType(MediaType.TEXT_PLAIN) // Set the content type to match the expected plain text
                .content("NewName")) // Send the firstName in the request body
                .andExpect(status().isOk()); // Expect HTTP 200 OK

        verify(userService, times(1)).updateUserFirstName(1, "NewName");
    }
    @Test
    void testUpdateLastName() throws Exception {
        // Given
        doNothing().when(userService).updateUserLastName(anyInt(), anyString());  // Mock service call to update last name

        // When
        mockMvc.perform(put("/api/user/update/lastname/1")  // Make PUT request to update last name
                .contentType(MediaType.TEXT_PLAIN)  // Specify the content type as plain text
                .content("NewLastName"))  // Pass the new last name in the request body
                .andExpect(status().isOk());  // Expect HTTP 200 OK response

        // Then
        verify(userService, times(1)).updateUserLastName(1, "NewLastName");  // Verify service method was called once
    }

    @Test
    void testUpdatePhoneNumber() throws Exception {
        // Given
        doNothing().when(userService).updateUserPhoneNum(anyInt(), anyString());

        // When
        mockMvc.perform(put("/api/user/update/phonenumber/1")
                .contentType(MediaType.TEXT_PLAIN)
                .content("9876543210"))
                .andExpect(status().isOk());

        // Then
        verify(userService, times(1)).updateUserPhoneNum(1, "9876543210");
    }

    @Test
    void testUpdateRole() throws Exception {
        // Given
        doNothing().when(userService).updateRole(anyInt(), anyInt());

        // When
        mockMvc.perform(put("/api/user/update/role/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")) // Assuming role number is passed as JSON (example: 2)
                .andExpect(status().isOk());

        // Then
        verify(userService, times(1)).updateRole(1, 2);
    }

    @Test
    void testGetUserById() throws Exception {
        // Given
        User user = new User();
        user.setUserId(1);
        user.setUserName("testUser");

        when(userService.findByUserName(anyString())).thenReturn(user);

        // When
        mockMvc.perform(get("/api/user/{username}", "testUser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("testUser"));

        // Then
        verify(userService, times(1)).findByUserName("testUser");
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Given
        User user1 = new User();
        user1.setUserId(1);
        user1.setUserName("testUser1");
        User user2 = new User();
        user2.setUserId(2);
        user2.setUserName("testUser2");

        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        // When
        mockMvc.perform(get("/api/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].userName").value("testUser1"))
                .andExpect(jsonPath("$[1].userName").value("testUser2"));

        // Then
        verify(userService, times(1)).getAllUsers();
    }
}