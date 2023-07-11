package com.ThesisApp.integrationRepositoryTest;

import com.ThesisApp.model.Role;
import com.ThesisApp.model.User;
import com.ThesisApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testUserRepository() {
        User user = new User("dionisis", "dionisis", Role.STUDENT);

        entityManager.persistAndFlush(user);

        User foundUser = userRepository.findByUsername("dionisis");
        assertEquals(user.getUsername(), foundUser.getUsername());
    }
}
