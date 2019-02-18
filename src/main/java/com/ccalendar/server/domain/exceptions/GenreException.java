package com.ccalendar.server.domain.exceptions;

public abstract class GenreException extends Exception {
    public GenreException(String message){
        super(message);
    }

    public GenreException(String message, Throwable cause){
        super(message, cause);
    }

    public GenreException(Throwable cause){
        super(cause);
    }
}
