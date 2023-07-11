package com.ThesisApp.controllerTest;
import com.ThesisApp.controller.AuthController;
import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Role;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.User;
import com.ThesisApp.service.ProfessorService;
import com.ThesisApp.service.StudentService;
import com.ThesisApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Mock
    private StudentService studentService;

    @Mock
    private ProfessorService professorService;

    @Mock
    private Model model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void index() {
        String viewName = authController.index();
        assertEquals("index", viewName);
    }

    @Test
    public void redirectToIndex() {
        String viewName = authController.redirectToIndex();
        assertEquals("index", viewName);
    }

    @Test
    public void login() {
        String viewName = authController.login();
        assertEquals("/auth/login", viewName);
    }

    @Test
    public void register() {
        String viewName = authController.register(model);
        assertEquals("/auth/register", viewName);
        verify(model, times(1)).addAttribute(eq("user"), any(User.class));
        verify(model, times(1)).addAttribute(eq("student"), any(Student.class));
        verify(model, times(1)).addAttribute(eq("professor"), any(Professor.class));
    }

    @Test
    public void registerUserStudent() {
        User user = new User();
        user.setRole(Role.STUDENT);
        when(userService.saveUser(any(User.class))).thenReturn(user);

        String viewName = authController.registerUser(user, "eleftherios", "kirkos", 9, 85, 15, null, null, null, model);
        assertEquals("redirect:/student_dashboard", viewName);
        verify(studentService, times(1)).saveStudent(any(Student.class));
    }

    @Test
    public void registerUserProfessor() {
        User user = new User();
        user.setRole(Role.PROFESSOR);
        when(userService.saveUser(any(User.class))).thenReturn(user);

        String viewName = authController.registerUser(user, null, null, null, null, null, "apostolos", "zarras", "zarras@cs.uoi.gr", model);
        assertEquals("redirect:/professor_dashboard", viewName);
        verify(professorService, times(1)).saveProfile(any(Professor.class));
    }
}
