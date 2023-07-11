package com.ThesisApp.unitTest;

import com.ThesisApp.model.Role;
import com.ThesisApp.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testUserCreation() {
        User user = new User("username", "password", Role.STUDENT);
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(Role.STUDENT, user.getRole());
    }
}
