package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DeliveryRequest {
    @NotBlank
    private String receiverPhoneNumber;
    @NotBlank
    private String senderPhoneNumber;
    @NotBlank
    private String pickUpAddress;
    @NotBlank
    private String deliveryAddress;
    @NotBlank
    private String userId;
    @NotBlank
    private String driverId;

}
