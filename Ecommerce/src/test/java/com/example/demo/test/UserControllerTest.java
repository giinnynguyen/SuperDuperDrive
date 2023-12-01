package com.example.demo.test;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("ginny");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        ResponseEntity<User> response = userController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        User resultUser = response.getBody();
        assertEquals(user.getUsername(), resultUser.getUsername());
    }

    @Test
    public void testGetUserByIdUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        ResponseEntity<User> response = userController.findById(2L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setId(1L);
        user.setUsername("ginny");

        when(userRepository.findByUsername("ginny")).thenReturn(user);

        ResponseEntity<User> response = userController.findByUserName("ginny");

        assertEquals(200, response.getStatusCodeValue());
        User resultUser = response.getBody();
        assertEquals(user.getUsername(), resultUser.getUsername());
    }

    @Test
    public void testGetUserByUsernameUserNotFound() {
        when(userRepository.findByUsername("asdasas")).thenReturn(null);

        ResponseEntity<User> response = userController.findByUserName("asdasas");

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreateUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("ginny");
        createUserRequest.setPassword("abcdefg");
        createUserRequest.setConfirmPassword("abcdefg");

        when(bCryptPasswordEncoder.encode("abcdefg")).thenReturn("dasasdasasdasd");

        ResponseEntity<User> response = userController.createUser(createUserRequest);

        assertEquals(200, response.getStatusCodeValue());
        User resultUser = response.getBody();
        assertEquals(createUserRequest.getUsername(), resultUser.getUsername());
        assertEquals("dasasdasasdasd", resultUser.getPassword());
        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUserInvalidPassword() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("ginny");
        createUserRequest.setPassword("abcdef");
        createUserRequest.setConfirmPassword("abcdef");

        ResponseEntity<User> response = userController.createUser(createUserRequest);

        assertEquals(400, response.getStatusCodeValue());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testCreateUserPasswordMismatch() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("ginny");
        createUserRequest.setPassword("abcdefg");
        createUserRequest.setConfirmPassword("sdasdasdasd");

        ResponseEntity<User> response = userController.createUser(createUserRequest);

        assertEquals(400, response.getStatusCodeValue());
        verify(userRepository, times(0)).save(any(User.class));
    }
}