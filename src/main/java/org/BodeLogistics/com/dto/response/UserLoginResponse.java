package org.BodeLogistics.com.dto.response;

import lombok.Data;
import org.BodeLogistics.com.data.models.Notification;
import org.BodeLogistics.com.data.models.RideActivity;
import org.BodeLogistics.com.data.models.UserType;

import java.util.List;

@Data
public class UserLoginResponse {
    private String id;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private UserType userType;
    private Notification notification;
    private List<RideActivity> activities;
}
