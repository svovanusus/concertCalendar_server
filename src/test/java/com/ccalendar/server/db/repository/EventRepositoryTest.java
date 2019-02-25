package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.EventModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void testSaveEvents(){
        final EventModel eventModel = new EventModel();
        eventModel.setTitle("title");
        eventModel.setEventRegion(regionRepository.findById(62L).get());
        eventModel.setPlace("place");
        eventModel.setDate(LocalDate.now());
        eventModel.setArtist("artist");
        eventModel.setFest(true);

        final EventModel savedModel = eventRepository.save(eventModel);

        Assert.assertNotNull(savedModel.getId());
        Assert.assertEquals(savedModel.getTitle(), eventModel.getTitle());
        Assert.assertEquals(savedModel.getEventRegion(), eventModel.getEventRegion());
        Assert.assertEquals(savedModel.getPlace(), eventModel.getPlace());
        Assert.assertEquals(savedModel.getDate(), eventModel.getDate());
        Assert.assertEquals(savedModel.getArtist(), eventModel.getArtist());
        Assert.assertEquals(savedModel.isFest(), eventModel.isFest());

        final Optional<EventModel> optSelected = eventRepository.findById(savedModel.getId());

        Assert.assertTrue(optSelected.isPresent());

        final EventModel selectedValue = optSelected.get();

        Assert.assertEquals(selectedValue.getId(), savedModel.getId());
        Assert.assertEquals(selectedValue.getTitle(), eventModel.getTitle());
        Assert.assertEquals(selectedValue.getEventRegion(), eventModel.getEventRegion());
        Assert.assertEquals(selectedValue.getPlace(), eventModel.getPlace());
        Assert.assertEquals(selectedValue.getDate(), eventModel.getDate());
        Assert.assertEquals(selectedValue.getArtist(), eventModel.getArtist());
        Assert.assertEquals(selectedValue.isFest(), eventModel.isFest());
    }
}