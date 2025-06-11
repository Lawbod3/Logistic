package org.BodeLogistics.com.service;

import org.BodeLogistics.com.data.repositories.ActivityRepository;
import org.BodeLogistics.com.data.repositories.UserRepository;
import org.BodeLogistics.com.dto.request.*;
import org.BodeLogistics.com.dto.response.*;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticServicesImpl implements LogisticServices{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;
    private StringEncryptor encryptor;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        return null;
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        return null;
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
