package org.BodeLogistics.com.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("DispatchDriver")
public class DispatchRider { private String id;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String password;
    private UserType userType = UserType.ORDINARY;
    private List<Activity> dispatchedActivities;
}
