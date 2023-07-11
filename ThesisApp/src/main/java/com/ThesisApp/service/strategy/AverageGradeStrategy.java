package com.ThesisApp.service.strategy;

import com.ThesisApp.model.Application;
import com.ThesisApp.model.Student;
import com.ThesisApp.service.BestApplicantStrategy;
import com.ThesisApp.service.strategy.TemplateStrategyAlgorithm;

import java.util.List;

public class AverageGradeStrategy extends TemplateStrategyAlgorithm implements BestApplicantStrategy {

    protected Application compareApplications(List<Application> applications) {

        Application bestApplication = null;
        int fewestCourses = 100;

        for (Application application : applications) {

            Student student = application.getStudent();
            if (student.getRemainingCourses() < fewestCourses) {
                fewestCourses = student.getRemainingCourses();
                bestApplication = application;
            }

        }
        return bestApplication;
    }
}
