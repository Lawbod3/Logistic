package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserBookARideForSomeoneRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String destinationAddress;
    @NotBlank
    private String pickupAddress;
    private String forSomeonePhoneNumber;
    private String forSomeoneName;
    private String price;



}
