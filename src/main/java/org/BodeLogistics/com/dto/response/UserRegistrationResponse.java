package org.BodeLogistics.com.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.BodeLogistics.com.data.models.Activity;

import java.util.List;

@Data
public class UserRegistrationResponse {
    private String id;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private boolean isADriver;
    private List<Activity> activities;
}
