package com.ThesisApp.controllerTest;

import com.ThesisApp.controller.StudentController;
import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Thesis;
import com.ThesisApp.model.User;
import com.ThesisApp.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class StudentControllerTest {

    @InjectMocks
    StudentController studentController;

    @Mock
    StudentService studentService;

    @Mock
    UserService userService;

    @Mock
    ThesisService thesisService;

    @Mock
    ProfessorService professorService;

    @Mock
    Model model;

    @Mock
    Principal principal;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStudentDashboard() {
        when(principal.getName()).thenReturn("testUser");
        when(userService.findByUsername(anyString())).thenReturn(new User());
        when(studentService.findByUser(any())).thenReturn(new Student());
        when(thesisService.findByStudentId(anyLong())).thenReturn(null);
        studentController.getStudentDashboard(model, principal);
        verify(model, times(1)).addAllAttributes(anyMap());
    }

    @Test
    public void testGetThesisTemplate() {
        when(principal.getName()).thenReturn("testUser");
        when(userService.findByUsername(anyString())).thenReturn(new User());
        when(studentService.findByUser(any())).thenReturn(new Student());
        when(thesisService.findByStudentId(anyLong())).thenReturn(new Thesis());
        studentController.getThesisTemplate(model, principal);
        verify(model, times(1)).addAttribute(eq("thesis"), any());
    }

    @Test
    public void testGetStudentProfile() {
        when(principal.getName()).thenReturn("testUser");
        when(userService.findByUsername(anyString())).thenReturn(new User());
        when(studentService.findByUser(any())).thenReturn(new Student());
        studentController.getStudentProfile(model, principal);
        verify(model, times(1)).addAttribute(eq("student"), any());
    }

    @Test
    public void testUpdateStudentProfile() {
        Student student = new Student();
        when(principal.getName()).thenReturn("testUser");
        when(studentService.updateStudent(anyString(), any())).thenReturn(new Student());
        studentController.updateStudentProfile(student, model, principal);
        verify(model, times(1)).addAttribute(eq("student"), any());
    }


    @Test
    public void testGetProfessorDetails() {
        when(professorService.findById(anyLong())).thenReturn(Optional.of(new Professor()));
        studentController.getProfessorDetails(anyLong(), model);
        verify(model, times(1)).addAttribute(eq("professor"), any());
    }
}

