package org.BodeLogistics.com.controllers;

import jakarta.validation.Valid;
import org.BodeLogistics.com.dto.request.*;
import org.BodeLogistics.com.dto.response.*;
import org.BodeLogistics.com.exceptions.*;
import org.BodeLogistics.com.service.LogisticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/logistics/user")
public class UserController {
    @Autowired
    LogisticServices logisticServices;

    @GetMapping("/test")
    public String test() {
        return "Api is working";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationRequest request) {
        try{
            UserRegistrationResponse response = logisticServices.registerUser(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        }
        catch(UserExistException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest request) {
        try {
            UserLoginResponse response = logisticServices.loginUser(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);

        }
        catch(UserDoesNotExistException | PasswordException | LogisticsSystemException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login/driver-registration")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody DriverRegistrationRequest request ) {
        try {
            DriverRegistrationResponse response = logisticServices.registerDriver(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        }
        catch(UserDoesNotExistException | DriverExistException | VehicleAuthenticationException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login/dispatcher-registration")
    public ResponseEntity<?> registerDispatcher(@Valid @RequestBody DispatchRiderRegistrationRequest request ) {
        try {
            DispatchRiderRegistrationResponse response = logisticServices.registerDispatchRider(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        }
        catch(UserDoesNotExistException | DispatcherExistException | MotorcycleAuthenticationException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }






    private ResponseEntity<?> createErrorResponse(Exception e, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), status);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = "One or more request form  are invalid or not filled";
        return createErrorResponse(new Exception(errorMessage), HttpStatus.BAD_REQUEST);
    }


}
