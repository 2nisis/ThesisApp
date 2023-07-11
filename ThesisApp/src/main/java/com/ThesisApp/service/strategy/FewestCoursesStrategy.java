package com.ThesisApp.service.strategy;

import com.ThesisApp.model.Application;
import com.ThesisApp.model.Student;
import com.ThesisApp.service.BestApplicantStrategy;
import com.ThesisApp.service.strategy.TemplateStrategyAlgorithm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FewestCoursesStrategy extends TemplateStrategyAlgorithm implements BestApplicantStrategy {

    @Override
    protected Application compareApplications(List<Application> applications) {

        if (applications.isEmpty()) {
            return null;
        }

        Application bestApplication = null;
        int fewestCourses = 100;

        for (Application application : applications) {

            Student student  = application.getStudent();
            if (student.getRemainingCourses() < fewestCourses) {
                fewestCourses = student.getRemainingCourses();
                bestApplication = application;
            }
        }
        return bestApplication;
    }

}

