package com.ccalendar.server.api.data;

public class RegionData {
    private long id;
    private String title;

    /**_CONSTRUCTORS_**/

    public RegionData(){}

    public RegionData(long id, String title){
        this.id = id;
        this.title = title;
    }

    /**_GETTERS_**/

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    /**_SETTERS_**/

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
