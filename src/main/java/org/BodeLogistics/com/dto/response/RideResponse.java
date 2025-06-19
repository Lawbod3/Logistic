package org.BodeLogistics.com.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.BodeLogistics.com.data.models.RideActivity;

@Data
@AllArgsConstructor
public class RideResponse {
    private DriverProfileResponse driverProfile;
    private RideActivity rideActivity;

}
