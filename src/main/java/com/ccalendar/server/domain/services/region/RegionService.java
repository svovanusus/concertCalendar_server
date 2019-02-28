package com.ccalendar.server.domain.services.region;

import com.ccalendar.server.domain.model.Region;

import java.util.Collection;

public interface RegionService {
    /**
     * Получить регион по идентификатору
     * @param id идентификатор региона
     * @return доменный объект региона
     */
    public Region getById(long id);
    public Collection<Region> getAll();
}
