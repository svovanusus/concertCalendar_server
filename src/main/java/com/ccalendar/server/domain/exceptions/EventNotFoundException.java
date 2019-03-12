package com.ccalendar.server.domain.exceptions;

public class EventNotFoundException extends EventException {
    private static final String DEFAULT_MSG = "Event not found";

    public EventNotFoundException() {
        super(DEFAULT_MSG);
    }
}
