package org.BodeLogistics.com.dto.response;


import lombok.Data;
import org.BodeLogistics.com.data.models.RideActivity;
import org.BodeLogistics.com.data.models.UserType;

import java.util.List;

@Data
public class DriverProfileResponse {
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private UserType userType;
    private String vehicleId;
    private String  vehicleDescription;
    private boolean available;

    private List<RideActivity> rideActivities;

}
