package org.BodeLogistics.com.service;

import org.BodeLogistics.com.dto.request.SetChatToActiveRequest;
import org.BodeLogistics.com.dto.response.SetChatToActiveResponse;

public interface ChatService {
    SetChatToActiveResponse setChatToActive(SetChatToActiveRequest request);
}
