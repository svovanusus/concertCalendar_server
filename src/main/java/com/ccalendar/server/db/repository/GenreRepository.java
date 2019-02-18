package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.GenreModel;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<GenreModel, Long> {
}
