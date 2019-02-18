package com.ccalendar.server.domain.exceptions;

public class UserInvalidParamsException extends UserException {
    private static final String DEFAULT_MSG = "Invalid user attribute -> %s";

    public UserInvalidParamsException(String param){
        super(String.format(DEFAULT_MSG, param));
    }
}
