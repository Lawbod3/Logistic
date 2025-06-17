package org.BodeLogistics.com.services;


import org.BodeLogistics.com.data.models.RideActivity;
import org.BodeLogistics.com.data.repositories.RideActivityRepository;
import org.BodeLogistics.com.dto.request.SetChatToActiveRequest;
import org.BodeLogistics.com.dto.response.SetChatToActiveResponse;
import org.BodeLogistics.com.service.ChatService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class ChatServiceTest {
    @Autowired
    ChatService chatService;

    @Autowired
    RideActivityRepository rideActivityRepository;

    private SetChatToActiveRequest setChatToActiveRequest;
    private SetChatToActiveResponse setChatToActiveResponse;
    private RideActivity rideActivity;


    @BeforeEach
    public void setUp() {
        rideActivityRepository.deleteAll();
        setChatToActiveRequest = new SetChatToActiveRequest();
        rideActivity = new RideActivity();
        rideActivityRepository.save(rideActivity);
        setChatToActiveRequest.setActivityId(rideActivity.getId());

    }
    @Test
    public void testChatService() {
        setChatToActiveResponse = chatService.setChatToActive(setChatToActiveRequest);
        assertTrue(setChatToActiveResponse.isActivated());
    }


}
