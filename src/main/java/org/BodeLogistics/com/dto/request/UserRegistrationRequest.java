package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
    private String homeAddress;
}
