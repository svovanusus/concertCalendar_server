package com.ccalendar.server.domain.services.event;

import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.exceptions.EventNotFoundException;
import com.ccalendar.server.domain.model.Event;

import java.util.Collection;

public interface EventService {
    /**
     * Получить список всех событий для пользователя с заданным параметром типа (<code>CONCERT</code> или <code>FEST</code>)
     * @param userModel объект БД пользователя, для которого формируется список концертов
     * @param type тип события (<code>CONCERT</code> или <code>FEST</code>)
     * @param byUserRegion флаг, устанавливающий фильтрацию событий по региону пользователя.
     * @return список доменных объектов концертов
     */
    public Collection<Event> getEvents(UserModel userModel, Event.EventType type, boolean byUserRegion);

    /**
     * Получить Событие по идентификатору
     * @param id идентификатор концерта
     * @return доменный объект найденного концерта
     */
    public Event getEvent(UserModel userModel, long id) throws EventNotFoundException;

    /**
     * Установить список жанров для события
     * @param eventId идентификатор концерта
     * @param genres список жанров
     * @return доменный объект сохранённого концерта после установки жанров
     */
    public Event setGenresForEvent(long eventId, Collection<Long> genres);

    public boolean addUser(EventModel eventModel, UserModel userModel) throws EventNotFoundException;

    public boolean delUser(EventModel eventModel, UserModel userModel) throws EventNotFoundException;
}
