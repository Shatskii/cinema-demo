package org.shatskii.cinemademo.exception;

public class NoSuchTicketException extends RuntimeException {
    public NoSuchTicketException(String message) {
        super(message);
    }
}
