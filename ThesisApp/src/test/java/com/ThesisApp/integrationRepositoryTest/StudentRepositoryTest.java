package com.ThesisApp.integrationRepositoryTest;

import com.ThesisApp.model.User;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Role;
import com.ThesisApp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testStudentRepository() {
        User user = new User("dionisis", "dionisis", Role.STUDENT);
        entityManager.persistAndFlush(user);

        Student student = new Student("dionisis", "kaisaris", 3, 85, 5, user, null, null);
        entityManager.persistAndFlush(student);

        Optional<Student> foundStudent = studentRepository.findById(student.getId());
        Student foundStudentByUser = studentRepository.findByUser(Optional.of(user));
        List<Student> allStudents = studentRepository.findAll();

        assertTrue(foundStudent.isPresent());
        assertEquals(student.getId(), foundStudent.get().getId());
        assertEquals(student.getId(), foundStudentByUser.getId());
        assertFalse(allStudents.isEmpty());
    }
}
