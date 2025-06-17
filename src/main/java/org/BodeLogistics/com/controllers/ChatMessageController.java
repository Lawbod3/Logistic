package org.BodeLogistics.com.controllers;

import jakarta.validation.Valid;
import org.BodeLogistics.com.dto.request.SetChatToActiveRequest;
import org.BodeLogistics.com.dto.response.ApiResponse;
import org.BodeLogistics.com.dto.response.SetChatToActiveResponse;
import org.BodeLogistics.com.exceptions.RideActivityDoesNotExist;
import org.BodeLogistics.com.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/logistics/chat")
public class ChatMessageController {
    @Autowired
    ChatService chatService;

    @GetMapping("/test")
    public String test() {
        return "Api is working";
    }

    @PostMapping("/login/ride-activity/activate-chat")
    public ResponseEntity<?> activateChat(@Valid @RequestBody SetChatToActiveRequest request) {
        try{
            SetChatToActiveResponse response = chatService.setChatToActive(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);

        }
        catch(RideActivityDoesNotExist e){
            return new ResponseEntity<>(new ApiResponse(false, e), HttpStatus.BAD_REQUEST);

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
