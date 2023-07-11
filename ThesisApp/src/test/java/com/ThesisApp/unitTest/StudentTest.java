package com.ThesisApp.unitTest;

import com.ThesisApp.model.Student;
import com.ThesisApp.model.Role;
import com.ThesisApp.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class StudentTest {
    @Test
    public void testStudentCreation() {
        User user = new User("username", "password", Role.STUDENT);
        Student student = new Student("name", "surname", 1, 90, 5, user, null, null);
        assertEquals("name", student.getName());
        assertEquals("surname", student.getSurname());
        assertEquals(1, student.getYearOfStudies());
        assertEquals(90, student.getAverageGrade());
        assertEquals(5, student.getRemainingCourses());
    }
}