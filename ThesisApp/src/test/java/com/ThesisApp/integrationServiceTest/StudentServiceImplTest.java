package com.ThesisApp.integrationServiceTest;

import com.ThesisApp.exceptions.ApplicationAlreadyExistsException;
import com.ThesisApp.model.*;
import com.ThesisApp.repository.*;
import com.ThesisApp.service.*;
import com.ThesisApp.service.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentDAO;

    @Mock
    private SubjectRepository subjectDAO;

    @Mock
    private ApplicationRepository applicationDAO;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ThesisServiceImpl thesisService;

    @Mock
    private SubjectServiceImpl subjectService;

    @Mock
    private ApplicationServiceImpl applicationService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testFindByUser() {
        User user = new User();
        user.setUsername("testUsername");
        Student student = new Student();


        when(studentDAO.findByUser(Optional.of(user))).thenReturn(student);

        Student foundStudent = studentService.findByUser(Optional.of(user));

        assertEquals(student.getId(), foundStudent.getId());
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student();


        when(studentDAO.save(student)).thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);

        assertEquals(student.getId(), savedStudent.getId());
    }

    @Test
    public void testUpdateStudent() {
        String username = "testUsername";
        User user = new User();
        user.setUsername(username);
        Student existingStudent = new Student();
        existingStudent.setName("Old Name");
        existingStudent.setSurname("Old Surname");
        existingStudent.setYearOfStudies(1);
        existingStudent.setAverageGrade(35);
        //forgive our lack of time this need type change refactor but now we assume 100/100!
        existingStudent.setRemainingCourses(5);
        existingStudent.setUser(user);

        Student updatedStudent = new Student();
        updatedStudent.setName("New Name");
        updatedStudent.setSurname("New Surname");
        updatedStudent.setYearOfStudies(2);
        updatedStudent.setAverageGrade(40);
        updatedStudent.setRemainingCourses(3);

        when(userService.findByUsername(username)).thenReturn(user);
        when(studentDAO.findByUser(Optional.of(user))).thenReturn(existingStudent);
        when(studentDAO.save(existingStudent)).thenReturn(existingStudent);

        Student result = studentService.updateStudent(username, updatedStudent);

        assertEquals(updatedStudent.getName(), result.getName());
        assertEquals(updatedStudent.getSurname(), result.getSurname());
        assertEquals(updatedStudent.getYearOfStudies(), result.getYearOfStudies());
        assertEquals(updatedStudent.getAverageGrade(), result.getAverageGrade(), 0.001);
        assertEquals(updatedStudent.getRemainingCourses(), result.getRemainingCourses());
        verify(studentDAO, times(1)).save(existingStudent);
    }

    @Test
    public void testFindById() {
        Student student = new Student();
        student.setId(1L);

        when(studentDAO.findById(anyLong())).thenReturn(Optional.of(student));

        Student foundStudent = studentService.findById(1L);

        assertEquals(student.getId(), foundStudent.getId());
    }

    @Test
    public void testApplyToSubject() {

        Student student = new Student();

        Subject subject = new Subject();


        when(applicationDAO.findByStudentIdAndSubjectId(anyLong(), anyLong())).thenReturn(null);
        when(studentDAO.findById(subject.getId())).thenReturn(Optional.of(student));
        when(subjectDAO.findById(student.getId())).thenReturn(Optional.of(subject));

        studentService.applyToSubject(student.getId(), subject.getId());

        verify(applicationDAO, times(1)).save(any(Application.class));
    }
}
