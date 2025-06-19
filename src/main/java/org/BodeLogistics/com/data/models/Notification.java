package org.BodeLogistics.com.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.BodeLogistics.com.dto.response.RideResponse;

@Data
@AllArgsConstructor
public class Notification {
    private String rideId;
    private String pickupAddress;
    private String destinationAddress;
    private String price;
    private String message;

    public Notification() {
        this.message = "";
    }

    public Notification(RideActivity activity) {
        this.rideId = activity.getId();
        this.pickupAddress = activity.getPickUpAddress();
        this.destinationAddress = activity.getDestinationAddress();
        this.price = activity.getPrice();
        this.message = "New ride assigned: From " + pickupAddress + " to " + destinationAddress ;
    }

    public Notification(RideResponse rideResponse) {
        RideActivity activity = rideResponse.getRideActivity();
        this.rideId = activity.getId();
        this.pickupAddress = activity.getPickUpAddress();
        this.destinationAddress = activity.getDestinationAddress();
        this.price = activity.getPrice();
        this.message = "Ride booked successfully with driver: " + rideResponse.getDriverProfile().getFirstName();
    }
}