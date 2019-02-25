package com.ccalendar.server.domain.exceptions;

public class GenreNotFoundException extends GenreException {
    private static final String DEFAULT_MSG = "Genre with id=%s not found.";

    public GenreNotFoundException(long genreId){
        super(String.format(DEFAULT_MSG, String.valueOf(genreId)));
    }
}
