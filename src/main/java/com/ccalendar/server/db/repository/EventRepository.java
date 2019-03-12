package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.RegionModel;
import com.ccalendar.server.db.model.UserModel;
import org.springframework.data.jpa.repository.Query;
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
    public Iterable<EventModel> findAllByDate(LocalDate date);

    public Iterable<EventModel> findAllByUsersContaining(UserModel user);

    public Iterable<EventModel> findAllByIsFestAndEventRegionOrIsFestAndUsersContaining(boolean isFest1, RegionModel region, boolean isFest2, UserModel user);

    @Query(value = "SELECT * FROM (SELECT *, EXISTS(SELECT 1 FROM public.user_event AS ue WHERE ue.event_id=e.id AND ue.user_id=?2 LIMIT 1 ) AS ex FROM public.event AS e WHERE e.fest=?1 ORDER BY ex DESC, date) AS tab WHERE region=?3 OR ex=true LIMIT 15",
    nativeQuery = true)
    public Iterable<EventModel> findAllByMyParams(boolean isFest, long userId, long regionId);
}
