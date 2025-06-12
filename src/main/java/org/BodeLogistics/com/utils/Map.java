package org.BodeLogistics.com.utils;

import org.BodeLogistics.com.data.models.Driver;
import org.BodeLogistics.com.data.models.User;
import org.BodeLogistics.com.data.models.UserType;
import org.BodeLogistics.com.dto.response.UserLoginResponse;
import org.BodeLogistics.com.dto.response.UserRegistrationResponse;

public class Map {
    public static UserRegistrationResponse userToUserRegistrationResponse(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        response.setHomeAddress(user.getHomeAddress());
        response.setId(user.getId());
        return response;
    }

    public static UserLoginResponse userToUserLoginResponse(User user) {
        UserLoginResponse response = new UserLoginResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setHomeAddress(user.getHomeAddress());
        response.setId(user.getId());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;

    }
    public static Driver userToDriver(User user) {
        Driver driver = new Driver();
        driver.setId(user.getId());
        driver.setFirstName(user.getFirstName());
        driver.setLastName(user.getLastName());
        driver.setHomeAddress(user.getHomeAddress());
        driver.setPhoneNumber(user.getPhoneNumber());
        driver.setEmail(user.getEmail());
        driver.setPassword(user.getPassword());
        driver.setUserType(UserType.DRIVER);
        user.setUserType(UserType.DRIVER);
        return driver;
    }
}
