package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestNotificationMessage {
    @NotBlank(message = "Id cant be blank")
    private String userId;

}
