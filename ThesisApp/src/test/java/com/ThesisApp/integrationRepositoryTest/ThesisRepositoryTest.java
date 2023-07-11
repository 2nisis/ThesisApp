package com.ThesisApp.integrationRepositoryTest;


import com.ThesisApp.model.Subject;
import com.ThesisApp.model.Thesis;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Professor;
import com.ThesisApp.repository.ThesisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ThesisRepositoryTest {

    @Autowired
    private ThesisRepository thesisRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testThesisRepository() {
        Professor professor = new Professor("apostolos", "zarras", "zarras@cs.uoi.gr", null, null, null);
        entityManager.persistAndFlush(professor);

        Student student = new Student("dionisis", "kaisaris", 3, 85, 5, null, null, null);
        entityManager.persistAndFlush(student);
        Subject subject = new Subject("softwareEngineering", "Subject Description", professor, null);
        entityManager.persistAndFlush(subject);

        Thesis thesis = new Thesis(student, subject, professor);
        entityManager.persistAndFlush(thesis);

        Optional<Thesis> foundThesis = thesisRepository.findById(thesis.getId());
        Optional<Thesis> thesisByStudent = thesisRepository.findByStudentId(student.getId());
        List<Thesis> thesesByProfessor = thesisRepository.findByProfessorId(professor.getId());
        Thesis thesisBySubject = thesisRepository.findBySubjectId(subject.getId());

        assertTrue(foundThesis.isPresent());
        assertEquals(thesis.getId(), foundThesis.get().getId());

        assertTrue(thesisByStudent.isPresent());
        assertEquals(thesis.getId(), thesisByStudent.get().getId());

        assertFalse(thesesByProfessor.isEmpty());
        assertEquals(thesis.getId(), thesesByProfessor.get(0).getId());

        assertEquals(thesis.getId(), thesisBySubject.getId());
    }
}
