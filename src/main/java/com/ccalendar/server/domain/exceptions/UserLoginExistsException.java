package com.ccalendar.server.domain.exceptions;

public class UserLoginExistsException extends UserException {
    private static final String DEFAULT_MSG = "This login is exists";

    public UserLoginExistsException(){
        super(DEFAULT_MSG);
    }
}
