package com.springapp.mvc.service;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class IdService {

    private static int userId = 0;
    private static int gameId = 0;
    private static int notificationId = 0;
    private static int gameConfirmationId = 0;
    private static int lobbyId = 0;
    private static int leaderboardId = 0;

    public static int getLeaderboardId() {
        leaderboardId++;
        return leaderboardId;
    }

    public static int getLobbyId() {

        lobbyId++;
        return lobbyId;
    }

    public static int getGameConfirmationId() {
        gameConfirmationId++;
        return gameConfirmationId;
    }

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
