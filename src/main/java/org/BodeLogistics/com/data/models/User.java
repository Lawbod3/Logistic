package org.BodeLogistics.com.data.models;



import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("UserLogistics")
public class User {
    @Id
    private String id;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String password;
    private Notification notification ;
    private UserType userType = UserType.ORDINARY;




}
