package com.springapp.mvc.model.notifications;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class Notification {


    private String id, receiverId;
    private String message;

    public Notification() {
    }

    public Notification(String salt) {
        IdService idService = new IdService();
        this.id = idService.getNotificationId(salt);
        System.out.println("Notification created with id: " + id);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }


}