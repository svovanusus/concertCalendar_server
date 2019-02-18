package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.EventModel;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventModel, Long> {
}
