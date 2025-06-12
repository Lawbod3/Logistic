package org.BodeLogistics.com.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("DispatchDriver")
public class DispatchRider {
    @Id
    private String id;
    private String userId;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String password;
    private UserType userType = UserType.DISPATCHER;
    private String ridersLicenseNumber;
    private String motorcycleId;
    private String motorcycleDescription;
    private List<Activity> dispatchedActivities;
}
