package com.springapp.mvc.model.notifications;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class Notification {


    private String id, receiverId, gameId;
    private ArrayList<String> winners;
    private String message;

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getWinners() {
        return winners;
    }

    public void setWinners(ArrayList<String> winners) {
        this.winners = winners;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }


}