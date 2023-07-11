package com.ThesisApp.service;

import com.ThesisApp.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ProfessorService {

    Optional<Professor> findById(Long id);
    Professor findByUser(Optional<User> user);

    Professor retrieveProfile(Long id);
    void saveProfile(Professor professor);
    void updateProfile(String string , Professor bean);
    List<Subject> listProfessorSubjects(String user);
    void addSubject(Subject subject, String username);

    List<Thesis> listProfessorTheses(User user);
    Thesis assignApplicant(Application application);

    Thesis assignApplicantByStrategy(Long subjectId, String strategyType);

}
