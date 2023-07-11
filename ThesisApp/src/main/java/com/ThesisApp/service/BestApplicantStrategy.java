package com.ThesisApp.service;

import com.ThesisApp.model.Application;

import java.util.List;

public interface BestApplicantStrategy {

    Application findBestApplicant(List<Application> applications);

}
