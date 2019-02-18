package com.ccalendar.server.domain.util;

import com.ccalendar.server.api.data.UserData;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

public final class UserConverter {
    private UserConverter(){}

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

    public static Collection<User> convertToUserDomain(Collection<UserModel> models){
        return models.stream()
                .map(UserConverter::convertToUserDomain)
                .collect(Collectors.toList());
    }

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

    public static Collection<UserData> convertToUserDTO(Collection<User> users){
        return users.stream()
                .map(UserConverter::convertToUserDTO)
                .collect(Collectors.toList());
    }
}
