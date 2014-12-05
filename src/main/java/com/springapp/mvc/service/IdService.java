package com.springapp.mvc.service;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class IdService {

    private static int userId = 0;
    private static int gameId = 0;
    private static int notificationId = 0;


    public static int getUserId() {
        userId++;
        return userId;
    }

    public static int getGameId() {
        gameId++;
        return gameId;
    }

    public static int getNotificationId() {
        notificationId++;
        return notificationId;
    }

}
