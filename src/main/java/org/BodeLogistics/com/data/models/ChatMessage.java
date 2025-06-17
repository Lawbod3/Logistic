package org.BodeLogistics.com.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("ChatMessage")
public class ChatMessage {
    @Id
    private String id;
    private String senderId;
    private String receiverId;
    private LocalDateTime timestamp;
    private String message;
    private boolean read;

}
