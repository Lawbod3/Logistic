package org.BodeLogistics.com.exceptions;

public class UserIsNotADriver extends RuntimeException {
    public UserIsNotADriver(String message) {
        super(message);
    }
}
