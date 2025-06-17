package org.BodeLogistics.com.dto.response;

import lombok.Data;
import org.BodeLogistics.com.data.models.ChatMessage;

import java.time.LocalDateTime;

@Data
public class SetChatToActiveResponse {
    private String activityId;
    private boolean activated;
    private LocalDateTime timestamp;
    private ChatMessage message;


}
