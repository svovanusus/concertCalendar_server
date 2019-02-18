package com.ccalendar.server.domain.util;

import com.ccalendar.server.api.data.EventData;
import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.domain.model.Event;

import java.util.Collection;
import java.util.stream.Collectors;

public final class EventConverter {
    private EventConverter(){}

    public static Event convertToEventDomain(EventModel model){
        return new Event(
                model.getId(),
                model.getTitle(),
                model.getPoster(),
                RegionConverter.convertToRegionDomain(model.getEventRegion()),
                model.getPlace(),
                model.getDate(),
                model.getArtist(),
                model.getDescription(),
                model.isFest(),
                GenreConverter.convertToGenreDomain(model.getGenresForEvent())
        );
    }

    public static Collection<Event> convertToEventDomain(Collection<EventModel> models){
        return models.stream()
                .map(EventConverter::convertToEventDomain)
                .collect(Collectors.toList());
    }

    public static EventData convertToEventDTO(Event event){
        return new EventData(
                event.getId(),
                event.getTitle(),
                event.getPoster(),
                RegionConverter.convertToRegionDTO(event.getRegion()),
                event.getPlace(),
                event.getDate(),
                event.getArtist(),
                event.getDescription(),
                event.isFest(),
                GenreConverter.convertToGenreDTO(event.getGenres()),
                event.isLiked()
        );
    }

    public static Collection<EventData> convertToEventDTO(Collection<Event> events){
        return events.stream()
                .map(EventConverter::convertToEventDTO)
                .collect(Collectors.toList());
    }

}
