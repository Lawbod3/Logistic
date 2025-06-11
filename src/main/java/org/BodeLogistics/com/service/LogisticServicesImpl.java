package org.BodeLogistics.com.service;

import org.BodeLogistics.com.data.models.User;
import org.BodeLogistics.com.data.repositories.ActivityRepository;
import org.BodeLogistics.com.data.repositories.UserRepository;
import org.BodeLogistics.com.dto.request.*;
import org.BodeLogistics.com.dto.response.*;
import org.BodeLogistics.com.exceptions.LogisticsSystemException;
import org.BodeLogistics.com.exceptions.PasswordException;
import org.BodeLogistics.com.exceptions.UserDoesNotExistException;
import org.BodeLogistics.com.exceptions.UserExistException;
import org.BodeLogistics.com.utils.Map;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticServicesImpl implements LogisticServices{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    StringEncryptor stringEncryptor;
    private static final Logger logger = LoggerFactory.getLogger(LogisticServicesImpl.class);
    private StringEncryptor encryptor;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())) throw new UserExistException("User Already Exist");
        User user = new User();
        user.setADriver(false);
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setHomeAddress(request.getHomeAddress());
        user.setPassword(stringEncryptor.encrypt(request.getPassword()));
        userRepository.save(user);
        return Map.userToUserRegistrationResponse(user);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->  new UserDoesNotExistException("User does not Exist"));
        try{
        String decryptedPassword = stringEncryptor.decrypt(user.getPassword());
        if(!decryptedPassword.equals(request.getPassword())) throw new PasswordException("Invalid Password");
        } catch (EncryptionOperationNotPossibleException e ) {
            logger.error("Failed to decrypt password for user with phone number: {}",
                    request.getPhoneNumber(), e);
            throw new LogisticsSystemException("System error");
        }
        response = Map.userToUserLoginResponse(user);
        response.setActivities(activityRepository.findAllByUserId(user.getId()).get());
        return response;

    }

    @Override
    public BecomeADriverResponse becomeDriver(BecomeADriverRequest becomeADriverRequest) {
        return null;
    }

    @Override
    public DeliveryResponse deliver(DeliveryRequest deliveryRequest) {
        return null;
    }

    @Override
    public UserBookARideResponse userBookARide(UserBookARideRequest userBookARideRequest) {
        return null;

    }

    @Override
    public ActivityStatusResponse activityStatus(ActivityStatusRequest activityStatusRequest) {
        return null;
    }
}
