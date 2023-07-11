package com.ThesisApp.service.strategy;

import com.ThesisApp.exceptions.HighStandardsException;
import com.ThesisApp.model.Application;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Subject;
import com.ThesisApp.service.BestApplicantStrategy;
import com.ThesisApp.service.strategy.TemplateStrategyAlgorithm;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AverageGradeRemainingCoursesGvnThreshold extends TemplateStrategyAlgorithm implements BestApplicantStrategy {

    @Override
    protected Application compareApplications(List<Application> applications) {

        Application bestApplication = null;

        for (Application application : applications) {

            Student student  = application.getStudent();
            Subject subject = application.getSubject();
            if (student.getRemainingCourses() <= subject.getCoursesThreshold() && student.getAverageGrade() >= subject.getGradeThreshold() ) {

                bestApplication = application;
            }
        }
        if (bestApplication == null){
            throw new HighStandardsException("There are no students filling your criteria.");
        }
        return bestApplication; //it will throw a null pointer exception 500
    }
}
