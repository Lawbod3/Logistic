package org.BodeLogistics.com.repositories;


import org.BodeLogistics.com.data.models.User;
import org.BodeLogistics.com.data.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPhoneNumber("5555555555");

    }

    @Test
    public void testUserRepositoryIsEmpty() {
        assertTrue(userRepository.findAll().isEmpty());
    }
    @Test
    public void testUserRepositoryIsNotEmpty() {
        userRepository.save(new User());
        assertFalse(userRepository.findAll().isEmpty());
    }
    @Test
    public void testUserRepositoryCanFindById() {
        userRepository.save(user);
        assertTrue(userRepository.findById(user.getId()).isPresent());
    }
    @Test
    public void testUserRepositoryCanFindByEmail() {
        userRepository.save(user);
        assertTrue(userRepository.findByEmail(user.getEmail()).isPresent());
    }
    @Test
    public void testUserRepositoryCanFindByPhoneNumber() {
        userRepository.save(user);
        assertTrue(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent());
    }


}
