package org.BodeLogistics.com.dto.response;

import lombok.Data;
import org.BodeLogistics.com.data.models.DriverRegistrationStatus;

@Data
public class BecomeADriverResponse {
    private DriverRegistrationStatus status;
    private boolean isADriver;
    private String message;

}
