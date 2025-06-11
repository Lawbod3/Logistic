package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.time.LocalDate;

@Data
public class UserBookARideRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String destinationAddress;
    private LocalDate date;
    private String price;
    private String driverId;
    private  String comment;


}
