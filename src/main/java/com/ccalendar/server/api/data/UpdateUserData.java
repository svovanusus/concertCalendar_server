package com.ccalendar.server.api.data;

import com.ccalendar.server.db.model.GenreModel;
import com.ccalendar.server.db.model.RegionModel;

import java.util.Collection;

public class UpdateUserData {
    private String name;
    private RegionModel userRegion;
    private Collection<GenreModel> genres;

    /**_CONSTRUCTORS_**/

    public UpdateUserData() {
    }

    public UpdateUserData(String name, RegionModel userRegion, Collection<GenreModel> genres) {
        this.name = name;
        this.userRegion = userRegion;
        this.genres = genres;
    }

    /**_GETTERS_**/

    public String getName() {
        return name;
    }

    public RegionModel getUserRegion() {
        return userRegion;
    }

    public Collection<GenreModel> getGenres() {
        return genres;
    }

    /**_SETTERS_**/

    public void setName(String name) {
        this.name = name;
    }

    public void setUserRegion(RegionModel userRegion) {
        this.userRegion = userRegion;
    }

    public void setGenres(Collection<GenreModel> genres) {
        this.genres = genres;
    }
}
