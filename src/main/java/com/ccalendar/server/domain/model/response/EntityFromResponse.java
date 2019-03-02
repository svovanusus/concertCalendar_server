package com.ccalendar.server.domain.model.response;

public class EntityFromResponse<T> {

    private T message;
    private int code;

    /**_CONSTRUCTORS_**/

    public EntityFromResponse() {
    }

    /**_GETTERS_**/

    public T getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    /**_SETTERS_**/

    public void setMessage(T message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
