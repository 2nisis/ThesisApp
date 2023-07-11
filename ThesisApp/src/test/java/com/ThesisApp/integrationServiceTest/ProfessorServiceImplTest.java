package com.ThesisApp.integrationServiceTest;

import com.ThesisApp.exceptions.ApplicationNotFoundException;
import com.ThesisApp.model.*;
import com.ThesisApp.repository.*;
import com.ThesisApp.service.ApplicationService;
import com.ThesisApp.service.BestApplicantStrategy;
import com.ThesisApp.service.ThesisService;
import com.ThesisApp.service.impl.ProfessorServiceImpl;
import com.ThesisApp.service.impl.UserServiceImpl;
import com.ThesisApp.service.strategy.BestApplicantStrategyFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProfessorServiceImplTest {

    @Mock
    private ProfessorRepository professorDAO;

    @Mock
    private SubjectRepository subjectDAO;

    @Mock
    private ApplicationRepository applicationDAO;

    @Mock
    private ThesisRepository thesisDAO;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ApplicationService applicationService;

    @Mock
    private ThesisService thesisService;

    @Mock
    private BestApplicantStrategyFactory strategyFactory;

    @InjectMocks
    private ProfessorServiceImpl professorService;

    public ProfessorServiceImplTest() {
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Professor professor = new Professor();
        professor.setId(1L);

        when(professorDAO.findById(1L)).thenReturn(Optional.of(professor));

        Optional<Professor> foundProfessor = professorService.findById(1L);

        assertEquals(professor.getId(), foundProfessor.get().getId());
    }

    @Test
    public void testFindByUser() {
        User user = new User();
        Professor professor = new Professor();
        professor.setUser(user);

        when(professorDAO.findByUser(Optional.of(user))).thenReturn(professor);

        Professor foundProfessor = professorService.findByUser(Optional.of(user));

        assertEquals(professor.getUser().getId(), foundProfessor.getUser().getId());
    }

    @Test
    public void testRetrieveProfile() {
        Professor professor = new Professor();
        professor.setId(1L);

        when(professorDAO.findById(anyLong())).thenReturn(Optional.of(professor));

        Professor retrievedProfessor = professorService.retrieveProfile(1L);

        assertEquals(professor.getId(), retrievedProfessor.getId());
    }

    @Test
    public void testSaveProfile() {
        Professor professor = new Professor();

        professorService.saveProfile(professor);

        verify(professorDAO, times(1)).save(professor);
    }

    @Test
    public void testUpdateProfile() {
        User user = new User();
        user.setUsername("testUsername");
        Professor existingProfessor = new Professor();
        existingProfessor.setName("dionisis");
        existingProfessor.setSurname("kaisaris");
        existingProfessor.setEmail("dionisis@kaisaris.com");
        existingProfessor.setUser(user);

        Professor updatedProfessor = new Professor();
        updatedProfessor.setName("kirkos");
        updatedProfessor.setSurname("eleftherios");
        updatedProfessor.setEmail("kirkos@kirkos.gr");
        updatedProfessor.setUser(user);

        when(userService.findByUsername("testUsername")).thenReturn(user);
        when(professorDAO.findByUser(Optional.of(user))).thenReturn(existingProfessor);

        professorService.updateProfile("testUsername", updatedProfessor);

        verify(professorDAO, times(1)).save(existingProfessor);
        assertEquals(updatedProfessor.getName(), existingProfessor.getName());
        assertEquals(updatedProfessor.getSurname(), existingProfessor.getSurname());
        assertEquals(updatedProfessor.getEmail(), existingProfessor.getEmail());
    }

    @Test
    public void testListProfessorSubjects() {
        String username = "testUsername";
        User user = new User();
        user.setUsername(username);
        Professor professor = new Professor();

        professor.setUser(user);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        subjects.add(new Subject());

        when(userService.findByUsername(username)).thenReturn(user);
        when(professorDAO.findByUser(Optional.of(user))).thenReturn(professor);
        when(subjectDAO.findByProfessorId(professor.getId())).thenReturn(subjects);

        List<Subject> professorSubjects = professorService.listProfessorSubjects(username);

        assertEquals(subjects.size(), professorSubjects.size());
    }

    @Test
    public void testAddSubject() {
        String username = "testUsername";
        User user = new User();
        user.setUsername(username);
        Professor professor = new Professor();

        professor.setUser(user);
        Subject subject = new Subject();

        when(userService.findByUsername(username)).thenReturn(user);
        when(professorDAO.findByUser(Optional.of(user))).thenReturn(professor);
        when(subjectDAO.save(subject)).thenReturn(subject);

        professorService.addSubject(subject, username);

        assertEquals(professor, subject.getProfessor());
        assertTrue(subject.isAvailable());
        verify(subjectDAO, times(1)).save(subject);
    }

    @Test
    public void testListProfessorTheses() {
        User user = new User();
        user.setUsername("testUsername");
        Professor professor = new Professor();

        professor.setUser(user);
        List<Thesis> theses = new ArrayList<>();
        theses.add(new Thesis());
        theses.add(new Thesis());

        when(professorDAO.findByUser(Optional.of(user))).thenReturn(professor);
        when(thesisDAO.findByProfessorId(professor.getId())).thenReturn(theses);

        List<Thesis> professorTheses = professorService.listProfessorTheses(user);

        assertEquals(theses.size(), professorTheses.size());
    }

}

