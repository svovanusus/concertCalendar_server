package com.ccalendar.server.domain.util;

import com.ccalendar.server.api.data.GenreData;
import com.ccalendar.server.db.model.GenreModel;
import com.ccalendar.server.domain.model.Genre;

import java.util.Collection;
import java.util.stream.Collectors;

public final class GenreConverter {
    private GenreConverter(){}

    public static Genre convertToGenreDomain(GenreModel model){
        return new Genre(
                model.getId(),
                model.getTitle()
        );
    }

    public static Collection<Genre> convertToGenreDomain(Collection<GenreModel> models){
        return models.stream()
                .map(GenreConverter::convertToGenreDomain)
                .collect(Collectors.toList());
    }

    public static GenreData convertToGenreDTO(Genre genre){
        return new GenreData(
                genre.getId(),
                genre.getTitle()
        );
    }

    public static Collection<GenreData> convertToGenreDTO(Collection<Genre> genres){
        return genres.stream()
                .map(GenreConverter::convertToGenreDTO)
                .collect(Collectors.toList());
    }
}
