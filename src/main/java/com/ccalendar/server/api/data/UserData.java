package com.ccalendar.server.api.data;

import java.util.Collection;

public class UserData {
    private long id;
    private String name;
    private String login;
    private String avatar;
    private RegionData region;
    private Collection<GenreData> genres;

    /**_CONSTRUCTORS_**/

    public UserData() {
    }

    public UserData(long id, String name, String login, String avatar, RegionData region, Collection<GenreData> genres) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.avatar = avatar;
        this.region = region;
        this.genres = genres;
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

    public String getAvatar() {
        return this.avatar;
    }

    public RegionData getRegion() {
        return region;
    }

    public Collection<GenreData> getGenres() {
        return genres;
    }

    /**_SETTERS_**/

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRegion(RegionData region) {
        this.region = region;
    }

    public void setGenres(Collection<GenreData> genres) {
        this.genres = genres;
    }
}
