package com.ThesisApp.integrationRepositoryTest;

import com.ThesisApp.model.*;
import com.ThesisApp.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSubjectRepository() {

        User user = new User("zarras", "zarras", Role.PROFESSOR);
        List<Subject> subjects = new ArrayList<Subject>();
        List<Thesis> theses = new ArrayList<Thesis>();
        Professor professor = new Professor("apostolos", "zarras", "zarras@cs.uoi.gr", user, subjects, theses);
        entityManager.persistAndFlush(user);
        entityManager.persistAndFlush(professor);

        Subject subject = new Subject("SoftwareEngineering", "Subject Description", professor, null);
        entityManager.persistAndFlush(subject);

        List<Subject> subjectsByProfessor = subjectRepository.findByProfessorId(professor.getId());
        List<Subject> allSubjects = subjectRepository.findAll();

        assertFalse(subjectsByProfessor.isEmpty());
        assertFalse(allSubjects.isEmpty());
    }
}
