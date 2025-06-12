package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DispatchRiderRegistrationRequest {
    @NotBlank
    private String ridersLicenseNumber;
    @NotBlank
    private String userId;
    @NotBlank
    private String motorcycleId;
    @NotBlank
    private String motorcycleDescription;
}
