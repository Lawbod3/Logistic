package org.BodeLogistics.com.exceptions;

public class UserIsNotADriverException extends RuntimeException {
    public UserIsNotADriverException(String message) {
        super(message);
    }
}
