package com.ThesisApp.service.impl;

import com.ThesisApp.model.Thesis;
import com.ThesisApp.repository.ThesisRepository;
import com.ThesisApp.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService {


    private ThesisRepository thesisDAO;

    @Autowired
    public ThesisServiceImpl(ThesisRepository thesisDAO) {
        this.thesisDAO = thesisDAO;
    }

    @Override
    public Thesis findByStudentId(Long studentId) {
        return thesisDAO.findByStudentId(studentId).orElse(null);
    }

    @Override
    public List<Thesis> findAllByProfessorId(Long professorId) {
        return thesisDAO.findByProfessorId(professorId);
    }


    public Thesis findById(Long thesisId){
        return thesisDAO.findById(thesisId).orElse(null);
    }


    public void saveThesis(Thesis thesis){ thesisDAO.save(thesis);}

    @Override
    public void setGrades(Long thesisId, Double implementationGrade, Double reportGrade, Double presentationGrade) {
        Thesis thesis = thesisDAO.findById(thesisId).orElse(null);
        thesis.setImplementationGrade(implementationGrade);
        thesis.setReportGrade(reportGrade);
        thesis.setPresentationGrade(presentationGrade);
        Double overallGrade = (implementationGrade + reportGrade + presentationGrade) / 3;
        thesis.setOverallGrade(overallGrade);
        thesisDAO.save(thesis);
    }

}

