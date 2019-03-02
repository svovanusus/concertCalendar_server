package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.RegionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends CrudRepository<RegionModel, Long> {
    Optional<RegionModel> findByAltId(long altId);
}
