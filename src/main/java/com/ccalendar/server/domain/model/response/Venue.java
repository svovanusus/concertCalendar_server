package com.ccalendar.server.domain.model.response;

public class Venue {
    private String title;
    private VenueRegion region;

    public Venue() {
    }

    public String getTitle() {
        return title;
    }

    public VenueRegion getRegion() {
        return region;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRegion(VenueRegion region) {
        this.region = region;
    }
}
