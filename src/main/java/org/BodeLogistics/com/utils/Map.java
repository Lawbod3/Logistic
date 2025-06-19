package org.BodeLogistics.com.utils;

import org.BodeLogistics.com.data.models.*;
import org.BodeLogistics.com.dto.request.DeliveryRequest;
import org.BodeLogistics.com.dto.request.DispatchRiderRegistrationRequest;
import org.BodeLogistics.com.dto.request.DriverRegistrationRequest;
import org.BodeLogistics.com.dto.request.RideRequest;
import org.BodeLogistics.com.dto.response.*;

import java.time.LocalDate;

public class Map {
    public static UserRegistrationResponse userToUserRegistrationResponse(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setHomeAddress(user.getHomeAddress());
        response.setId(user.getId());
        return response;
    }

    public static UserLoginResponse userToUserLoginResponse(User user) {
        UserLoginResponse response = new UserLoginResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setHomeAddress(user.getHomeAddress());
        response.setId(user.getId());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setUserType(user.getUserType());
        response.setNotification(user.getNotification());
        return response;

    }
    public static Driver userToDriver(User user) {
        Driver driver = new Driver();
        driver.setUserId(user.getId());
        driver.setFirstName(user.getFirstName());
        driver.setLastName(user.getLastName());
        driver.setHomeAddress(user.getHomeAddress());
        driver.setPhoneNumber(user.getPhoneNumber());
        driver.setEmail(user.getEmail());
        driver.setPassword(user.getPassword());
        user.setUserType(UserType.DRIVER);
        return driver;
    }
    public static void driverRegistrationRequestToDriver(DriverRegistrationRequest request, Driver driver) {
        driver.setDriversLicenseNumber(request.getDriversLicenseNumber());
        driver.setVehicleDescription(request.getVehicleDescription());
        driver.setVehicleId(request.getVehicleId().toLowerCase());
    }
    public static DispatchRider userToDispatchDriver(User user) {
        DispatchRider dispatcher = new DispatchRider();
        dispatcher.setUserId(user.getId());
        dispatcher.setFirstName(user.getFirstName());
        dispatcher.setLastName(user.getLastName());
        dispatcher.setHomeAddress(user.getHomeAddress());
        dispatcher.setPhoneNumber(user.getPhoneNumber());
        dispatcher.setEmail(user.getEmail());
        dispatcher.setPassword(user.getPassword());
        user.setUserType(UserType.DISPATCHER);
        return dispatcher;
    }
    public static void DispatchRiderRegistrationRequestToDriver(DispatchRiderRegistrationRequest request, DispatchRider rider) {
        rider.setRidersLicenseNumber(request.getRidersLicenseNumber());
        rider.setMotorcycleDescription(request.getMotorcycleDescription());
        rider.setMotorcycleId(request.getMotorcycleId().toLowerCase());
    }
    public static DispatchActivity dispatchActivityToDeliveryRequest(DeliveryRequest request) {
        DispatchActivity activity = new DispatchActivity();
        activity.setReceiversPhoneNumber(request.getReceiverPhoneNumber());
        activity.setDestinationAddress(request.getDeliveryAddress());
        activity.setActivityStatus(ActivityStatus.SearchingForDispatcher);
        activity.setPickUpAddress(request.getPickUpAddress());
        activity.setDate(LocalDate.now());
        activity.setReceiversName(request.getReceiverName());
        activity.setUserId(request.getUserId());
        activity.setPrice(request.getPrice());
        return activity;
    }

    public static void dispatchRiderToActivity(DispatchRider foundDispatcher, DispatchActivity activity) {
        activity.setDispatcherId(foundDispatcher.getId());
        activity.setActivityStatus(ActivityStatus.FoundDispatcher);
    }

    public static DispatcherProfileResponse dispatchActivityResponseToRider(DispatchRider rider) {
        DispatcherProfileResponse response = new DispatcherProfileResponse();
        response.setAvailable(true);
        response.setDispatchedActivities(rider.getDispatchedActivities());
        response.setMotorcycleId(rider.getMotorcycleId());
        response.setMotorcycleDescription(rider.getMotorcycleDescription());
        response.setFirstName(rider.getFirstName());
        response.setLastName(rider.getLastName());
        response.setEmail(rider.getEmail());
        response.setUserType(rider.getUserType());
        response.setPhoneNumber(rider.getPhoneNumber());

        return response;
    }

    public static RideActivity rideActivityToRideRequest(RideRequest request) {
        RideActivity activity = new RideActivity();
        activity.setUserId(request.getUserId());
        activity.setPickUpAddress(request.getPickupAddress());
        activity.setPrice(request.getPrice());
        activity.setDestinationAddress(request.getDestinationAddress());
        activity.setDate(LocalDate.now());
        activity.setActivityStatus(ActivityStatus.SearchingForRide);
        return activity;
    }

    public static DriverProfileResponse dispatchActivityResponseToDriver(Driver foundDriver) {
        DriverProfileResponse response = new DriverProfileResponse();
        response.setAvailable(true);
        response.setRideActivities(foundDriver.getRideActivities());
        response.setVehicleDescription(foundDriver.getVehicleDescription());
        response.setVehicleId(foundDriver.getVehicleId());
        response.setFirstName(foundDriver.getFirstName());
        response.setLastName(foundDriver.getLastName());
        response.setEmail(foundDriver.getEmail());
        response.setUserType(foundDriver.getUserType());
        response.setPhoneNumber(foundDriver.getPhoneNumber());
        return response;
    }

    public static void driverToActivity(Driver foundDriver, RideActivity activity) {
        activity.setDriverId(foundDriver.getId());
        activity.setActivityStatus(ActivityStatus.FoundDriver);
    }


}
