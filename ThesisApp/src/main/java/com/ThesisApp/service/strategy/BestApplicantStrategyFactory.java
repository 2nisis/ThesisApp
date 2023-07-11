package com.ThesisApp.service.strategy;

import com.ThesisApp.service.BestApplicantStrategy;
import org.springframework.stereotype.Service;

@Service
public class BestApplicantStrategyFactory {

    public BestApplicantStrategy createStrategy(String strategyType) {
        switch (strategyType) {
            case "AverageGrade":
                return new AverageGradeStrategy();
            case "FewestCourses":
                return new FewestCoursesStrategy();
            case "Random":
                return new RandomStrategy();
            case "AverageGradeRemainingCoursesGvnThreshold":
                return new AverageGradeRemainingCoursesGvnThreshold();
            default:
                throw new IllegalArgumentException("Invalid strategy type: " + strategyType);
        }
    }

}
