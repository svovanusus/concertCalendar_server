package com.ccalendar.server.api.data;

import java.time.LocalDate;
import java.util.Collection;

public class EventData {
    private long id;
    private String title;
    private String poster;
    private RegionData region;
    private String place;
    private LocalDate date;
    private String artist;
    private String description;
    private boolean isFest;
    private Collection<GenreData> genres;
    private boolean liked;

    /**_CONSTRUCTORS_**/

    public EventData() {
    }

    public EventData(long id, String title, String poster, RegionData region, String place, LocalDate date, String artist, String description, boolean isFest, Collection<GenreData> genres, boolean liked) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.region = region;
        this.place = place;
        this.date = date;
        this.artist = artist;
        this.description = description;
        this.isFest = isFest;
        this.genres = genres;
        this.liked = liked;
    }

    /**_GETTERS_**/

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public RegionData getRegion() {
        return region;
    }

    public String getPlace() {
        return place;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFest() {
        return isFest;
    }

    public Collection<GenreData> getGenres() {
        return genres;
    }

    public boolean isLiked() {
        return liked;
    }

    /**_SETTERS_**/

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setRegion(RegionData region) {
        this.region = region;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFest(boolean fest) {
        isFest = fest;
    }

    public void setGenres(Collection<GenreData> genres) {
        this.genres = genres;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
