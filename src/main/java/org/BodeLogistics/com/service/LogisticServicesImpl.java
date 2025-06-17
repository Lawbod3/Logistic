package org.BodeLogistics.com.service;

import org.BodeLogistics.com.data.models.*;
import org.BodeLogistics.com.data.repositories.*;
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
    DispatchRiderRepository dispatchRiderRepository;
    @Autowired
    RideActivityRepository activityRepository;
    @Autowired
    DispatchActivityRepository dispatchActivityRepository;
    @Autowired
    ChatMessageRepository chatMessageRepository;
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
    public DriverRegistrationResponse registerDriver(DriverRegistrationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("User Does Not Exist"));
        if(user.getUserType().equals(UserType.DRIVER)) throw new DriverExistException("Driver Already Exist");
        if(driverRepository.existsByVehicleId(request.getVehicleId().toLowerCase())) throw new VehicleAuthenticationException("This Vehicle Already Associated with another Driver");
        DriverRegistrationResponse response = new DriverRegistrationResponse();
        if(verifyBecomeADriverRequest(request)){
            Driver driver = Map.userToDriver(user);
            Map.driverRegistrationRequestToDriver(request, driver);
            response.setStatus(DriverRegistrationStatus.Success);
            response.setMessage("Registered successfully");
            userRepository.save(user);
            driverRepository.save(driver);
        }
        else throw new VehicleAuthenticationException("Your DriversLicense need to 12 digit Number or VehicleId need to be in this format(AbCd1234)");
        return response;
    }
    private boolean verifyBecomeADriverRequest(DriverRegistrationRequest becomeADriverRequest) {
        if(!becomeADriverRequest.getDriversLicenseNumber().matches("^\\d{12}$")) return false;
        if(!becomeADriverRequest.getVehicleId().matches("^[A-Za-z]{4}\\d{4}$")) return false;
        return true;
    }

    @Override
    public DispatchRiderRegistrationResponse registerDispatchRider(DispatchRiderRegistrationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("User Does Not Exist"));
        if(user.getUserType().equals(UserType.DISPATCHER)) throw new DispatcherExistException("Dispatcher Already Exist");
        if(dispatchRiderRepository.existsByMotorcycleId(request.getMotorcycleId().toLowerCase())) throw new MotorcycleAuthenticationException("This Motorcycle Already Associated with another rider");
        DispatchRiderRegistrationResponse response = new DispatchRiderRegistrationResponse();
        if(verifyBecomeADisPatcherRequest(request)){
            DispatchRider rider = Map.userToDispatchDriver(user);
            Map.DispatchRiderRegistrationRequestToDriver(request, rider);
            response.setStatus(DriverRegistrationStatus.Success);
            response.setMessage("Registered successfully");
            userRepository.save(user);
            dispatchRiderRepository.save(rider);
        }
        else throw  new MotorcycleAuthenticationException("Your DriversLicense need to 12 digit Number or VehicleId need to be in this format(Ab123Qwe)");
        return response;
    }

    private boolean verifyBecomeADisPatcherRequest(DispatchRiderRegistrationRequest request) {
        if(!request.getRidersLicenseNumber().matches("^\\d{12}$")) return false;
        if(!request.getMotorcycleId().matches("^[A-Za-z]{2}\\d{3}[A-za-z]{3}$")) return false;
        return true;
    }

    @Override
    public DispatchRiderAvailableResponse setDispatchRiderToAvailable(DispatchRiderAvailableRequest request) {
        DispatchRider rider = dispatchRiderRepository.findByUserId(request.getRiderId())
                .orElseThrow(() -> new RiderDoesNotExistException("Rider does not exist"));
        rider.setAvailable(true);
        dispatchRiderRepository.save(rider);
        DispatchRiderAvailableResponse response = new DispatchRiderAvailableResponse();
        response.setSuccess(true);
        response.setMessage("Rider is available");
        return response;
    }

    @Override
    public DriverAvailableResponse setDriverToAvailable(DriverAvailableRequest request) {
        Driver driver = driverRepository.findByUserId(request.getDriverId())
                .orElseThrow(() -> new DriverDoesNotExistException("Driver does not exist"));
        driver.setAvailable(true);
        driverRepository.save(driver);
        DriverAvailableResponse response = new DriverAvailableResponse();
        response.setSuccess(true);
        response.setMessage("Driver is available");
        return response;
    }

    @Override
    public DeliveryResponse dispatchRequest(DeliveryRequest deliveryRequest) {
        DispatchActivity dispatchActivity = Map.dispatchActivityToDeliveryRequest(deliveryRequest);
        DispatchRider  foundDispatcher = searchForDispatcherService();
        DispatcherProfileResponse dispatchActivityResponse = Map.dispatchActivityResponseToRider(foundDispatcher);
        Map.dispatchRiderToActivity(foundDispatcher,dispatchActivity);
        dispatchActivity = dispatchActivityRepository.save(dispatchActivity);
        return new DeliveryResponse(dispatchActivityResponse,dispatchActivity);
    }

    private DispatchRider searchForDispatcherService() {
        return dispatchRiderRepository.findDispatchRiderByAvailable(true)
                .orElseThrow(() -> new DispatcherNotAvailableException("No dispatch rider available at the moment"));
    }




    @Override
    public SetChatToActiveResponse setChatToActive(SetChatToActiveRequest request) {
        return null;
    }



    @Override
    public UserBookARideForSomeoneResponse userBookARideForSomeone(UserBookARideForSomeoneRequest userBookARideRequest) {

        return null;

    }

    @Override
    public RideResponse userBookARide(RideRequest rideRequest) {
        RideActivity activity = Map.rideActivityToRideRequest(rideRequest);
        Driver foundDriver = searchForDriverService();
        DriverProfileResponse driverProfileResponse = Map.dispatchActivityResponseToDriver(foundDriver);
        Map.driverToActivity(foundDriver,activity);
        activity = activityRepository.save(activity);
        return new RideResponse(driverProfileResponse, activity);

    }

    private Driver searchForDriverService() {
        return driverRepository.findRideByAvailable(true)
                .orElseThrow(() -> new DriverNotAvailableException("No Driver available at the moment"));
    }




}
