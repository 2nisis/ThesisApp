package com.ThesisApp.controller;

import com.ThesisApp.exceptions.ApplicationNotFoundException;
import com.ThesisApp.model.*;
import com.ThesisApp.service.*;
import com.ThesisApp.service.strategy.BestApplicantStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private BestApplicantStrategyFactory strategyFactory;


    @GetMapping("/professor_dashboard")
    public String professorDashboard(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        Professor professor = professorService.findByUser(Optional.ofNullable(user));
        model.addAttribute("professor", professor);
        model.addAttribute("professorId", professor.getId());

        List<Subject> subjects = subjectService.findAllByProfessorId(professor.getId());
        List<Subject> availableSubjects = subjectService.findAvailableSubjectsByProfessorId(professor.getId());
        model.addAttribute("subjects", subjects);
        model.addAttribute("AvailableSubjects", availableSubjects);

        List<Thesis> theses = thesisService.findAllByProfessorId(professor.getId());
        model.addAttribute("theses", theses);

        return "professor/professor_dashboard";
    }

    @GetMapping("/professor_profile")
    public String retrieveProfile(@RequestParam("id") Long professorId, Model model) {

        Professor professor = professorService.retrieveProfile(professorId);
        model.addAttribute("professor", professor);
        model.addAttribute("professorId", professor.getId());

        return "/professor/professor_profile";
    }

    @PostMapping("/professor_profile")
    public String saveProfile(@ModelAttribute("professor") Professor beanProfessor, Principal principal) {
        professorService.updateProfile(principal.getName(), beanProfessor);
        return "redirect:/professor_dashboard";
    }

    @GetMapping("/subjects")
    public String listProfessorSubjects(Model model, Principal principal) {
        List<Subject> subjects = professorService.listProfessorSubjects(principal.getName());
        model.addAttribute("subjects", subjects);
        return "/professor/subjects";
    }

    @GetMapping("/subjects_form")
    public String showSubjectForm(Model model) {

        model.addAttribute("subject", new Subject());
        return "/professor/subjects_form";
    }

    @PostMapping("/add_subject")
    public String addSubject(@ModelAttribute("subject") Subject subject, Principal principal) {
        professorService.addSubject(subject, principal.getName());
        return "redirect:/subjects";
    }

    @GetMapping("/delete_subject")
    public String deleteSubject(@RequestParam("id") Long subjectId) {
        subjectService.deleteById(subjectId);
        return "redirect:/subjects";

    }

    @GetMapping("/view_applications")
    public String listApplications(@RequestParam("id") Long subjectId, Model model) {

        Subject subject = subjectService.findById(subjectId);
        List<Application> applications = applicationService.findBySubjectId(subjectId);
        if (applications == null) {
            throw new ApplicationNotFoundException("There are no Applicants yet! have patience.");
        }
        model.addAttribute("subject", subject);
        model.addAttribute("applications", applications);

        return "/professor/view_applications";
    }

    @GetMapping("/all_theses")
    public String listProfessorTheses(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Thesis> theses = professorService.listProfessorTheses(user);
        model.addAttribute("theses", theses);
        return "/professor/all_theses";
    }

    @PostMapping("/set_grades")
    public String setGrades(@RequestParam("thesisId") Long thesisId,
                            @RequestParam("implementationGrade") Double implementationGrade,
                            @RequestParam("reportGrade") Double reportGrade,
                            @RequestParam("presentationGrade") Double presentationGrade) {
        thesisService.setGrades(thesisId, implementationGrade, reportGrade, presentationGrade);
        return "redirect:/all_theses";
    }

    @PostMapping("/assign_applicant")
    public String assignApplicant(@RequestParam("studentId") Long studentId,
                                  @RequestParam("subjectId") Long subjectId,
                                  Model model) {
        Application application = applicationService.findByStudentIdAndSubjectId(studentId ,subjectId );
        Thesis thesis = professorService.assignApplicant(application);
        model.addAttribute("theses" , thesis);
        return "redirect:/professor_dashboard";
    }

    @GetMapping("/assign_applicant/{strategy}")
    public String assignApplicantByStrategy(@PathVariable("strategy") String strategyType,
                                            @RequestParam("id") Long subjectId, Model model) {

        model.addAttribute("theses" , professorService.assignApplicantByStrategy(subjectId, strategyType));

        return "redirect:/professor_dashboard";
    }


    @GetMapping("/assign_applicant/threshold_form")
    public String showThresholdForm(@RequestParam("id") Long subjectId , Model model) {
        Subject subject = subjectService.findById(subjectId);
        model.addAttribute("subject", subject);
        return "professor/threshold_form";
    }

    @PostMapping("/assign_applicant/threshold")
    public String assignApplicantThresholdStrategy(@RequestParam("id") Long subjectId,
                                                   @RequestParam("coursesThreshold") Double coursesThreshold,
                                                   @RequestParam("gradeThreshold") Double gradeThreshold,
                                                   Model model) {

        Subject subject = subjectService.findById(subjectId);
        subject.setCoursesThreshold(coursesThreshold);
        subject.setGradeThreshold(gradeThreshold);

        return assignApplicantByStrategy( "AverageGradeRemainingCoursesGvnThreshold" , subjectId , model);
    }


}