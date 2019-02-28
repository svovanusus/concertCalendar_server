package com.ccalendar.server.api.data;

import java.io.Serializable;

public class ResultResponse<T> implements Serializable {

    private T data;
    private Meta meta;

    public ResultResponse(T data, Meta meta){
        this.data = data;
        this.meta = meta;
    }

    public ResultResponse(Status status, String message){
        this.meta = new Meta(message, status);
    }

    public ResultResponse(T data){
        this(Status.OK, null);
        this.data = data;
    }

    public ResultResponse(){
        this(null);
    }

    public T getData() {
        return this.data;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public class Meta {
        private String message;
        private Status status;

        public Meta(){}

        public Meta(String message, Status status){
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return this.message;
        }

        public Status getStatus() {
            return this.status;
        }
    }

    public enum Status {
        OK, ERROR, UNAUTHORIZED
    }
}
