package com.ccalendar.server.domain.services.system;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.RegionModel;
import com.ccalendar.server.db.repository.EventRepository;
import com.ccalendar.server.db.repository.GenreRepository;
import com.ccalendar.server.db.repository.RegionRepository;
import com.ccalendar.server.domain.model.response.EntityFromResponse;
import com.ccalendar.server.domain.model.response.EventFromEntity;
import com.ccalendar.server.domain.model.response.SubEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class SystemService {
    private static final String CRON1 = "0 50 0 * * *";
    private static final String CRON2 = "0 30 23 * * *";
    private static final String uri = "https://api.cultserv.ru/v4/events/list";
    private static final String pars = "?session=svovanusus912461&category_id=10&first_only=true&limit=%d&offset=%d&fields=tags, subevents.venue, subevents.venue.region";
    private static final int limit = 100;

    private RegionRepository regionRepository;
    private GenreRepository genreRepository;
    private EventRepository eventRepository;

    @Scheduled(cron = CRON1)
    public void loadEvents(){
        RestTemplate restTemplate = new RestTemplate();

        int count = 0;
        do {
            ResponseEntity<EntityFromResponse<List<EventFromEntity>>> result = restTemplate.exchange(
                    String.format("%s%s", uri, String.format(pars, limit, limit * count++)),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<EntityFromResponse<List<EventFromEntity>>>() {
                    });

            if (result.getStatusCode() != HttpStatus.OK) continue;

            if (result.getBody() == null) continue;

            if (result.getBody().getCode() <= 0) continue;

            if (result.getBody().getMessage().size() <= 0) break;

            result.getBody().getMessage().forEach(eventFromEntity -> {
                if (eventRepository.findByAltId(eventFromEntity.getId()).isPresent()) return;

                SubEvent subEvent = eventFromEntity.getSubevents().get(0);
                RegionModel regionModel = regionRepository.findByAltId(subEvent.getVenue().getRegion().getId()).orElse(null);

                EventModel eventModel = new EventModel();
                eventModel.setAltId(eventFromEntity.getId());
                eventModel.setTitle(eventFromEntity.getTitle());
                eventModel.setArtist(eventFromEntity.getTitle());
                eventModel.setDescription(eventFromEntity.getDescription() != null ? eventFromEntity.getDescription() : "");
                eventModel.setPoster(subEvent.getImage() != null ? String.format("http://media.cultserv.ru/i/300x200/%s", subEvent.getImage()) : "");
                eventModel.setPlace(subEvent.getVenue().getTitle());
                eventModel.setFest(false);
                eventModel.setEventRegion(regionModel);
                eventModel.setDate(LocalDate.parse(String.copyValueOf(subEvent.getDate().toCharArray(), 0, 10)));

                if (eventFromEntity.getTaggroups() != null)
                    eventFromEntity.getTaggroups().forEach(tagGroup -> genreRepository.findByAltId(tagGroup.getId()).ifPresent(genre -> eventModel.getGenresForEvent().add(genre)));

                eventRepository.save(eventModel);
            });
        } while (true);
        System.out.println("Events added!");
    }

    @Scheduled(cron = CRON2)
    public void delEvents() {
        eventRepository.findAllByDate(LocalDate.now()).forEach(eventModel -> {
            eventModel.setUsers(null);
            eventModel.setGenresForEvent(null);

            eventRepository.save(eventModel);
            eventRepository.delete(eventModel);
        });
    }

    @Autowired
    public void setRegionRepository(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
}
