package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BecomeADriverRequest {
    @NotBlank
    private String driversLicenseNumber;
    @NotBlank
    private String userId;
    @NotBlank
    private String vehicleId;
}
