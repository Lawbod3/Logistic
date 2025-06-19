package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DriverRegistrationRequest {
    @NotBlank(message = "license must not be blank")
    private String driversLicenseNumber;
    @NotBlank(message = "UserId must not be blank")
    private String userId;
    @NotBlank(message = "vehicle Id must not be blank")
    private String vehicleId;
    @NotBlank(message = "description must not be bklank")
    private String vehicleDescription;
}
