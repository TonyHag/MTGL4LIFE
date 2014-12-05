package com.springapp.mvc.service;

import com.springapp.mvc.model.Notification;
import com.springapp.mvc.model.Player;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class NotificationService {

    public static void sendNotifications(ArrayList<Player> players, int senderId, int gameId, String winner, int winnerId) {
        for (Player p : players) {
            if(p.getUserId() != senderId) { // hindrer notification til seg selv
                Notification notification = new Notification();
                notification.setId(IdService.getNotificationId());
                notification.setGameId(gameId);
                notification.setSenderId(senderId);
                notification.setReceiverId(p.getUserId());
                notification.setWinner(winner);
                notification.setWinnerId(winnerId);
                MockDB.addNotification(notification);
                System.out.println("Notification sent to: " + p.getUsername());
            }

        }
    }

    public static void deleteNotification(int userId, int notificationId) {

        if(MockDB.getNotificationOwnerId(notificationId) == userId) {
            MockDB.deleteNotification(notificationId);

        }

    }

    public static ArrayList<Notification> getNotifications(int userId) {

        return MockDB.getNotifications(userId);

    }


}
