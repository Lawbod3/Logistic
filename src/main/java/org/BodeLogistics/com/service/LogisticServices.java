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

    UserBookARideForSomeoneResponse userBookARideForSomeone(UserBookARideForSomeoneRequest userBookARideRequest);
    RideResponse userBookARide(RideRequest rideRequest);





}
