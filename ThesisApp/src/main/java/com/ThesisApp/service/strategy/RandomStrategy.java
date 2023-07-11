package com.ThesisApp.service.strategy;

import com.ThesisApp.model.Application;
import com.ThesisApp.service.BestApplicantStrategy;
import com.ThesisApp.service.strategy.TemplateStrategyAlgorithm;

import java.util.List;
import java.util.Random;

public class RandomStrategy extends TemplateStrategyAlgorithm implements BestApplicantStrategy {
    private Random random = new Random();

    @Override
    protected Application compareApplications(List<Application> applications){
        return applications.get(random.nextInt(applications.size()));
    }
}
