package com.ccalendar.server.domain.services.user;

import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.exceptions.UserException;
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
     * @param user объект БД, который нужно сохранить
     * @param isANewUser флаг, является ли пользователь новым
     * @return Доменный объект сохранённого пользователя
     * @throws UserException Ошибка пользователя при сохранении
     */
    public User saveUser(UserModel user, boolean isANewUser) throws UserException;

    /**
     * Сохранить пользователя в БД
     * @param user объект БД, который нужно сохранить
     * @return Доменный объект сохранённого пользователя
     * @throws UserException Ошибка пользователя при сохранении
     */
    default public User saveUser(UserModel user) throws UserException {
        return saveUser(user, false);
    }
}
