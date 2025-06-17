package org.BodeLogistics.com.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SetChatToActiveRequest {
    @NotBlank
    private String activityId;
    private String driverId;
    private String userId;
}
