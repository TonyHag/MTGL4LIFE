package com.springapp.mvc.service;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.notifications.GameConfirmation;
import com.springapp.mvc.model.notifications.LeaderboardInvitation;
import com.springapp.mvc.model.notifications.Notification;
import com.springapp.mvc.model.Player;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class NotificationService {

    public void sendGameConfirmations(Game game) {

        ArrayList<Player> players = game.getPlayers();
        for (Player p : players) {

            GameConfirmation gameConfirmation = new GameConfirmation(p.getUserId());
            gameConfirmation.setGameID(game.getId());
            gameConfirmation.setReceiverId(p.getUserId());
            gameConfirmation.setWinners(game.getWinners());
            gameConfirmation.setMessage("Host: " + game.getHostUsername() + ", winner: " + game.getWinnerUsername());
            MockDB.addNotification(gameConfirmation);

            /*
            Notification notification = new Notification(p.getUserId());
            notification.setType("gameConfirmation");
            notification.setGameId(game.getId());
            notification.setReceiverId(p.getUserId());
            notification.setWinners(game.getWinners());
            notification.setMessage("Host: " + game.getHostUsername() + ", winner: " + game.getWinnerUsername());
            MockDB.addNotification(notification);
            */System.out.println("Notification sent to: " + p.getUsername());

        }
    }

    public void sendLeaderboardInvitation(String userId, String senderId, String leaderboardId) {

        LeaderboardInvitation invitation = new LeaderboardInvitation(userId);
        invitation.setLeaderboardID(leaderboardId);
        invitation.setLeaderboardOwner(MockDB.getOwnerName(leaderboardId));
        invitation.setLeaderboardName(MockDB.getLeaderboardNameById(leaderboardId));
        invitation.setReceiverId(userId);
        invitation.setMessage(invitation.getLeaderboardOwner() + " invited you to the leaderboard " + invitation.getLeaderboardName());


        //Notification notification = new Notification(userId);
        //notification.setType("leaderboardInvitation");
        //notification.setLeaderboardId(leaderboardId);
        //notification.setReceiverId(userId);
        //notification.setMessage(MockDB.getUsername(senderId) + " invited you to the leaderboard " + MockDB.getLeaderboardNameById(leaderboardId));
        MockDB.addLeaderboardInvitation(invitation);
        System.out.println("Notification sent to: " + MockDB.getUsername(userId));

    }

    public void deleteNotification(String userId, String notificationId) {

        if(MockDB.getGameConfirmationOwnerId(notificationId).equals(userId)) {
            MockDB.deleteGameConfirmation(notificationId);
        } else if(MockDB.getLeaderboardNameById(notificationId).equals(userId)) {
            MockDB.deleteLeaderboardInvitation(notificationId);
        }
    }

    /*
    public ArrayList<Notification> getNotifications(String userId) {

        return MockDB.getNotifications(userId);

    } */


}
