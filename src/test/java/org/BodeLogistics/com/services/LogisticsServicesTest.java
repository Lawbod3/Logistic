package org.BodeLogistics.com.services;

import org.BodeLogistics.com.data.repositories.UserRepository;
import org.BodeLogistics.com.dto.request.UserRegistrationRequest;
import org.BodeLogistics.com.dto.response.UserRegistrationResponse;
import org.BodeLogistics.com.exceptions.UserExistException;
import org.BodeLogistics.com.service.LogisticServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class LogisticsServicesTest {
    @Autowired
    LogisticServices logisticServices;
    @Autowired
    UserRepository userRepository;
    private UserRegistrationRequest userRegistrationRequest;
    private UserRegistrationResponse userRegistrationResponse;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail("test@test.com");
        userRegistrationRequest.setFirstName("Test");
        userRegistrationRequest.setLastName("Test");
        userRegistrationRequest.setPassword("password");
        userRegistrationRequest.setPhoneNumber("123456789");


    }

    @Test
    public void testThatLogisticsServiceCanRegisterUser() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());
    }
    @Test
    public void testThatLogisticsServiceCannotRegisterUserWhenUserAlreadyExists() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());
        assertThrows(UserExistException.class, () -> logisticServices.registerUser(userRegistrationRequest));
    }
}
