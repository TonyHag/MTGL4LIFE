package com.springapp.mvc.service;

import com.springapp.mvc.model.GameConfirmationData;
import com.springapp.mvc.model.Notification;
import com.springapp.mvc.model.Player;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class GameConfirmationService {
                /*
    public static void addGameConfirmationData(GameConfirmationData data) {
         MockDB.addGameConfirmationData(data);
    }


    public static void acceptGame(int gameId) {

        // Hent info om bruker fra session


        // Henter GameConfirmationdata
        GameConfirmationData confirmationData = MockDB.getConfirmationData(gameId);

        // Setter playerID map til true
        confirmationData.getPlayersAccepted().put()


        //  Om alle har acceptet, oppdater wins/loss
        boolean allAccepted = true;
        for (int i = 0; i < data.getAccepted().length; i++) {
            if(data.getAccepted()[i] == false) {
                  allAccepted = false;
            }
        }

        if(allAccepted) {

            System.out.println("all accepted, updating wins/losses");

            for(Integer i : data.getGameResult().getLosers()) {
                MockDB.addUserLoss(i);
            }

            for(Integer i : data.getGameResult().getWinners()) {
                MockDB.addUserWin(i);
            }

        }

        //  slett gameConfirmation
        MockDB.deleteGameConfirmation(data.getId());

        // Slett notifikasjon fra database
        MockDB.deleteNotification(notificationId);

    }

    // Fjerner notifikasjon, men oppdaterer ikke stats
    public static void rejectGame(int notificationId) {

    }
       */
}
