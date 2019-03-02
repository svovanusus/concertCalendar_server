package com.ccalendar.server.domain.model.response;

public class VenueRegion{
    private long id;
    private String title;

    public VenueRegion() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}