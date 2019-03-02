package com.ccalendar.server.domain.exceptions;

public class EventNotFoundException extends EventException {
    private static final String DEFAULT_MSG = "Event with id=%s not found.";

    public EventNotFoundException() {
        super("Event not found.");
    }

    public EventNotFoundException(long eventId){
        super(String.format(DEFAULT_MSG, String.valueOf(eventId)));
    }
}
