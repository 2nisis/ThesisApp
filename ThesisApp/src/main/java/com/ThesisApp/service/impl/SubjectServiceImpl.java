package com.ThesisApp.service.impl;

import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Subject;
import com.ThesisApp.repository.SubjectRepository;
import com.ThesisApp.service.SubjectService;
import com.ThesisApp.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {


    private SubjectRepository subjectDAO;
    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    public Subject findById(Long id) {
        return subjectDAO.findById(id).orElse(null);
    }

    @Override
    public List<Subject> findAllByProfessorId(Long professorId) {
        return subjectDAO.findByProfessorId(professorId);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectDAO.save(subject);
    }

    @Override
    public void deleteById(Long id) {
        subjectDAO.deleteById(id);
    }

    @Override
    public List<Subject> findAll(){ return subjectDAO.findAll(); }

    public List<Subject> findAllAvailableSubjects(){
        return subjectDAO.findByAvailableTrue();
    }

    public List<Subject> findAvailableSubjectsByProfessorId(Long professorId){
        return subjectDAO.findByProfessorIdAndAvailableTrue(professorId);
    }
}
