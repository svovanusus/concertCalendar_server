package com.ccalendar.server.domain.util;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.RegionModel;
import com.ccalendar.server.domain.model.Event;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
public class EventConverterTest {

    @Test
    public void convertToEventDomain() {
        final EventModel eventModel = new EventModel();
        final RegionModel regionModel = new RegionModel();

        regionModel.setId(30L);
        regionModel.setTitle("info");
        regionModel.setEvents(Collections.singleton(eventModel));

        eventModel.setId(5L);
        eventModel.setTitle("test title");
        eventModel.setEventRegion(regionModel);
        eventModel.setPlace("place");
        eventModel.setDate(LocalDate.now());
        eventModel.setArtist("artist");
        eventModel.setFest(true);

        final Event eventDomain = EventConverter.convertToEventDomain(eventModel);

        Assert.assertEquals(eventDomain.getId(), eventModel.getId());
        Assert.assertEquals(eventDomain.getTitle(), eventModel.getTitle());
        Assert.assertEquals(eventDomain.getRegion(), RegionConverter.convertToRegionDomain(regionModel));
        Assert.assertEquals(eventDomain.getPlace(), eventModel.getPlace());
        Assert.assertEquals(eventDomain.getDate(), eventModel.getDate());
        Assert.assertEquals(eventDomain.getArtist(), eventModel.getArtist());
        Assert.assertEquals(eventDomain.isFest(), eventModel.isFest());
    }

    @Test
    public void convertToEventListDomain1() {
        final EventModel eventModel1 = new EventModel();
        eventModel1.setId(5L);
        eventModel1.setTitle("test title1");
        eventModel1.setEventRegion(new RegionModel());
        eventModel1.setPlace("place1");
        eventModel1.setDate(LocalDate.now());
        eventModel1.setArtist("artist1");
        eventModel1.setFest(true);

        final EventModel eventModel2 = new EventModel();
        eventModel2.setId(6L);
        eventModel2.setTitle("test title2");
        eventModel2.setPlace("place2");
        eventModel2.setDate(LocalDate.now().plusDays(1));
        eventModel2.setArtist("artist2");
        eventModel2.setFest(false);

        List<EventModel> eventModels = new ArrayList<>();
        eventModels.add(eventModel1);
        eventModels.add(eventModel2);

        List<Event> events = new ArrayList<>(EventConverter.convertToEventDomain(eventModels));

        Assert.assertEquals(events.get(0).getId(), eventModels.get(0).getId());
        Assert.assertEquals(events.get(0).getTitle(), eventModels.get(0).getTitle());
        Assert.assertEquals(events.get(0).getPlace(), eventModels.get(0).getPlace());
        Assert.assertEquals(events.get(0).getDate(), eventModels.get(0).getDate());
        Assert.assertEquals(events.get(0).getArtist(), eventModels.get(0).getArtist());
        Assert.assertEquals(events.get(0).isFest(), eventModels.get(0).isFest());

        Assert.assertEquals(events.get(1).getId(), eventModels.get(1).getId());
        Assert.assertEquals(events.get(1).getTitle(), eventModels.get(1).getTitle());
        Assert.assertEquals(events.get(1).getPlace(), eventModels.get(1).getPlace());
        Assert.assertEquals(events.get(1).getDate(), eventModels.get(1).getDate());
        Assert.assertEquals(events.get(1).getArtist(), eventModels.get(1).getArtist());
        Assert.assertEquals(events.get(1).isFest(), eventModels.get(1).isFest());
    }
}