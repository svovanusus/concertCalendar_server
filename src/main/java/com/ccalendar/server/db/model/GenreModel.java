package com.ccalendar.server.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
public class GenreModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "alt_id")
    @Null
    private long altId;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genresForUser", cascade = CascadeType.REMOVE)
    private Set<UserModel> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genresForEvent", cascade = CascadeType.REMOVE)
    private Set<EventModel> events = new HashSet<>();

    /**_CONSTRUCTORS_**/

    public GenreModel(){
    }

    /**_GETTERS_**/

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public long getAltId() {
        return altId;
    }

    public Set<UserModel> getUsers(){
        return users;
    }

    public Set<EventModel> getEvents(){
        return events;
    }

    /**_SETTERS_**/

    public void setId(long id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }

    public void setUsers(Set<UserModel> users) { this.users = users; }

    public void setEvents(Set<EventModel> events) { this.events = events; }

    public void setAlt_id(long altId) {
        this.altId = altId;
    }

    /**_OBJECT_METHODS_**/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof GenreModel
                && ((GenreModel) obj).getId() == this.id
                && ((GenreModel) obj).getTitle().equals(this.title);
    }
}
