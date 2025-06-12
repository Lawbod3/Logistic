package org.BodeLogistics.com.dto.response;

import lombok.Data;
import org.BodeLogistics.com.data.models.DriverRegistrationStatus;

@Data
public class DispatchRiderRegistrationResponse {
    private DriverRegistrationStatus status;
    private String message;
}
