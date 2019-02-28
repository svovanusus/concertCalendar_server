package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.GenreModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<GenreModel, Long> {
}
