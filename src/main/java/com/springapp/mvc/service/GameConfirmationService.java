package com.springapp.mvc.service;

import com.springapp.mvc.model.Notification;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class GameConfirmationService {


    public static void acceptGame(int notificationId) {
        Notification notification = MockDB.getNotification(notificationId);

        // Oppdater vinner
        MockDB.addUserWin(notification.getWinnerId());

        // Oppdater taper
        int loserId;
        // Sjekk hvem taperen er
        if(notification.getWinnerId() == notification.getSenderId()) {
            loserId = notification.getReceiverId();
        } else {
            loserId = notification.getSenderId();
        }
        MockDB.addUserLoss(loserId);

        // Slett notifikasjon fra database
        MockDB.deleteNotification(notificationId);

    }

    // Fjerner notifikasjon, men oppdaterer ikke stats
    public static void rejectGame(int notificationId) {

    }

}
