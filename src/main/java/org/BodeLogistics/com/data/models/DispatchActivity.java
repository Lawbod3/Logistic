package org.BodeLogistics.com.data.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document("DispatchActivity")
public class DispatchActivity {
    @Id
    private String id;
    private String receiversName;
    private String receiversPhoneNumber;
    private String pickUpAddress;
    private String destinationAddress;
    private LocalDate date;
    private String price;
    private String dispatcherId;
    private String userId;
    private String userComment;
    private String DispatcherComment;
    @Setter(AccessLevel.NONE)
    private ActivityType activityType = ActivityType.DISPATCH;
    private ActivityStatus activityStatus = ActivityStatus.InProgress;
    private boolean chatActive ;
    private LocalDateTime chatStartedAt;
    private String lastMessageSenderId;
    private String lastMessage;

}
