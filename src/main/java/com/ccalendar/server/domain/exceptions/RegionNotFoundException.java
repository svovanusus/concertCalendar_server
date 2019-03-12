package com.ccalendar.server.domain.exceptions;

public class RegionNotFoundException extends RegionException {
    private static final String DEFAULT_MSG = "Region not found";

    public RegionNotFoundException(){
        super(DEFAULT_MSG);
    }
}
