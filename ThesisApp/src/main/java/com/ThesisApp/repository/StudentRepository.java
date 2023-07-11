package com.ThesisApp.repository;

import com.ThesisApp.model.Student;
import com.ThesisApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findById(Long id);
    List<Student> findAll();
    Student findByUser(Optional<User> user);
}
