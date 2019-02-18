package com.ccalendar.server.domain.model;

public class Genre {
    private long id;
    private String title;

    /**_CONSTRUCTORS_**/

    public Genre(long id, String title) {
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
        if (obj instanceof Genre){
            Genre genre = (Genre)obj;
            return genre.getId() == id && id > 0;
        }
        return false;
    }
}
