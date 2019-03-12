package com.ccalendar.server.domain.exceptions;

public class GenreNotFoundException extends GenreException {
    private static final String DEFAULT_MSG = "Genre not found";

    public GenreNotFoundException(){
        super(DEFAULT_MSG);
    }
}
