package com.ThesisApp.service;

import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User saveUser(User user);
    boolean isUserPresent(User user);
    User findById(String id);
    User findByUsername(String username);

    //User registerUser(User user, Student student, Professor professor);
}

