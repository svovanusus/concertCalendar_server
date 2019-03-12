package com.ccalendar.server.domain.services.event;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.GenreModel;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.db.repository.EventRepository;
import com.ccalendar.server.db.repository.GenreRepository;
import com.ccalendar.server.domain.exceptions.EventNotFoundException;
import com.ccalendar.server.domain.model.Event;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.util.EventConverter;
import com.ccalendar.server.domain.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private GenreRepository genreRepository;

    private boolean filterEvent(UserModel user, EventModel event){
        for(EventModel em : user.getEventsForUser()){
            if (event.getId() == em.getId()) return true;
        }
        if (user.getGenresForUser().toArray().length <= 0) return true;

        for(GenreModel eventGenre : event.getGenresForEvent()){
            for(GenreModel userGenre : user.getGenresForUser()){
                if (eventGenre.equals(userGenre)) return true;
            }
        }

        return false;
    }

    @Override
    public Collection<Event> getEvents(UserModel userModel, Event.EventType type, boolean byUserRegion){
        User user = UserConverter.convertToUserDomain(userModel);
        Iterable<EventModel> events =
                byUserRegion
                //? eventRepository.findAllByUsersContaining(userModel)
                ? eventRepository.findAllByIsFestAndEventRegionOrIsFestAndUsersContaining(type == Event.EventType.FEST, userModel.getUserRegion(), type == Event.EventType.FEST, userModel)
                : eventRepository.findAllByIsFest(type == Event.EventType.FEST);
        return StreamSupport.stream(events.spliterator(), false)
                .filter(event -> filterEvent(userModel, event))
                .map(EventConverter::convertToEventDomain)
                .map(event -> {
                    event.setLiked(user.getEvents().contains(event));
                    return event;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Event getEvent(long id) throws EventNotFoundException{
        Optional<EventModel> optEvent = eventRepository.findById(id);
        if (!optEvent.isPresent())
            throw new EventNotFoundException();

        return EventConverter.convertToEventDomain(optEvent.get());
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
