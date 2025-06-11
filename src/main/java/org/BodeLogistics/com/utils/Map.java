package org.BodeLogistics.com.utils;

import org.BodeLogistics.com.data.models.User;
import org.BodeLogistics.com.dto.response.UserLoginResponse;
import org.BodeLogistics.com.dto.response.UserRegistrationResponse;

public class Map {
    public static UserRegistrationResponse userToUserRegistrationResponse(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setADriver(user.isADriver());
        response.setHomeAddress(user.getHomeAddress());
        response.setId(user.getId());
        return response;
    }

    public static UserLoginResponse userToUserLoginResponse(User user) {
        UserLoginResponse response = new UserLoginResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setADriver(user.isADriver());
        response.setHomeAddress(user.getHomeAddress());
        response.setId(user.getId());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;

    }
}
