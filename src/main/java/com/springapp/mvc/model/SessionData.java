package com.springapp.mvc.model;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class SessionData {

    String username;
    int userId;
    LobbyData lobbyData;
    GameData gameData;
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

    public LobbyData getLobbyData() {
        return lobbyData;
    }

    public void setLobbyData(LobbyData lobbyData) {
        this.lobbyData = lobbyData;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }
}
