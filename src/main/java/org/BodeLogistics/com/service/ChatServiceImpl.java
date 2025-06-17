package org.BodeLogistics.com.service;

import org.BodeLogistics.com.data.models.RideActivity;
import org.BodeLogistics.com.data.repositories.RideActivityRepository;
import org.BodeLogistics.com.dto.request.SetChatToActiveRequest;
import org.BodeLogistics.com.dto.response.SetChatToActiveResponse;
import org.BodeLogistics.com.exceptions.RideActivityDoesNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    RideActivityRepository rideActivityRepository;

    @Override
    public SetChatToActiveResponse setChatToActive(SetChatToActiveRequest request) {
        RideActivity rideActivity = rideActivityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new RideActivityDoesNotExist("Activity does not exist"));
        rideActivity.setChatActive(true);
        rideActivityRepository.save(rideActivity);
        SetChatToActiveResponse response = new SetChatToActiveResponse();
        response.setActivated(true);
        response.setActivityId(request.getActivityId());
        response.setTimestamp(LocalDateTime.now());
        return response;

    }
}
