package org.BodeLogistics.com.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SetChatToActiveResponse {
    private String activityId;
    private boolean activated;
    private LocalDateTime timestamp;


}
