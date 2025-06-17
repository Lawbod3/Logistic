package org.BodeLogistics.com.dto.request;

import lombok.Data;

@Data
public class SetChatToActiveRequest {
    private String activityId;
    private String driverId;
    private String userId;
}
