package com.ccalendar.server.domain.model;

import java.util.Collection;

public class User {
    private long id;
    private String name;
    private String login;
    private String password;
    private String avatar;
    private Region region;
    private Collection<Genre> genres;
    private Collection<Event> events;

    /**_CONSTRUCTORS_**/

    public User(long id, String name, String login, String password, String avatar, Region region, Collection<Genre> genres, Collection<Event> events) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.region = region;
        this.genres = genres;
        this.events = events;
    }

    /**_GETTERS_**/

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Region getRegion() {
        return region;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    /**_SYS_**/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof User){
            User user = (User)obj;
            return user.getId() == id && id > 0;
        }
        return false;
    }
}
