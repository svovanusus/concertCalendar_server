package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.EventModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventModel, Long> {
}
