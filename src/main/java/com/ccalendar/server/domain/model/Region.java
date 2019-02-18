package com.ccalendar.server.domain.model;

public class Region {
    private long id;
    private String title;

    /**_CONSTRUCTORS_**/

    public Region(long id, String title) {
        this.id = id;
        this.title = title;
    }

    /**_GETTERS_**/

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    /**_SYS_**/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Region){
            Region region = (Region)obj;
            return region.getId() == id && id > 0;
        }
        return false;
    }
}
