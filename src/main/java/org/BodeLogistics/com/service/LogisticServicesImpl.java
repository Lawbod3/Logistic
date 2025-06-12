package org.BodeLogistics.com.service;

import org.BodeLogistics.com.data.models.Driver;
import org.BodeLogistics.com.data.models.DriverRegistrationStatus;
import org.BodeLogistics.com.data.models.User;
import org.BodeLogistics.com.data.models.UserType;
import org.BodeLogistics.com.data.repositories.ActivityRepository;
import org.BodeLogistics.com.data.repositories.DriverRepository;
import org.BodeLogistics.com.data.repositories.UserRepository;
import org.BodeLogistics.com.dto.request.*;
import org.BodeLogistics.com.dto.response.*;
import org.BodeLogistics.com.exceptions.*;
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
    DriverRepository driverRepository;
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
        User user = userRepository.findById(becomeADriverRequest.getUserId())
                .orElseThrow(() -> new UserExistException("User Already Exist"));
        if(user.getUserType().equals(UserType.DRIVER)) throw new DriverExistException("Driver Already Exist");
        BecomeADriverResponse response = new BecomeADriverResponse();
        if(verifyBecomeADriverRequest(becomeADriverRequest)){
            Driver driver = Map.userToDriver(user);
            response.setStatus(DriverRegistrationStatus.Success);
            response.setMessage("Registered successfully");
            userRepository.save(user);
            driverRepository.save(driver);
        }
        else{
            response.setStatus(DriverRegistrationStatus.Failed);
            response.setMessage("Your DriversLicense need to 12 digit Number or VehicleId need to be in this format(AbCd1234)");
        }

        return response;
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
    private boolean verifyBecomeADriverRequest(BecomeADriverRequest becomeADriverRequest) {
        if(!becomeADriverRequest.getDriversLicenseNumber().matches("^\\d{12}$")) return false;
        if(!becomeADriverRequest.getVehicleId().matches("^[A-Za-z]{4}\\d{4}$")) return false;
        return true;
    }
}
