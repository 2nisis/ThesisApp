package com.ThesisApp.integrationServiceTest;

import com.ThesisApp.model.User;
import com.ThesisApp.repository.UserRepository;
import com.ThesisApp.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userDAO;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");

        when(bCryptPasswordEncoder.encode(any())).thenReturn("encryptedPassword");
        when(userDAO.save(any(User.class))).thenReturn(user);

        User savedUser = userServiceImpl.saveUser(user);

        assertEquals("testUsername", savedUser.getUsername());
        assertEquals("encryptedPassword", savedUser.getPassword());
    }

    @Test
    public void testIsUserPresent() {
        User user = new User();
        user.setUsername("testUsername");

        when(userDAO.findByUsername(anyString())).thenReturn(user);

        boolean isUserPresent = userServiceImpl.isUserPresent(user);

        assertTrue(isUserPresent);
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);

        when(userDAO.findById(anyString())).thenReturn(Optional.of(user));

        User foundUser = userServiceImpl.findById(user.getId().toString());

        assertEquals(1L , foundUser.getId().longValue());
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testUsername");

        when(userDAO.findByUsername(anyString())).thenReturn(user);

        User foundUser = userServiceImpl.findByUsername("testUsername");

        assertEquals("testUsername", foundUser.getUsername());
    }


}

