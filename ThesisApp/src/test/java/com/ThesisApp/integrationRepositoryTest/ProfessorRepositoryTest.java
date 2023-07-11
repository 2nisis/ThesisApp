package com.ThesisApp.integrationRepositoryTest;

import com.ThesisApp.model.Role;
import com.ThesisApp.model.User;
import com.ThesisApp.model.Subject;
import com.ThesisApp.model.Thesis;
import com.ThesisApp.model.Professor;
import com.ThesisApp.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testProfessorRepository() {

        User user = new User("zarras", "zarras", Role.PROFESSOR);
        List<Subject> subjects = new ArrayList<Subject>();
        List<Thesis> theses = new ArrayList<Thesis>();
        Professor professor = new Professor("zarras" , "apostolos" , "zarras@cs.uoi.gr" , user , subjects , theses );


        entityManager.persistAndFlush(user);
        entityManager.persistAndFlush(professor);

        Professor foundProfessor = professorRepository.findByUser(Optional.ofNullable(user));
        assertEquals(professor, foundProfessor);
    }
}
