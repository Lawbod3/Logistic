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
    @Pattern(regexp = "^[0-9]+$", message = "Please amke sure it only digit")
    private String phoneNumber;
    @NotBlank
    private String password;
    private String homeAddress;
}
