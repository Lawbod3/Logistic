package org.BodeLogistics.com.controllers;

import jakarta.validation.Valid;
import org.BodeLogistics.com.dto.request.DeliveryRequest;
import org.BodeLogistics.com.dto.request.DispatchRiderAvailableRequest;
import org.BodeLogistics.com.dto.response.ApiResponse;
import org.BodeLogistics.com.dto.response.DeliveryResponse;
import org.BodeLogistics.com.dto.response.DispatchRiderAvailableResponse;
import org.BodeLogistics.com.exceptions.DispatcherNotAvailableException;
import org.BodeLogistics.com.exceptions.RiderDoesNotExistException;
import org.BodeLogistics.com.service.LogisticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/logistics/rider")
public class RiderController {
    @Autowired
    LogisticServices logisticServices;

    @GetMapping("/test")
    public String test() {
        return "Api is working";
    }

    @PostMapping("/login/dispatch-request")
    public ResponseEntity<?> dispatchRequest(@Valid @RequestBody DeliveryRequest request){
        try{
            DeliveryResponse response = logisticServices.dispatchRequest(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        }
        catch(DispatcherNotAvailableException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login/rider-available")
    public ResponseEntity<?> riderAvailable(@Valid @RequestBody DispatchRiderAvailableRequest request){
        try{
            DispatchRiderAvailableResponse response = logisticServices.setDispatchRiderToAvailable(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        }
        catch(RiderDoesNotExistException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
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




