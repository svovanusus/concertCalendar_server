package com.ccalendar.server.api.data;

public class GenreData {
    private long id;
    private String title;

    /**_CONSTRUCTORS_**/

    public GenreData() {
    }

    public GenreData(long id, String title){
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

    /**_SETTERS_**/

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
