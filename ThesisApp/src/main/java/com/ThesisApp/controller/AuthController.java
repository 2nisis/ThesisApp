package com.ThesisApp.controller;

import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.Role;
import com.ThesisApp.model.User;
import com.ThesisApp.service.ProfessorService;
import com.ThesisApp.service.StudentService;
import com.ThesisApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;


    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/index")
    public String redirectToIndex() {

        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "/auth/login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("student", new Student());
        model.addAttribute("professor", new Professor());
        return "/auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value = "studentName", required = false) String studentName,
                               @RequestParam(value = "surname", required = false) String studentSurname,
                               @RequestParam(value = "yearOfStudies", required = false) Integer yearOfStudies,
                               @RequestParam(value = "currentAverageGrade", required = false) Integer averageGrade,
                               @RequestParam(value = "remainingCourses", required = false) Integer remainingCourses,
                               @RequestParam(value = "professorName", required = false) String professorName,
                               @RequestParam(value = "professorSurname", required = false) String professorSurname,
                               @RequestParam(value = "professorEmail", required = false) String professorEmail,
                               Model model) {


        User savedUser = userService.saveUser(user);

        if (savedUser != null) {

            if (user.getRole() == Role.STUDENT) {

                Student student = new Student();
                student.setUser(savedUser);
                student.setName(studentName);
                student.setSurname(studentSurname);
                student.setYearOfStudies(yearOfStudies);
                student.setAverageGrade(averageGrade);
                student.setRemainingCourses(remainingCourses);

                studentService.saveStudent(student);
                return "redirect:/student_dashboard";

            } else if (user.getRole() == Role.PROFESSOR) {

                Professor professor = new Professor();
                professor.setUser(savedUser);
                professor.setName(professorName);
                professor.setSurname(professorSurname);
                professor.setEmail(professorEmail);

                professorService.saveProfile(professor);
                return "redirect:/professor_dashboard";
            }
        }

        return "redirect:/login";
    }
}