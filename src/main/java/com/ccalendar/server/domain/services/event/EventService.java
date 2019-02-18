package com.ccalendar.server.domain.services.event;

import com.ccalendar.server.domain.model.Event;
import com.ccalendar.server.domain.model.Genre;
import com.ccalendar.server.domain.model.User;

import java.util.Collection;

public interface EventService {
    public Collection<Event> getEvents(User user);

    public Event getEvent(long id);

    public Collection<Genre> getEventGenres(long eventId) throws RuntimeException;

    public Event setGenresForEvent(long eventId, Collection<Long> genres);
}
