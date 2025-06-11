package org.BodeLogistics.com.service;


import org.BodeLogistics.com.dto.request.*;
import org.BodeLogistics.com.dto.response.*;

public interface LogisticServices {
    UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest);
    UserLoginResponse loginUser(UserLoginRequest userLoginRequest);
    BecomeADriverResponse becomeDriver(BecomeADriverRequest becomeADriverRequest);
    DeliveryResponse deliver(DeliveryRequest deliveryRequest);
    UserBookARideResponse userBookARide(UserBookARideRequest userBookARideRequest);
    ActivityStatusResponse activityStatus(ActivityStatusRequest activityStatusRequest);




}
