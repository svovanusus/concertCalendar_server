package com.ccalendar.server.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
public class EventModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alt_id")
    private long altId;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "poster_url")
    private String poster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region")
    private RegionModel eventRegion;

    @Column(name = "place")
    @NotNull
    private String place;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @Column(name = "artist")
    @NotNull
    private String artist;

    @Column(name = "description")
    private String description;

    @Column(name = "fest")
    @NotNull
    private boolean isFest;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinTable(name = "event_genre",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private Set<GenreModel> genresForEvent = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "eventsForUser", cascade = CascadeType.REMOVE)
    private Set<UserModel> users = new HashSet<>();

    /**_CONSTRUCTORS_**/

    public EventModel() {
    }

    /**_GETTERS_**/

    public long getId() {
        return id;
    }

    public long getAltId() {
        return altId;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public RegionModel getEventRegion() {
        return eventRegion;
    }

    public boolean isFest() {
        return isFest;
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

    public String getDescription(){
        return description;
    }

    public Set<GenreModel> getGenresForEvent(){
        return genresForEvent;
    }

    public Set<UserModel> getUsers() {
        return this.users;
    }

    /**_SETTERS_**/

    public void setId(long id) {
        this.id = id;
    }

    public void setAltId(long altId) {
        this.altId = altId;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public void setPoster(@Null String poster) {
        this.poster = poster;
    }

    public void setEventRegion(RegionModel eventRegion) {
        this.eventRegion = eventRegion;
    }

    public void setPlace(@NotNull String place) {
        this.place = place;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public void setArtist(@NotNull String artist) {
        this.artist = artist;
    }

    public void setDescription(@Null String description) {
        this.description = description;
    }

    public void setFest(@NotNull boolean fest) {
        isFest = fest;
    }

    public void setGenresForEvent(@Null Set<GenreModel> genresForEvent) {
        this.genresForEvent = genresForEvent;
    }

    public void setUsers(@Null Set<UserModel> users) {
        this.users = users;
    }
}
