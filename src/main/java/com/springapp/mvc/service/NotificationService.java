package com.springapp.mvc.service;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.Notification;
import com.springapp.mvc.model.Player;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class NotificationService {

    public static void sendNotifications(Game game) {

        ArrayList<Player> players = game.getPlayers();
        for (Player p : players) {
                Notification notification = new Notification();

                notification.setId(IdService.getNotificationId());
                notification.setGameId(game.getId());
                notification.setReceiverId(p.getUserId());
                notification.setWinners(game.getWinners());
                MockDB.addNotification(notification);
                System.out.println("Notification sent to: " + p.getUsername());
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
