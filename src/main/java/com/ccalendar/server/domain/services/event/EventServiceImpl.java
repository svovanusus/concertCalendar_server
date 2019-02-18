package com.ccalendar.server.domain.services.event;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.GenreModel;
import com.ccalendar.server.db.repository.EventRepository;
import com.ccalendar.server.db.repository.GenreRepository;
import com.ccalendar.server.domain.model.Event;
import com.ccalendar.server.domain.model.Genre;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.util.EventConverter;
import com.ccalendar.server.domain.util.GenreConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private GenreRepository genreRepository;

    @Override
    public Collection<Event> getEvents(User user){
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .map(EventConverter::convertToEventDomain)
                .map(event -> {
                    event.setLiked(user.getEvents().contains(event));
                    return event;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Event getEvent(long id){
        return eventRepository.findById(id)
                .map(EventConverter::convertToEventDomain)
                .orElse(null);
    }



    @Override
    public Collection<Genre> getEventGenres(long eventId) throws RuntimeException{

        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(GenreConverter::convertToGenreDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Event setGenresForEvent(long eventId, Collection<Long> genres){
        EventModel eventModel = eventRepository.findById(eventId)
                .orElse(null);

        if (eventModel == null) return null;

        Collection<GenreModel> genreModels = StreamSupport.stream(genreRepository.findAllById(genres).spliterator(), false)
                .collect(Collectors.toList());

        eventModel.getGenresForEvent().addAll(genreModels);

        return EventConverter.convertToEventDomain(eventRepository.save(eventModel));
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }
}
