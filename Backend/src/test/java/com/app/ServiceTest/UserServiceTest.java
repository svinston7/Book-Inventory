package com.app.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Service.JwtService;
import com.Service.UserService;
import com.dao.UserDAO;
import com.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUserId(1);
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setUserName("john.doe");
        testUser.setPassword("password123");
        testUser.setPhoneNumber("1234567890");
        testUser.setRoleNumber(1);
    }

    @Test
    public void testRegisterUser() {
        // Given
        when(bCryptPasswordEncoder.encode(testUser.getPassword())).thenReturn("encodedPassword");
        when(userDAO.save(testUser)).thenReturn(testUser);

        // When
        User registeredUser = userService.register(testUser);

        // Then
        assertNotNull(registeredUser);
        assertEquals("encodedPassword", registeredUser.getPassword());
        verify(userDAO, times(1)).save(testUser);
    }


    @Test
    public void testVerifyUser_Success() {
        // Given
        String username = "john.doe";
        String password = "password123";
        
        // Mock the Authentication object
        Authentication mockAuthentication = mock(Authentication.class);
        when(mockAuthentication.isAuthenticated()).thenReturn(true); // Ensure authentication is successful
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication); // Return the mocked authentication object

        // Mock JWT token generation
        when(jwtService.generateToken(username)).thenReturn("generatedToken");

        // When
        String token = userService.verify(testUser);

        // Then
        assertEquals("generatedToken", token); // Verify the expected token is returned
    }

    

    @Test
    public void testUpdateUserFirstName() {
        // Given
        when(userDAO.findById(1)).thenReturn(java.util.Optional.of(testUser));

        // When
        userService.updateUserFirstName(1, "NewFirstName");

        // Then
        assertEquals("NewFirstName", testUser.getFirstName());
        verify(userDAO, times(1)).save(testUser);
    }

    @Test
    public void testAddUser() {
        // Given
        when(userDAO.save(testUser)).thenReturn(testUser);

        // When
        userService.addUser(testUser);

        // Then
        verify(userDAO, times(1)).save(testUser);
    }
}

//package com.app.ServiceTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.Service.JwtService;
//import com.Service.UserService;
//import com.dao.UserDAO;
//import com.model.User;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserDAO userDAO;
//
//    @Mock
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private JwtService jwtService;
//
//    @InjectMocks
//    private UserService userService;
//
//    private User testUser;
//
//    @BeforeEach
//    public void setUp() {
//        testUser = new User();
//        testUser.setUserId(1);
//        testUser.setFirstName("John");
//        testUser.setLastName("Doe");
//        testUser.setUserName("john.doe");
//        testUser.setPassword("password123");
//        testUser.setPhoneNumber("1234567890");
//        testUser.setRoleNumber(1);
//    }
//
//   // @Test
//    public void testRegisterUser() {
//        // Given
//        when(bCryptPasswordEncoder.encode(testUser.getPassword())).thenReturn("encodedPassword");
//        when(userDAO.save(testUser)).thenReturn(testUser);
//
//        // When
//        User registeredUser = userService.register(testUser);
//
//        // Then
//        assertNotNull(registeredUser);
//        assertEquals("encodedPassword", registeredUser.getPassword());
//        verify(userDAO, times(1)).save(testUser);
//    }
//
//   // @Test
//    public void testVerifyUser_Success() {
//        // Given
//        String username = "john.doe";
//        String password = "password123";
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenReturn(mock(Authentication.class));
//        when(jwtService.generateToken(username)).thenReturn("generatedToken");
//
//        // When
//        String token = userService.verify(testUser);
//
//        // Then
//        assertEquals("generatedToken", token);
//    }
//
//    @Test
//    public void testVerifyUser_Failure() {
//        // Given
//        String username = "john.doe";
//        String password = "wrongPassword";
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenThrow(new RuntimeException("Authentication failed"));
//
//        // When
//        String token = userService.verify(testUser);
//
//        // Then
//        assertEquals("failure", token);
//    }
//
//   // @Test
//    public void testUpdateUserFirstName() {
//        // Given
//        when(userDAO.findById(1)).thenReturn(java.util.Optional.of(testUser));
//
//        // When
//        userService.updateUserFirstName(1, "NewFirstName");
//
//        // Then
//        assertEquals("NewFirstName", testUser.getFirstName());
//        verify(userDAO, times(1)).save(testUser);
//    }
//
//   // @Test
//    public void testAddUser() {
//        // Given
//        when(userDAO.save(testUser)).thenReturn(testUser);
//
//        // When
//        userService.addUser(testUser);
//
//        // Then
//        verify(userDAO, times(1)).save(testUser);
//    }
//}
