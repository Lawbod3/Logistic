package org.BodeLogistics.com.dto.response;

import lombok.Data;
import org.BodeLogistics.com.data.models.DriverRegistrationStatus;

@Data
public class DriverRegistrationResponse {
    private DriverRegistrationStatus status;
    private String message;

}
