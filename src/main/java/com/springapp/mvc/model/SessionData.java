package com.springapp.mvc.model;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class SessionData {

    String username;
    int userId;
    Lobby lobby;
    ArrayList<Notification> notifications = new ArrayList<Notification>();

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

}
