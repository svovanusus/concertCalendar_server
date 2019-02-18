package com.ccalendar.server.domain.exceptions;

public class RegionNotFoundException extends RegionException {
    private static final String DEFAULT_MSG = "Region with id=%s not found.";

    public RegionNotFoundException(long regionId){
        super(String.format(DEFAULT_MSG, String.valueOf(regionId)));
    }
}
