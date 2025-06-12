package org.BodeLogistics.com.data.models;

import lombok.Data;

import java.util.List;

@Data
public class Driver extends User {
    private List<Activity> rideActivities;
}
