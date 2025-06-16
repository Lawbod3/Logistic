package org.BodeLogistics.com.dto.response;

import lombok.Data;
import org.BodeLogistics.com.data.models.RideActivity;
import org.BodeLogistics.com.data.models.UserType;

import java.util.List;

@Data
public class DispatcherActivityResponse {

    private String userId;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private UserType userType;
    private String motorcycleId;
    private String  motorcycleDescription;
    private boolean available;
    private List<RideActivity> dispatchedActivities;
}
