package com.ccalendar.server.domain.services.user;

import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.exceptions.UserException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    public User getById(long id);
    public Collection<User> getAll();
    public User saveUser(UserModel user, boolean isANewUser) throws UserException;
}
