package com.ThesisApp.service;

import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Subject;
import com.ThesisApp.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);

    public Student updateStudent(String username, Student beanStudent);
    Student findByUser(Optional<User> user);
    Student findById(Long id);
    void applyToSubject(Long studentId, Long subjectId);

    public Map<String, Object> getDashboardData(String username);

}
