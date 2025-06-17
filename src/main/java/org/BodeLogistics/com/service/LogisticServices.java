package org.BodeLogistics.com.service;


import org.BodeLogistics.com.dto.request.*;
import org.BodeLogistics.com.dto.response.*;

public interface LogisticServices {
    UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest);
    UserLoginResponse loginUser(UserLoginRequest userLoginRequest);
    DriverRegistrationResponse registerDriver(DriverRegistrationRequest request);
    DispatchRiderRegistrationResponse registerDispatchRider(DispatchRiderRegistrationRequest request);
    DispatchRiderAvailableResponse setDispatchRiderToAvailable(DispatchRiderAvailableRequest request);
    DriverAvailableResponse setDriverToAvailable(DriverAvailableRequest request);
    DeliveryResponse dispatchRequest(DeliveryRequest deliveryRequest);
    SetChatToActiveResponse setChatToActive(SetChatToActiveRequest request);
    UserBookARideForSomeoneResponse userBookARideForSomeone(UserBookARideForSomeoneRequest userBookARideRequest);
    RideRequest userBookARide(RideRequest rideRequest);
    RideResponse activityStatus(RideRequest activityStatusRequest);




}
