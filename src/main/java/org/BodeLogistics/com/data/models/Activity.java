package org.BodeLogistics.com.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

@Data
@Document("Activity")
public class Activity {
    @Id
    private String id;
    private String forSomeoneName;
    private String forSomeonePhoneNumber;
    private String pickUpAddress;
    private String destinationAddress;
    private LocalDate date;
    private String price;
    private String driverId;
    private String userId;
    private String userComment;
    private String driverComment;
    private ActivityType activityType;
    private boolean successful;
    private boolean cancelled;



}
