package com.ccalendar.server.domain.model.response;

import java.util.List;

public class EventFromEntity {
    private long id;
    private String title;
    private String description;
    private List<SubEvent> subevents;
    private List<TagGroup> taggroups;

    public EventFromEntity() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<SubEvent> getSubevents() {
        return subevents;
    }

    public List<TagGroup> getTaggroups() {
        return taggroups;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubevents(List<SubEvent> subevents) {
        this.subevents = subevents;
    }

    public void setTaggroups(List<TagGroup> taggroups) {
        this.taggroups = taggroups;
    }
}
