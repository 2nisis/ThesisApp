package com.ThesisApp.service.impl;
import com.ThesisApp.model.Application;
import com.ThesisApp.repository.ApplicationRepository;
import com.ThesisApp.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {


    private ApplicationRepository applicationDAO;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public List<Application> findByStudentId(Long studentId) {
        return applicationDAO.findByStudentId(studentId);
    }

    @Override
    public List<Application> findBySubjectId(Long subjectId) {
        return applicationDAO.findBySubjectId(subjectId);
    }

    @Override
    public Application findByStudentIdAndSubjectId(Long studentId , Long subjectId){return applicationDAO.findByStudentIdAndSubjectId(studentId ,subjectId );}

    public void deleteApplication(Application application){ applicationDAO.delete(application);}




    public void deleteAllByStudentId(Long studentId) {

        List<Application> applications = applicationDAO.findByStudentId(studentId);
        for (Application application : applications) {
            applicationDAO.delete(application);
        }
    }
    public void deleteAllBySubjectId(Long subjectId){

        List<Application> applications = applicationDAO.findBySubjectId(subjectId);
        for(Application application : applications){
            applicationDAO.delete(application);
        }
    }
}
