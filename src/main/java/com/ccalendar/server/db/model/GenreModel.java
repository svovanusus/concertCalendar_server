package com.ccalendar.server.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genresForUser", cascade = CascadeType.REMOVE)
    private Set<UserModel> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genresForEvent")
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
}
