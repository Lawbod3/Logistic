package org.BodeLogistics.com.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document("Activity")
public class Activity {
    @Id
    private String id;
    private String for_someone_name;
    private String for_someone_PhoneNumber;
    private String pickUpAddress;
    private String destinationAddress;
    private LocalDate date;
    private String price;
    private String driverId;
    private  String comment;
    private String userId;


}
