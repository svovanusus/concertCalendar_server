package com.ccalendar.server.domain.model;

import java.time.LocalDate;
import java.util.Collection;

public class Event {
    private long id;
    private String title;
    private String poster;
    private Region region;
    private String place;
    private LocalDate date;
    private String artist;
    private String description;
    private boolean isFest;
    private Collection<Genre> genres;
    private boolean liked = false;

    /**_CONSTRUCTORS_**/

    public Event(long id, String title, String poster, Region region, String place, LocalDate date, String artist, String description, boolean isFest, Collection<Genre> genres) {
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

    public Region getRegion() {
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

    public Collection<Genre> getGenres() {
        return genres;
    }

    public boolean isLiked() {
        return liked;
    }

    /**_SETTERS_**/

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    /**_SYS_**/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Event){
            Event event = (Event)obj;
            return event.getId() == id && id > 0;
        }
        return false;
    }
}
