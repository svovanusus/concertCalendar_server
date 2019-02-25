package com.ccalendar.server.domain.services.info;

import com.ccalendar.server.domain.exceptions.GenreNotFoundException;
import com.ccalendar.server.domain.exceptions.RegionNotFoundException;
import com.ccalendar.server.domain.model.Genre;
import com.ccalendar.server.domain.model.Region;

import java.util.Collection;

public interface InfoService {

    /**_REGIONS_**/

    /**
     * Получить регион по идентификатору
     * @param id идентификатор региона
     * @return доменный объект региона
     * @throws RegionNotFoundException если регион с указанным идентификатором не найден
     */
    public Region getRegionById(long id) throws RegionNotFoundException;

    /**
     * Получить список всех регионов
     * @return список доменных объектов регионов
     */
    public Collection<Region> getAllRegions();

    /**_GENRES_**/

    /**
     * Получить жанр по идентификатору
     * @param id идентификатор жанра
     * @return доменный объект жанра
     * @throws GenreNotFoundException если жанр с указанным идентификатором не найден
     */
    public Genre getGenreById(long id) throws GenreNotFoundException;

    /**
     * Получить список всех жанров
     * @return список доменных объектов жанров
     */
    public Collection<Genre> getAllGenres();
}
