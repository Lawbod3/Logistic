package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DriverRegistrationRequest {
    @NotBlank
    private String driversLicenseNumber;
    @NotBlank
    private String userId;
    @NotBlank
    private String vehicleId;
    @NotBlank
    private String vehicleDescription;
}
