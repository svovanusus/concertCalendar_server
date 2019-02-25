package com.ccalendar.server.domain.services.event;

import com.ccalendar.server.domain.model.Event;
import com.ccalendar.server.domain.model.Genre;
import com.ccalendar.server.domain.model.User;

import java.util.Collection;

public interface EventService {
    /**
     * Получить список всех концертов для пользователя
     * @param user доменный объект пользователя, для которого формируется список концертов
     * @return список доменных объектов концертов
     */
    public Collection<Event> getEvents(User user);

    /**
     * Получить Концерт по идентификатору
     * @param id идентификатор концерта
     * @return доменный объект найденного концерта
     */
    public Event getEvent(long id);

    /**
     * Получить список жанров заданного концерта
     * @param eventId идентификатор концерта
     * @return список доменных объектов жанров
     * @throws RuntimeException ошибка при поиске
     */
    public Collection<Genre> getEventGenres(long eventId) throws RuntimeException;

    /**
     * Установить список жанров для концерта
     * @param eventId идентификатор концерта
     * @param genres список жанров
     * @return доменный объект сохранённого концерта после установки жанров
     */
    public Event setGenresForEvent(long eventId, Collection<Long> genres);
}
