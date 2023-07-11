package com.ThesisApp.unitTest;

import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Role;
import com.ThesisApp.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ProfessorTest {
    @Test
    public void testProfessorCreation() {
        User user = new User("username", "password", Role.PROFESSOR);
        Professor professor = new Professor("name", "surname", "email", user, null, null);
        assertEquals("name", professor.getName());
        assertEquals("surname", professor.getSurname());
        assertEquals("email", professor.getEmail());
    }
}