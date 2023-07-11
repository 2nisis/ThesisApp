package com.ThesisApp.unitTest;

import com.ThesisApp.model.Student;
import com.ThesisApp.model.Subject;
import com.ThesisApp.model.Thesis;
import com.ThesisApp.model.Professor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class ThesisTest {
    @Test
    public void testThesisCreation() {
        Student student = new Student();
        Subject subject = new Subject();
        Professor professor = new Professor();
        Thesis thesis = new Thesis(student, subject, professor);
        assertEquals(student, thesis.getStudent());
        assertEquals(subject, thesis.getSubject());
        assertEquals(professor, thesis.getProfessor());
    }
}