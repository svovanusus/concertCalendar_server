package com.ccalendar.server.domain.services.region;

import com.ccalendar.server.domain.model.Region;

public interface RegionService {
    /**
     * Получить регион по идентификатору
     * @param id идентификатор региона
     * @return доменный объект региона
     */
    public Region getById(long id);
}
