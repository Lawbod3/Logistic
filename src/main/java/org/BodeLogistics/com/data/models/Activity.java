package org.BodeLogistics.com.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document("Activity")
public class Activity {
    private String id;
    private String address;
    private LocalDate date;
    private String price;
    private String driver;
}
