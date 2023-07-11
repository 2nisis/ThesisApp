package com.ThesisApp.integrationRepositoryTest;

import com.ThesisApp.model.Student;
import com.ThesisApp.model.Subject;
import com.ThesisApp.model.Application;
import com.ThesisApp.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ApplicationRepositoryTest {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testApplicationRepository() {
        Student student = new Student();
        Subject subject = new Subject();
        entityManager.persistAndFlush(student);
        entityManager.persistAndFlush(subject);

        Application application = new Application(student, subject);
        entityManager.persistAndFlush(application);

        List<Application> applicationsByStudent = applicationRepository.findByStudentId(student.getId());
        List<Application> applicationsBySubject = applicationRepository.findBySubjectId(subject.getId());
        Application specificApplication = applicationRepository.findByStudentIdAndSubjectId(student.getId(), subject.getId());

        assertFalse(applicationsByStudent.isEmpty());
        assertFalse(applicationsBySubject.isEmpty());
        assertEquals(application.getId(), specificApplication.getId());
    }
}
