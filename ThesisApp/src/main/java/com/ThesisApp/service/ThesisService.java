package com.ThesisApp.service;

import com.ThesisApp.model.Thesis;

import java.util.List;

public interface ThesisService {

    Thesis findById(Long Thesis);
    Thesis findByStudentId(Long studentId);
    List<Thesis> findAllByProfessorId(Long professorId);

    void saveThesis(Thesis thesis);

    void setGrades(Long thesisId, Double implementationGrade, Double reportGrade, Double presentationGrade);
}
