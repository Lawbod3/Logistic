package org.BodeLogistics.com.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UltimateUser extends User {
    private List<RideActivity> dispatchedActivities;
    private List<RideActivity> DriverActivities;
}
