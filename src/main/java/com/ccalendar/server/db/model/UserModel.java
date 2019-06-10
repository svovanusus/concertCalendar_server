package com.ccalendar.server.db.model;

import com.ccalendar.server.domain.model.Event;
import jdk.internal.jline.internal.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "login")
    @NotNull
    private String login;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "avatar_url")
    private String avatar;

    @Column(name = "activate")
    @NotNull
    private boolean activate;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region")
    private RegionModel userRegion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_genre",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private Set<GenreModel> genresForUser = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
    private Set<EventModel> eventsForUser = new HashSet<>();

    /**_CONSTRUCTORS_**/

    public UserModel() {
    }

    /**_GETTERS_**/

    public long getId() {
        return this.id;
    }

    public RegionModel getUserRegion() {
        return userRegion;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull String getLogin() {
        return this.login;
    }

    public @NotNull String getPassword() {
        return this.password;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean isActivate() {
        return activate;
    }

    public Set<GenreModel> getGenresForUser() {
        return this.genresForUser;
    }

    public Set<EventModel> getEventsForUser() {
        return this.eventsForUser;
    }

    /**_SETTERS_**/

    public void setId(long id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setGenresForUser(Set<GenreModel> genresForUser) {
        this.genresForUser = genresForUser;
    }

    public void setEventsForUser(Set<EventModel> eventsForUser) {
        this.eventsForUser = eventsForUser;
    }

    public void setUserRegion(RegionModel userRegion) {
        this.userRegion = userRegion;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    /**_USER_DETAILS_IMPLEMENTATION_**/

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isEnabled() {
        return isActivate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**_EVENTS_SUBSCRIBE_**/

    public void addEvent(@NotNull EventModel eventModel) {
        if (this.eventsForUser != null) this.eventsForUser.add(eventModel);
    }

    public void removeEvent(@NotNull EventModel eventModel) {
        if (eventsForUser != null) {
            EventModel foundEvent = null;

            for (EventModel event : eventsForUser) {
                if (event.getId() == eventModel.getId()) {
                    foundEvent = event;
                    break;
                }
            }

            if (foundEvent != null)
                eventsForUser.remove(foundEvent);
        }


        if (eventModel.getUsers() != null){
            UserModel foundUser = null;

            for (UserModel user : eventModel.getUsers()) {
                if (user.getId() == id) {
                    foundUser = user;
                    break;
                }
            }

            if (foundUser != null)
                eventModel.getUsers().remove(foundUser);
        }
    }

    /**_USER_ROLES_**/
    public enum Role implements GrantedAuthority {
        USER;

        @Override
        public String getAuthority() {
            return name();
        }
    }
}
