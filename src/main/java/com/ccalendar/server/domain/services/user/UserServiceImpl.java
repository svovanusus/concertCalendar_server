package com.ccalendar.server.domain.services.user;

import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.db.repository.UserRepository;
import com.ccalendar.server.domain.exceptions.UserException;
import com.ccalendar.server.domain.exceptions.UserInvalidParamsException;
import com.ccalendar.server.domain.exceptions.UserLoginExistsException;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User getById(long id) {
        return UserConverter.convertToUserDomain(userRepository.findById(id).get());
    }

    @Override
    public Collection<User> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserConverter::convertToUserDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User saveUser(UserModel user, boolean isANewUser) throws UserException {
        if (user.getLogin() == null || user.getLogin().isEmpty()) throw new UserInvalidParamsException("Login");
        if (user.getPassword() == null || user.getPassword().isEmpty()) throw new UserInvalidParamsException("Password");
        if (user.getName() == null || user.getName().isEmpty()) throw new UserInvalidParamsException("Name");
        if (user.getUserRegion() == null) throw new UserInvalidParamsException("Region");
        if (isANewUser) {
            if (userRepository.findByLogin(user.getLogin()).isPresent()){
                throw new UserLoginExistsException(user.getLogin());
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(UserModel.Role.USER));
            user.setActivate(true);
        }
        return UserConverter.convertToUserDomain(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByLogin(username);
        if (user.isPresent())
            return user.get();
        else throw new UsernameNotFoundException("User not found!");
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

}
