package com.ThesisApp.repository;

import com.ThesisApp.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByProfessorId(Long professorId);
    List<Subject> findAll();
    Subject save(Subject subject);
    void deleteById(Long id);

    List<Subject> findByAvailableTrue();
    List<Subject> findByProfessorIdAndAvailableTrue(Long professorId);
}
