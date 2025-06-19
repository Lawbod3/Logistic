package org.BodeLogistics.com.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document("RideActivity")
public class RideActivity {
    @Id
    private String id;
    private String pickUpAddress;
    private String destinationAddress;
    private LocalDate date;
    private String price;
    private String driverId;
    private String userId;
    private String userComment;
    private String driverComment;
    private ActivityType activityType = ActivityType.RIDE;
    private ActivityStatus activityStatus = ActivityStatus.InProgress;
    private boolean chatActive ;
    private LocalDateTime chatStartedAt;
    private String lastMessageSenderId;
    private String lastMessage;

}
