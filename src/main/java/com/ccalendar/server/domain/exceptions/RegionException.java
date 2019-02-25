package com.ccalendar.server.domain.exceptions;

public abstract class RegionException extends Exception {
    public RegionException(String message){
        super(message);
    }

    public RegionException(String message, Throwable cause){
        super(message, cause);
    }

    public RegionException(Throwable cause){
        super(cause);
    }
}
