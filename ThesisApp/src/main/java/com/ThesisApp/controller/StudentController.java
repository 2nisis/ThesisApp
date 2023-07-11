package com.ThesisApp.controller;

import com.ThesisApp.model.*;
import com.ThesisApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/student_dashboard")
    public String getStudentDashboard(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Student student = studentService.findByUser(Optional.ofNullable(user));
        Thesis thesis = thesisService.findByStudentId(student.getId());
        if (thesis != null) {
            model.addAttribute("thesis", thesis);
            return "redirect:/thesis_template";
        }
        model.addAllAttributes(studentService.getDashboardData(principal.getName()));
        return "student/student_dashboard";
    }

    @GetMapping("/thesis_template")
    public String getThesisTemplate(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Student student = studentService.findByUser(Optional.ofNullable(user));
        Thesis thesis = thesisService.findByStudentId(student.getId());
        model.addAttribute("thesis", thesis);
        return "/student/thesis_template";
    }

    @GetMapping("/student_profile")
    public String getStudentProfile(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        Student student = studentService.findByUser(Optional.ofNullable(user));
        model.addAttribute("student", student);
        return "/student/student_profile";

    }

    @PostMapping("/student_profile")
    public String updateStudentProfile(@ModelAttribute("student") Student beanStudent, Model model , Principal principal) {
        Student updatedStudent = studentService.updateStudent(principal.getName(), beanStudent);
        model.addAttribute("student", updatedStudent);
        return "redirect:/student_dashboard";
    }

    @GetMapping("/available_subjects")
    public String listAvailableSubjects(Model model, Principal principal) {
        List<Subject> availableSubjects = subjectService.findAll();
        model.addAttribute("availableSubjects", availableSubjects);
        return "/student/available_subjects";
    }

    @PostMapping("/apply_subject")
    public String applyToSubject(@RequestParam("subjectId") Long subjectId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Student student = studentService.findByUser(Optional.ofNullable(user));
        studentService.applyToSubject(student.getId(), subjectId);
        return "redirect:/student_dashboard";
    }

    @GetMapping("/student_applications")
    public String listStudentApplications(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Student student = studentService.findByUser(Optional.ofNullable(user));
        List<Application> applications = applicationService.findByStudentId(student.getId());
        model.addAttribute("studentApplications", applications);
        return "student/student_applications";
    }
    @GetMapping("/professor_details")
    public String getProfessorDetails(@RequestParam("id") Long professorId, Model model) {
        Professor professor = professorService.findById(professorId).orElse(null);
        model.addAttribute("professor", professor);
        return "/student/professor_details";
    }

}
