package com.ccalendar.server.domain.util;

import com.ccalendar.server.api.data.GenreData;
import com.ccalendar.server.db.model.GenreModel;
import com.ccalendar.server.domain.model.Genre;

import java.util.Collection;
import java.util.stream.Collectors;

public final class GenreConverter {
    private GenreConverter(){}

    /**
     * Конвертация объекта БД в доменный уровень
     * @param model объект БД
     * @return доменный объект
     */
    public static Genre convertToGenreDomain(GenreModel model){
        return new Genre(
                model.getId(),
                model.getTitle()
        );
    }

    /**
     * Конвертация списка объектов БД в список доменных объектов
     * @param models список объектов БД
     * @return список доменных объектов
     */
    public static Collection<Genre> convertToGenreDomain(Collection<GenreModel> models){
        return models.stream()
                .map(GenreConverter::convertToGenreDomain)
                .collect(Collectors.toList());
    }

    /**
     * Конвертация доменного объекта в DTO
     * @param genre доменный объект
     * @return DTO
     */
    public static GenreData convertToGenreDTO(Genre genre){
        return new GenreData(
                genre.getId(),
                genre.getTitle()
        );
    }

    /**
     * Конвертация списка доменных объектов в список DTO
     * @param genres список доменных объектов
     * @return список DTO
     */
    public static Collection<GenreData> convertToGenreDTO(Collection<Genre> genres){
        return genres.stream()
                .map(GenreConverter::convertToGenreDTO)
                .collect(Collectors.toList());
    }
}
