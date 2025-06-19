package org.BodeLogistics.com.dto.response;

import lombok.Data;

@Data
public class DriverNotificationResponse {
    private  String pickUpLocation;
    private  String dropOffLocation;
    private String price;
}
