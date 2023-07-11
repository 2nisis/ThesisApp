package com.ThesisApp.repository;

import com.ThesisApp.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {

    Optional<Thesis> findById(Long id);
    Optional<Thesis> findByStudentId(Long studentId);
    List<Thesis> findByProfessorId(Long professorId);
    public Thesis findBySubjectId(Long subjectId);


    void deleteByStudentId(Long studentId);
}