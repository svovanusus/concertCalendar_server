package com.ccalendar.server.domain.exceptions;

public class UserLoginExistsException extends UserException {
    private static final String DEFAULT_MSG = "User with login=%s exists.";

    public UserLoginExistsException(String userLogin){
        super(String.format(DEFAULT_MSG, userLogin));
    }
}
