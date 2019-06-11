package com.ccalendar.server.domain.services.event;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.GenreModel;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.db.repository.EventRepository;
import com.ccalendar.server.db.repository.GenreRepository;
import com.ccalendar.server.db.repository.UserRepository;
import com.ccalendar.server.domain.exceptions.EventNotFoundException;
import com.ccalendar.server.domain.model.Event;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.util.EventConverter;
import com.ccalendar.server.domain.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventServiceImpl implements EventService {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    private GenreRepository genreRepository;

    private static int compareEvents(Event o1, Event o2) {
        if (o1.isLiked() == o2.isLiked()){
            return o1.getDate().compareTo(o2.getDate());
        }
        if (o1.isLiked() && !o2.isLiked()) return -1;
        else return 1;
    }

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
        UserModel um = userRepository.findById(userModel.getId()).orElse(null);
        if (um == null) return null;
        User user = UserConverter.convertToUserDomain(um);
        Iterable<EventModel> events =
                byUserRegion
                //? eventRepository.findAllByUsersContaining(userModel)
                ? eventRepository.findAllByIsFestAndEventRegionOrIsFestAndUsersContaining(type == Event.EventType.FEST, userModel.getUserRegion(), type == Event.EventType.FEST, userModel)
                //? eventRepository.findAllByMyParams(type == Event.EventType.FEST, userModel.getId(), userModel.getUserRegion().getId())
                : eventRepository.findAllByIsFest(type == Event.EventType.FEST);
        return StreamSupport.stream(events.spliterator(), false)
                .filter(event -> filterEvent(userModel, event))
                .map(EventConverter::convertToEventDomain)
                .peek(event -> event.setLiked(user.getEvents().contains(event)))
                .sorted(EventServiceImpl::compareEvents)
                .limit(25)
                .collect(Collectors.toList());
    }

    @Override
    public Event getEvent(UserModel userModel, long id) throws EventNotFoundException{
        UserModel um = userRepository.findById(userModel.getId()).orElse(null);
        if (um == null) return null;
        User user = UserConverter.convertToUserDomain(um);
        Optional<EventModel> optEvent = eventRepository.findById(id);
        if (!optEvent.isPresent())
            throw new EventNotFoundException();

        Event event = EventConverter.convertToEventDomain(optEvent.get());
        event.setLiked(user.getEvents().contains(event));
        return event;
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

    @Override
    public boolean addUser(EventModel eventModel, UserModel userModel) throws EventNotFoundException {
        if (eventModel == null)
            throw new EventNotFoundException();
        
        userModel.setGenresForUser(null);
        
        eventModel.getUsers().add(userModel);
        eventRepository.save(eventModel);

        return true;
    }

    @Override
    public boolean delUser(EventModel eventModel, UserModel userModel) throws EventNotFoundException {
        if (eventModel == null)
            throw new EventNotFoundException();

        Set<UserModel> usersForEvent = eventModel.getUsers();
        UserModel fountUser = null;
        for (UserModel user : usersForEvent) {
            if (user.getId() == userModel.getId()) {
                fountUser = user;
                break;
            }
        }

        if (fountUser != null)
            usersForEvent.remove(fountUser);

        eventModel.setUsers(usersForEvent);
        eventRepository.save(eventModel);

        return false;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
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
