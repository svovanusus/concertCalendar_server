package com.ccalendar.server.domain.exceptions;

public abstract class EventException extends Exception {
    public EventException(String message){
        super(message);
    }

    public EventException(String message, Throwable cause){
        super(message, cause);
    }

    public EventException(Throwable cause){
        super(cause);
    }
}
