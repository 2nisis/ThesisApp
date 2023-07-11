package com.ThesisApp.service.impl;

import com.ThesisApp.exceptions.ApplicationNotFoundException;
import com.ThesisApp.model.*;
import com.ThesisApp.repository.ApplicationRepository;
import com.ThesisApp.repository.ProfessorRepository;
import com.ThesisApp.repository.SubjectRepository;
import com.ThesisApp.repository.ThesisRepository;
import com.ThesisApp.service.ApplicationService;
import com.ThesisApp.service.BestApplicantStrategy;
import com.ThesisApp.service.ProfessorService;
import com.ThesisApp.service.ThesisService;
import com.ThesisApp.service.strategy.BestApplicantStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorDAO;
    private SubjectRepository subjectDAO;
    private ApplicationRepository applicationDAO;
    private ThesisRepository thesisDAO;
    private UserServiceImpl userService;
    private ApplicationService applicationService;
    private ThesisService thesisService;
    private BestApplicantStrategyFactory strategyFactory;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorDAO,
                                SubjectRepository subjectDAO,
                                ApplicationRepository applicationDAO,
                                ThesisRepository thesisDAO,
                                UserServiceImpl userService,
                                ApplicationService applicationService,
                                ThesisService thesisService,
                                BestApplicantStrategyFactory strategyFactory) {
        this.professorDAO = professorDAO;
        this.subjectDAO = subjectDAO;
        this.applicationDAO = applicationDAO;
        this.thesisDAO = thesisDAO;
        this.userService = userService;
        this.applicationService = applicationService;
        this.thesisService = thesisService;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public Optional<Professor> findById(Long id){
        return professorDAO.findById(id);
    }
    @Override
    public Professor findByUser(Optional<User> user) {
        return professorDAO.findByUser(user);
    }

    @Override
    public Professor retrieveProfile(Long id) {
        return professorDAO.findById(id).orElse(null);
    }

    @Override
    public void saveProfile(Professor professor) {
        professorDAO.save(professor);
    }

    @Override
    public void updateProfile(String username, Professor beanProfessor) {

        User user = userService.findByUsername(username);
        Professor professor = findByUser(Optional.ofNullable(user));

        if(professor != null) {
            professor.setName(beanProfessor.getName());
            professor.setSurname(beanProfessor.getSurname());
            professor.setEmail(beanProfessor.getEmail());
            saveProfile(professor);
        }
    }
    @Override
    public List<Subject> listProfessorSubjects(String username) {
        User user = userService.findByUsername(username);
        Professor professor = this.findByUser(Optional.ofNullable(user));
        return subjectDAO.findByProfessorId(professor.getId());
    }
    @Override
    public void addSubject(Subject subject, String username) {
        User user = userService.findByUsername(username);
        Professor professor = this.findByUser(Optional.ofNullable(user));
        subject.setProfessor(professor);
        subject.setAvailable();
        subjectDAO.save(subject);
    }

    @Override
    public List<Thesis> listProfessorTheses(User user) {
        Professor professor = professorDAO.findByUser(Optional.ofNullable(user));
        return thesisDAO.findByProfessorId(professor.getId());
    }


    @Override
    public Thesis assignApplicant(Application application) {
        application.getSubject().setUnavailable();

        Thesis thesis = new Thesis();
        thesis.setSubject(application.getSubject());
        thesis.setStudent(application.getStudent());
        thesis.setProfessor(application.getSubject().getProfessor());

        applicationService.deleteApplication(application);
        applicationService.deleteAllByStudentId(application.getStudent().getId());
        applicationService.deleteAllBySubjectId(application.getSubject().getId());

        thesisService.saveThesis(thesis);
        return thesis;
    }

    @Override
    public Thesis assignApplicantByStrategy(Long subjectId, String strategyType){
        List<Application> applications = applicationService.findBySubjectId(subjectId);

        if (applications.isEmpty()) {
            throw new ApplicationNotFoundException("There are no applicants yet. Be patient. ");
        }

        BestApplicantStrategy strategy = strategyFactory.createStrategy(strategyType);
        Application bestApplicant = strategy.findBestApplicant(applications);

        return assignApplicant(bestApplicant);
    }

}
