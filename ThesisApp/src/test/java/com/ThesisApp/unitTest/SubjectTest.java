package com.ThesisApp.unitTest;

import com.ThesisApp.model.Subject;
import com.ThesisApp.model.Professor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubjectTest {
    @Test
    public void testSubjectCreation() {
        Professor professor = new Professor();
        Subject subject = new Subject("title", "description", professor, null);
        assertEquals("title", subject.getTitle());
        assertEquals("description", subject.getDescription());
        assertEquals(professor, subject.getProfessor());
    }
}

