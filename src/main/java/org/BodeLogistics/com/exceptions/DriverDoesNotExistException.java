package org.BodeLogistics.com.exceptions;

public class DriverDoesNotExistException extends RuntimeException{
    public DriverDoesNotExistException(String message) {
        super(message);
    }
}
