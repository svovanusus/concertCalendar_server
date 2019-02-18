package com.ccalendar.server.domain.exceptions;

public abstract class UserException extends Exception {
    public UserException(String message){
        super(message);
    }

    public UserException(String message, Throwable cause){
        super(message, cause);
    }

    public UserException(Throwable cause){
        super(cause);
    }
}
