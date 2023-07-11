package com.ThesisApp.unitTest;

import com.ThesisApp.model.Student;
import com.ThesisApp.model.Subject;
import com.ThesisApp.model.Application;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
    @Test
    public void testApplicationCreation() {
        Student student = new Student();
        Subject subject = new Subject();
        Application application = new Application(student, subject);
        assertEquals(student, application.getStudent());
        assertEquals(subject, application.getSubject());
    }
}
