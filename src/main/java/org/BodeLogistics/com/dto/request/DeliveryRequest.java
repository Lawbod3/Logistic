package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DeliveryRequest {
    @NotBlank
    private String receiverPhoneNumber;
    @NotBlank
    private String receiverName;
    @NotBlank
    private String senderPhoneNumber;
    @NotBlank
    private String pickUpAddress;
    @NotBlank
    private String deliveryAddress;
    @NotBlank
    private String userId;
    @NotBlank
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Price must be a number with up to 2 decimal places")
    private String price;


}
