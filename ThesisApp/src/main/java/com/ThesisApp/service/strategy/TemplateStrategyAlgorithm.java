package com.ThesisApp.service.strategy;

import com.ThesisApp.model.Application;
import org.springframework.stereotype.Service;

import java.util.List;

public abstract class TemplateStrategyAlgorithm {

        protected abstract Application compareApplications(List<Application> applications);

        public Application findBestApplicant(List<Application> applications) {
            return compareApplications(applications);
        }

}
