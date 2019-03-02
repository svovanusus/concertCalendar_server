package com.ccalendar.server.domain.model.response;

public class SubEvent {
    private long id;
    private String date;
    private Venue venue;
    private String image;

    public SubEvent() {
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getImage() {
        return image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
