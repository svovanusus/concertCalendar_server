package com.ccalendar.server.db.repository;

import com.ccalendar.server.db.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    /**
     * Найти пользователя по логину
     * @param login логин скомого пользователя
     * @return объект БД найденного пользователя в обёртке Optional
     */
    Optional<UserModel> findByLogin(String login);
}
