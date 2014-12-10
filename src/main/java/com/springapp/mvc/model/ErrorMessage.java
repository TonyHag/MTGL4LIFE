package com.springapp.mvc.model;

/**
 * Created by eirikskogland on 10.12.14.
 */
public class ErrorMessage {

    private String message;

    private String type;

    public ErrorMessage(String type, String message) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
