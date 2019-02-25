package com.ccalendar.server.domain.util;

import com.ccalendar.server.api.data.UserData;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

public final class UserConverter {
    private UserConverter(){}

    /**
     * Конвертация объекта БД в доменный уровень
     * @param model объект БД
     * @return доменный объект
     */
    public static User convertToUserDomain(UserModel model){
        return new User(
                model.getId(),
                model.getName(),
                model.getLogin(),
                model.getPassword(),
                model.getAvatar(),
                RegionConverter.convertToRegionDomain(model.getUserRegion()),
                GenreConverter.convertToGenreDomain(model.getGenresForUser()),
                EventConverter.convertToEventDomain(model.getEventsForUser())
        );
    }

    /**
     * Конвертация списка объектов БД в список доменных объектов
     * @param models список объектов БД
     * @return список доменных объектов
     */
    public static Collection<User> convertToUserDomain(Collection<UserModel> models){
        return models.stream()
                .map(UserConverter::convertToUserDomain)
                .collect(Collectors.toList());
    }

    /**
     * Конвертация доменного объекта в DTO
     * @param user доменный объект
     * @return DTO
     */
    public static UserData convertToUserDTO(User user){
        return new UserData(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getAvatar(),
                RegionConverter.convertToRegionDTO(user.getRegion()),
                GenreConverter.convertToGenreDTO(user.getGenres())
        );
    }

    /**
     * Конвертация списка доменных объектов в список DTO
     * @param users список доменных объектов
     * @return список DTO
     */
    public static Collection<UserData> convertToUserDTO(Collection<User> users){
        return users.stream()
                .map(UserConverter::convertToUserDTO)
                .collect(Collectors.toList());
    }
}
