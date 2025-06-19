package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DispatchRiderRegistrationRequest {
    @NotBlank(message = "Rider license cant be blank")
    private String ridersLicenseNumber;
    @NotBlank(message = "user Id cant be blank")
    private String userId;
    @NotBlank(message = "motorcycle id cant be blank")
    private String motorcycleId;
    @NotBlank(message = "motorcycle description cant be blank")
    private String motorcycleDescription;
}
