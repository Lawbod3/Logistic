package org.BodeLogistics.com.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DispatchRider extends User{
    private List<Activity> dispatchedActivities;
}
