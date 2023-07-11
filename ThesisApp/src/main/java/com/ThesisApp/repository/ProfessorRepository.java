package com.ThesisApp.repository;

import com.ThesisApp.model.Professor;
import com.ThesisApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findById(Long id);

    Professor findByUser(Optional<User> user);
}
