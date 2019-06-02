package com.ccalendar.server.domain.services.user;

import com.ccalendar.server.api.data.RegisterUserData;
import com.ccalendar.server.api.data.UpdateUserData;
import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.exceptions.EventNotFoundException;
import com.ccalendar.server.domain.exceptions.UserException;
import com.ccalendar.server.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    /**
     * Получить пользователя по его идентификатору
     * @param id идентификатор пользователя
     * @return доменный объект найденного пользователя
     */
    public User getById(long id);

    /**
     * Получить список всех пользователей
     * @return список пользователей из БД
     */
    public Collection<User> getAll();

    /**
     * Сохранить пользователя в БД
     * @param user объект нового пользователя, который нужно сохранить
     * @return Доменный объект сохранённого пользователя
     * @throws UserException Ошибка пользователя при сохранении
     */
    public User registerUser(RegisterUserData user) throws UserException;

    /**
     * Сохранить пользователя в БД
     * @param userParams параметры пользователя, которые необходимо оновить
     * @return Доменный объект сохранённого пользователя
     * @throws UserException Ошибка пользователя при сохранении
     */
    public User updateUser(UpdateUserData userParams) throws UserException;
}
