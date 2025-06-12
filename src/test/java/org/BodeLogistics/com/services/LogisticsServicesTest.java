package org.BodeLogistics.com.services;


import org.BodeLogistics.com.data.models.User;
import org.BodeLogistics.com.data.repositories.DriverRepository;
import org.BodeLogistics.com.data.repositories.UserRepository;
import org.BodeLogistics.com.dto.request.DriverRegistrationRequest;
import org.BodeLogistics.com.dto.request.UserLoginRequest;
import org.BodeLogistics.com.dto.request.UserRegistrationRequest;
import org.BodeLogistics.com.dto.response.DriverRegistrationResponse;
import org.BodeLogistics.com.dto.response.UserLoginResponse;
import org.BodeLogistics.com.dto.response.UserRegistrationResponse;

import org.BodeLogistics.com.exceptions.*;
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
    @Autowired
    DriverRepository driverRepository;

    private UserRegistrationRequest userRegistrationRequest;
    private UserRegistrationResponse userRegistrationResponse;
    private UserLoginRequest userLoginRequest;
    private UserLoginResponse userLoginResponse;
    private DriverRegistrationRequest becomeADriverRequest;
    private DriverRegistrationResponse becomeADriverResponse;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        driverRepository.deleteAll();

        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail("test@test.com");
        userRegistrationRequest.setFirstName("Test");
        userRegistrationRequest.setLastName("Test");
        userRegistrationRequest.setPassword("password");
        userRegistrationRequest.setPhoneNumber("123456789");

        userLoginRequest = new UserLoginRequest();
        userLoginRequest.setPhoneNumber("123456789");
        userLoginRequest.setPassword("password");

        becomeADriverRequest = new DriverRegistrationRequest();
        becomeADriverRequest.setDriversLicenseNumber("123456789012");
        becomeADriverRequest.setVehicleId("ABCD1234");


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

    @Test
    public void testThatLogisticsServiceCanRegisterUserAsDriver() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());
        userLoginResponse = logisticServices.loginUser(userLoginRequest);
        assertEquals(userLoginRequest.getPhoneNumber(), userLoginResponse.getPhoneNumber());
        becomeADriverRequest.setUserId(userLoginResponse.getId());
        becomeADriverResponse = logisticServices.registerDriver(becomeADriverRequest);
        assertNotNull(becomeADriverResponse.getMessage());

    }
    @Test
    public void testThatLogisticsServiceCannotRegisterUserAsDriverWhenUserIsAlreadyADriver() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());

        userLoginResponse = logisticServices.loginUser(userLoginRequest);
        assertEquals(userLoginRequest.getPhoneNumber(), userLoginResponse.getPhoneNumber());

        becomeADriverRequest.setUserId(userLoginResponse.getId());
        becomeADriverResponse = logisticServices.registerDriver(becomeADriverRequest);
        assertNotNull(becomeADriverResponse.getMessage());

        assertThrows(DriverExistException.class, () -> logisticServices.registerDriver(becomeADriverRequest));
    }
    @Test
    public void testThatLogisticsServiceCannotRegisterUserAsDriverWhenRequestDetailsIsWrong() {
        userRegistrationResponse = logisticServices.registerUser(userRegistrationRequest);
        assertTrue(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent());
        assertEquals(userRegistrationRequest.getEmail(), userRegistrationResponse.getEmail());
        userLoginResponse = logisticServices.loginUser(userLoginRequest);
        assertEquals(userLoginRequest.getPhoneNumber(), userLoginResponse.getPhoneNumber());
        becomeADriverRequest.setUserId(userLoginResponse.getId());
        becomeADriverRequest.setDriversLicenseNumber("123456789");
        becomeADriverResponse = logisticServices.registerDriver(becomeADriverRequest);
        assertEquals("Your DriversLicense need to 12 digit Number or VehicleId need to be in this format(AbCd1234)",becomeADriverResponse.getMessage());

    }



}

