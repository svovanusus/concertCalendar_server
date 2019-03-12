package com.ccalendar.server.domain.services.user;

import com.ccalendar.server.api.data.RegisterUserData;
import com.ccalendar.server.api.data.UpdateUserData;
import com.ccalendar.server.db.model.EventModel;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.db.repository.UserRepository;
import com.ccalendar.server.domain.exceptions.EventNotFoundException;
import com.ccalendar.server.domain.exceptions.UserException;
import com.ccalendar.server.domain.exceptions.UserInvalidParamsException;
import com.ccalendar.server.domain.exceptions.UserLoginExistsException;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
    public User registerUser(RegisterUserData user) throws UserException{
        if (user.getLogin() == null || user.getLogin().isEmpty()) throw new UserInvalidParamsException("Login");
        if (user.getPassword() == null || user.getPassword().isEmpty()) throw new UserInvalidParamsException("Password");
        if (user.getName() == null || user.getName().isEmpty()) throw new UserInvalidParamsException("Name");
        if (user.getUserRegion() == null) throw new UserInvalidParamsException("Region");
        if (userRepository.findByLogin(user.getLogin()).isPresent()){
            throw new UserLoginExistsException();
        }

        UserModel userModel = new UserModel();
        userModel.setLogin(user.getLogin());
        userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        userModel.setName(user.getName());
        userModel.setUserRegion(user.getUserRegion());
        userModel.setRoles(Collections.singleton(UserModel.Role.USER));
        userModel.setActivate(true);

        return UserConverter.convertToUserDomain(userRepository.save(userModel));
    }

    @Override
    public User updateUser(UpdateUserData userParams) throws UserException {
        if (userParams.getName() == null || userParams.getName().isEmpty()) throw new UserInvalidParamsException("Name");
        if (userParams.getUserRegion() == null) throw new UserInvalidParamsException("Region");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = (UserModel)auth.getPrincipal();

        user.setName(userParams.getName());
        user.setUserRegion(userParams.getUserRegion());
        if (userParams.getGenres() != null)
            user.setGenresForUser(new HashSet<>(userParams.getGenres()));
        else
            user.setGenresForUser(new HashSet<>());

        user = userRepository.save(user);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return UserConverter.convertToUserDomain(user);
    }

    @Override
    public boolean addEvent(UserModel userModel, EventModel eventModel) throws EventNotFoundException {
        if (eventModel == null)
            throw new EventNotFoundException();

        userModel.addEvent(eventModel);

        userModel = userRepository.save(userModel);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(userModel, userModel.getPassword(), userModel.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return userModel.getEventsForUser().contains(eventModel);
    }

    @Override
    public boolean delEvent(UserModel userModel, EventModel eventModel) throws EventNotFoundException {
        if (eventModel == null)
            throw new EventNotFoundException();

        userModel.removeEvent(eventModel);

        userModel = userRepository.save(userModel);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(userModel, userModel.getPassword(), userModel.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return userModel.getEventsForUser().contains(eventModel);
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
