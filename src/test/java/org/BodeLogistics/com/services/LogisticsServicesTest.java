package org.BodeLogistics.com.services;

import org.BodeLogistics.com.data.repositories.UserRepository;
import org.BodeLogistics.com.dto.request.UserLoginRequest;
import org.BodeLogistics.com.dto.request.UserRegistrationRequest;
import org.BodeLogistics.com.dto.response.UserLoginResponse;
import org.BodeLogistics.com.dto.response.UserRegistrationResponse;
import org.BodeLogistics.com.exceptions.LogisticsSystemException;
import org.BodeLogistics.com.exceptions.PasswordException;
import org.BodeLogistics.com.exceptions.UserDoesNotExistException;
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
    private UserLoginRequest userLoginRequest;
    private UserLoginResponse userLoginResponse;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail("test@test.com");
        userRegistrationRequest.setFirstName("Test");
        userRegistrationRequest.setLastName("Test");
        userRegistrationRequest.setPassword("password");
        userRegistrationRequest.setPhoneNumber("123456789");

        userLoginRequest = new UserLoginRequest();
        userLoginRequest.setPhoneNumber("123456789");
        userLoginRequest.setPassword("password");



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
    @Test
    public void testThatLogisticsServiceCanLoginUser() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());
        userLoginResponse = logisticServices.loginUser(userLoginRequest);
        assertEquals(userLoginRequest.getPhoneNumber(), userLoginResponse.getPhoneNumber());

    }
    @Test
    public void testThatLogisticsServiceCannotLoginUserWhenUserDoesNotExist() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());
        userLoginRequest.setPhoneNumber("1111");
        assertThrows(UserDoesNotExistException.class, () -> logisticServices.loginUser(userLoginRequest));
    }
    @Test
    public void testThatLogisticsServiceCannotLoginUserWhenUserExistsButWrongPassword() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());
        userLoginRequest.setPassword("1111");
        assertThrows(PasswordException.class, () -> logisticServices.loginUser(userLoginRequest));
    }


}

