package org.BodeLogistics.com.data.models;

import lombok.Data;
import org.BodeLogistics.com.exceptions.UserIsNotADriver;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document("Activity")
public class Activity {
    @Id
    private String id;
    private String destinationAddress;
    private LocalDate date;
    private String price;
    private User driver;
    private  String comment;
    @Field("UserId")
    private String userId;

    public void setDriver(User user) {
        if(user != null && user.isADriver()) {
            this.driver = user;
        }
        else throw new UserIsNotADriver("User is not a Driver");
    }
}
