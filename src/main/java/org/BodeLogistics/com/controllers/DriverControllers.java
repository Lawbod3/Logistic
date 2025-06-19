package org.BodeLogistics.com.controllers;

import org.BodeLogistics.com.dto.request.DriverAvailableRequest;
import org.BodeLogistics.com.dto.request.RideRequest;
import org.BodeLogistics.com.dto.response.ApiResponse;
import org.BodeLogistics.com.dto.response.DriverAvailableResponse;
import org.BodeLogistics.com.dto.response.RideResponse;
import org.BodeLogistics.com.exceptions.DriverDoesNotExistException;
import org.BodeLogistics.com.exceptions.DriverNotAvailableException;
import org.BodeLogistics.com.exceptions.UserDoesNotExistException;
import org.BodeLogistics.com.service.LogisticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/logistics/driver")
public class DriverControllers {
    @Autowired
    LogisticServices logisticServices;

    @GetMapping("/test")
    public String test() {
        return "Api is working";
    }
 @PostMapping("/login/ride-request")
    public ResponseEntity<?> rideRequest(@RequestBody RideRequest rideRequest) {
        try{
            RideResponse response = logisticServices.userBookARide(rideRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }
        catch (DriverNotAvailableException | UserDoesNotExistException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.BAD_REQUEST);

        }
 }

 @PostMapping("/login/driver-available")
 public ResponseEntity<?> driverAvailable(@RequestBody DriverAvailableRequest driverAvailableRequest) {
        try{
            DriverAvailableResponse response = logisticServices.setDriverToAvailable(driverAvailableRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }
        catch (DriverDoesNotExistException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.BAD_REQUEST);
        }
 }

    private ResponseEntity<?> createErrorResponse(Exception e, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), status);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        FieldError errorMessage = e.getBindingResult().getFieldError();
        return createErrorResponse(new Exception(errorMessage.getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

}
