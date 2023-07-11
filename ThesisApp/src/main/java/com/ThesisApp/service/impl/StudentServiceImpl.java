package com.ThesisApp.service.impl;

import com.ThesisApp.exceptions.ApplicationAlreadyExistsException;
import com.ThesisApp.model.Application;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Subject;
import com.ThesisApp.model.User;
import com.ThesisApp.repository.ApplicationRepository;
import com.ThesisApp.repository.StudentRepository;
import com.ThesisApp.repository.SubjectRepository;
import com.ThesisApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentDAO;
    private SubjectRepository subjectDAO;
    private ApplicationRepository applicationDAO;
    private UserServiceImpl userService;
    private ThesisServiceImpl thesisService;
    private SubjectServiceImpl subjectService;
    private ApplicationServiceImpl applicationService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentDAO,
                              SubjectRepository subjectDAO,
                              ApplicationRepository applicationDAO,
                              UserServiceImpl userService,
                              ThesisServiceImpl thesisService,
                              SubjectServiceImpl subjectService,
                              ApplicationServiceImpl applicationService) {
        this.studentDAO = studentDAO;
        this.subjectDAO = subjectDAO;
        this.applicationDAO = applicationDAO;
        this.userService = userService;
        this.thesisService = thesisService;
        this.subjectService = subjectService;
        this.applicationService = applicationService;
    }

    @Override
    public Student saveStudent(Student student ) {
        return studentDAO.save(student);
    }
    public Student updateStudent(String username, Student beanStudent) {
        User user = userService.findByUsername(username);
        Student student = findByUser(Optional.ofNullable(user));

        student.setName(beanStudent.getName());
        student.setSurname(beanStudent.getSurname());
        student.setYearOfStudies(beanStudent.getYearOfStudies());
        student.setAverageGrade(beanStudent.getAverageGrade());
        student.setRemainingCourses(beanStudent.getRemainingCourses());
        return saveStudent(student);
    }

    @Override
    public Student findById(Long id) {
        return studentDAO.findById(id).orElse(null);
    }

    public Map<String, Object> getDashboardData(String username) {
        User user = userService.findByUsername(username);
        Student student = findByUser(Optional.ofNullable(user));
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("student", student);
        dashboardData.put("availableSubjects", subjectService.findAllAvailableSubjects());
        dashboardData.put("studentApplications", applicationService.findByStudentId(student.getId()));
        return dashboardData;
    }


    @Override
    public void applyToSubject(Long studentId, Long subjectId) {

        Application alreadyExists = applicationDAO.findByStudentIdAndSubjectId(studentId, subjectId);
        if(alreadyExists != null){
            throw new ApplicationAlreadyExistsException("You have already applied for this Thesis subject.");
        }

        Student student = studentDAO.findById(studentId).orElse(null);
        Subject subject = subjectDAO.findById(subjectId).orElse(null);

        if (student != null && subject != null) {
            Application application = new Application();
            application.setStudent(student);
            application.setSubject(subject);
            applicationDAO.save(application);
        }
    }


    @Override
    public Student findByUser(Optional<User> user){return studentDAO.findByUser(user); }
}
