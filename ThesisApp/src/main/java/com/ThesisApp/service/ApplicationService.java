package com.ThesisApp.service;

import com.ThesisApp.model.Application;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Subject;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<Application> findByStudentId(Long studentId);
    List<Application> findBySubjectId(Long subjectId);

    Application findByStudentIdAndSubjectId(Long studentId , Long subjectId);

    public void deleteApplication(Application application);
    public void deleteAllByStudentId(Long studentId);

    public void deleteAllBySubjectId(Long subjectId);
}
