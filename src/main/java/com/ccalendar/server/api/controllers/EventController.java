package com.ccalendar.server.api.controllers;

import com.ccalendar.server.api.data.EventData;
import com.ccalendar.server.api.data.ResultResponse;
import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.exceptions.EventNotFoundException;
import com.ccalendar.server.domain.model.Event;
import com.ccalendar.server.domain.services.event.EventService;
import com.ccalendar.server.domain.services.user.UserService;
import com.ccalendar.server.domain.util.EventConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
@RequestMapping("/event")
public class EventController {

    private EventService eventService;
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public ResultResponse<EventData> getEventById(
            @AuthenticationPrincipal UserModel userModel,
            @RequestParam(name = "id") long eventId,
            HttpServletResponse httpResponse){
        try{
            EventData response = EventConverter.convertToEventDTO(eventService.getEvent(userModel, eventId));
            return new ResultResponse<>(response);
        } catch (EventNotFoundException e) {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ResultResponse<>(ResultResponse.Status.ERROR, e.getMessage());
        }
    }

    @GetMapping("/concerts")
    @ResponseBody
    public ResultResponse<Collection<EventData>> getAllConcerts(@AuthenticationPrincipal UserModel userModel){
        Collection<EventData> response = EventConverter.convertToEventDTO(
                eventService.getEvents(userModel, Event.EventType.CONCERT, true)
        );
        return new ResultResponse<>(response);
    }

    @GetMapping("/fests")
    @ResponseBody
    public ResultResponse<Collection<EventData>> getAllFests(@AuthenticationPrincipal UserModel userModel){
        Collection<EventData> response = EventConverter.convertToEventDTO(
                eventService.getEvents(userModel, Event.EventType.FEST, false)
        );
        return new ResultResponse<>(response);
    }

    @PutMapping("/subscribe")
    @ResponseBody
    public ResultResponse<Boolean> subscribeUserToEvent(
            @AuthenticationPrincipal UserModel userModel,
            @RequestParam(name = "id") EventModel eventModel,
            @RequestParam(name = "act") boolean isSubscribe,
            HttpServletResponse httpResponse){

        try {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            if (isSubscribe)
                return new ResultResponse<>(
                        eventService.addUser(eventModel, userModel)
                );
            else
                return new ResultResponse<>(
                    eventService.delUser(eventModel, userModel)
                );
        } catch (EventNotFoundException e) {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ResultResponse<>(ResultResponse.Status.ERROR, e.getMessage());
        }
    }

    @Autowired
    public void setEventService(EventService eventService){
        this.eventService = eventService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
