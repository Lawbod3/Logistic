package org.BodeLogistics.com.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document("Driver")
public class Driver  {
    @Id
    private String id;
    private String userId;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String password;
    private UserType userType = UserType.DRIVER;
    private String driversLicenseNumber;
    private String vehicleId;
    private String vehicleDescription;
    private boolean available;
    private Notification notification;
    private List<RideActivity> rideActivities;
}
