package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RideRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String destinationAddress;
    @NotBlank
    private String pickupAddress;

    @NotBlank
    @Pattern(regexp = "^[0-9](\\.[0-9]{1,2})?$")
    private String price;

}
