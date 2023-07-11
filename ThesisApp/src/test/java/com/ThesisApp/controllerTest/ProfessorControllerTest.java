package com.ThesisApp.controllerTest;
import com.ThesisApp.controller.ProfessorController;
import com.ThesisApp.model.*;
import com.ThesisApp.service.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProfessorControllerTest {

    @InjectMocks
    private ProfessorController professorController;

    @Mock
    private ProfessorService professorService;

    @Mock
    private UserService userService;

    @Mock
    private SubjectService subjectService;

    @Mock
    private ApplicationService applicationService;

    @Mock
    private ThesisService thesisService;


    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void professorDashboard() {
        String name = "name";
        User user = new User();
        Professor professor = new Professor();
        when(principal.getName()).thenReturn(name);
        when(userService.findByUsername(name)).thenReturn(user);
        when(professorService.findByUser(Optional.of(user))).thenReturn(professor);

        String viewName = professorController.professorDashboard(model, principal);
        assertEquals("professor/professor_dashboard", viewName);
        verify(model).addAttribute("professor", professor);
    }

    @Test
    public void listProfessorSubjects() {
        String name = "name";
        when(principal.getName()).thenReturn(name);
        when(professorService.listProfessorSubjects(name)).thenReturn(Collections.emptyList());

        String viewName = professorController.listProfessorSubjects(model, principal);
        assertEquals("/professor/subjects", viewName);
        verify(model).addAttribute("subjects", Collections.emptyList());
    }

    @Test
    public void addSubject() {
        Subject subject = new Subject();
        String name = "name";
        when(principal.getName()).thenReturn(name);

        String viewName = professorController.addSubject(subject, principal);
        assertEquals("redirect:/subjects", viewName);
        verify(professorService).addSubject(subject, name);
    }

    @Test
    public void retrieveProfile() {
        Professor professor = new Professor();
        when(professorService.retrieveProfile(professor.getId())).thenReturn(professor);

        String viewName = professorController.retrieveProfile(professor.getId(), model);
        assertEquals("/professor/professor_profile", viewName);
        verify(model).addAttribute("professor", professor);
        verify(model).addAttribute("professorId", professor.getId());
    }

    @Test
    public void saveProfile() {
        Professor professor = new Professor();
        String name = "name";
        when(principal.getName()).thenReturn(name);

        String viewName = professorController.saveProfile(professor, principal);
        assertEquals("redirect:/professor_dashboard", viewName);
        verify(professorService).updateProfile(name, professor);
    }

    @Test
    public void deleteSubject() {
        String viewName = professorController.deleteSubject(anyLong());
        assertEquals("redirect:/subjects", viewName);
        verify(subjectService).deleteById(anyLong());
    }

    @Test
    public void listApplications() {
        Subject subject = new Subject();
        when(subjectService.findById(subject.getId())).thenReturn(subject);
        when(applicationService.findBySubjectId(subject.getId())).thenReturn(Collections.emptyList());

        String viewName = professorController.listApplications(subject.getId(), model);
        assertEquals("/professor/view_applications", viewName);
        verify(model).addAttribute("subject", subject);
        verify(model).addAttribute("applications", Collections.emptyList());
    }

    @Test
    public void listProfessorTheses() {
        String name = "name";
        User user = new User();
        when(principal.getName()).thenReturn(name);
        when(userService.findByUsername(name)).thenReturn(user);
        when(professorService.listProfessorTheses(user)).thenReturn(Collections.emptyList());

        String viewName = professorController.listProfessorTheses(model, principal);
        assertEquals("/professor/all_theses", viewName);
        verify(model).addAttribute("theses", Collections.emptyList());
    }

    @Test
    public void setGrades() {
        Thesis thesis = new Thesis();
        Double implementationGrade = 5.0;
        Double reportGrade = 5.0;
        Double presentationGrade = 5.0;

        String viewName = professorController.setGrades(thesis.getId(), implementationGrade, reportGrade, presentationGrade);
        assertEquals("redirect:/all_theses", viewName);
        verify(thesisService).setGrades(thesis.getId(), implementationGrade, reportGrade, presentationGrade);
    }

    @Test
    public void assignApplicant() {
        Student student = new Student();
        Subject subject = new Subject();
        Application application = new Application();
        Thesis thesis = new Thesis();
        when(applicationService.findByStudentIdAndSubjectId(student.getId() ,subject.getId() )).thenReturn(application);
        when(professorService.assignApplicant(application)).thenReturn(thesis);

        String viewName = professorController.assignApplicant(student.getId() , subject.getId(), model);
        assertEquals("redirect:/professor_dashboard", viewName);
        verify(model).addAttribute("theses" , thesis);
    }

}
