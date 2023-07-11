package com.ThesisApp.service;

import com.ThesisApp.model.Application;
import com.ThesisApp.model.Subject;
import com.ThesisApp.model.Thesis;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    Subject findById(Long id);
    List<Subject> findAllByProfessorId(Long professorId);
    Subject save(Subject subject);
    void deleteById(Long id);

    List<Subject> findAll();

    List<Subject> findAllAvailableSubjects();

    List<Subject> findAvailableSubjectsByProfessorId(Long professorId);

}
