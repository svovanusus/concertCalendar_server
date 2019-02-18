package com.ccalendar.server.api.controllers;

import com.ccalendar.server.api.data.EventData;
import com.ccalendar.server.api.data.GenreData;
import com.ccalendar.server.api.data.ResultResponse;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.services.event.EventService;
import com.ccalendar.server.domain.util.EventConverter;
import com.ccalendar.server.domain.util.GenreConverter;
import com.ccalendar.server.domain.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @RequestMapping("/all")
    @ResponseBody
    public ResultResponse<Collection<EventData>> getAll(@AuthenticationPrincipal UserModel userModel){
        Collection<EventData> response = EventConverter.convertToEventDTO(
                eventService.getEvents(UserConverter.convertToUserDomain(userModel))
        );
        return new ResultResponse<>(response);
    }

    @RequestMapping("/first")
    @ResponseBody
    public ResultResponse<EventData> getFirst(){

        EventData response = EventConverter.convertToEventDTO(eventService.getEvent(1));

        return new ResultResponse<>(response);
    }

    @RequestMapping("/genres")
    @ResponseBody
    public ResultResponse<Collection<GenreData>> getGenres(@RequestParam(name = "eventId") long eventId){
        try {
            Collection<GenreData> response = GenreConverter.convertToGenreDTO(
                    eventService.getEventGenres(eventId)
            );
            return new ResultResponse<>(response);
        }
        catch (RuntimeException e){
            return new ResultResponse<>(ResultResponse.Status.ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping("/set")
    @ResponseBody
    public ResultResponse<EventData> setGenres(){
        try {
            ArrayList<Long> arr = new ArrayList<>();
            arr.add(1L);
            arr.add(2L);
            arr.add(3L);
            return new ResultResponse<>(EventConverter.convertToEventDTO(eventService.setGenresForEvent(1, arr)));
        }
        catch (RuntimeException e){
            return new ResultResponse<>(ResultResponse.Status.ERROR, e.getLocalizedMessage());
        }
    }

    @GetMapping("/user")
    @ResponseBody
    public String getSessionUser(@AuthenticationPrincipal UserModel userModel){
        return String.valueOf(userModel.getId());
    }

    @Autowired
    public void setEventService(EventService eventService){
        this.eventService = eventService;
    }
}
