package com.springapp.mvc.service;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.Notification;
import com.springapp.mvc.model.Player;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class NotificationService {

    public void sendNotifications(Game game) {

        ArrayList<Player> players = game.getPlayers();
        for (Player p : players) {

            Notification notification = new Notification();
            notification.setType("gameConfirmation");
            notification.setGameId(game.getId());
            notification.setReceiverId(p.getUserId());
            notification.setWinners(game.getWinners());
            MockDB.addNotification(notification);
            System.out.println("Notification sent to: " + p.getUsername());
        }
    }

    public void sendLeaderboardInvitation(int userId, int senderId, int leaderboardId) {

        Notification notification = new Notification();
        notification.setType("leaderboardInvitation");
        notification.setLeaderboardId(leaderboardId);
        notification.setReceiverId(userId);
        notification.setMessage(MockDB.getUsername(senderId) + " invited you to the leaderboard " + MockDB.getLeaderboardNameById(leaderboardId));
        MockDB.addNotification(notification);
        System.out.println("Notification sent to: " + MockDB.getUsername(userId));

    }

    public void deleteNotification(int userId, int notificationId) {

        if(MockDB.getNotificationOwnerId(notificationId) == userId) {
            MockDB.deleteNotification(notificationId);

        }

    }

    public ArrayList<Notification> getNotifications(int userId) {

        return MockDB.getNotifications(userId);

    }


}
