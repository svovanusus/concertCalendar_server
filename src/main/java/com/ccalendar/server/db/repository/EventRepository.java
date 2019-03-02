package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.RegionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<EventModel, Long> {
    /**
     * Найти все события с заданным значением флага <code>isFest</code>
     * @param isFest значение флага, по которому ведётся поиск
     * @return список моделей найденных событий
     */
    public Iterable<EventModel> findAllByIsFest(boolean isFest);

    /**
     * Найти все события с заданным значением флага <code>isFest</code> и заданным регионом
     * @param isFest значение флага, по которому ведётся поиск
     * @param region рагион, по которому фильтруются события
     * @return список моделей найденных событий
     */
    public Iterable<EventModel> findAllByIsFestAndEventRegion(boolean isFest, RegionModel region);

    /**
     * Найти событие по альтернатвному идентификатору
     * @param altId альтернативный идентификатор
     * @return модель найденная по заданному значению альтернативного идентификатора
     */
    public Optional<EventModel> findByAltId(long altId);

    /**
     * Найти все события, с определённой датой
     * @param date дата, по которой ведётся поиск
     * @return список моделей найденных событий
     */
    public Iterable<EventModel>findAllByDate(LocalDate date);
}
