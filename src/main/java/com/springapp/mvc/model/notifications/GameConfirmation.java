package com.springapp.mvc.model.notifications;


import java.util.ArrayList;

/**
 * Created by eirikskogland on 18.12.14.
 */
public class GameConfirmation extends Notification {

    private String gameID;
    private ArrayList<String> winners;
    private ArrayList<String> losers;


    public GameConfirmation(String salt) {
        super(salt);

    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    @Override
    public ArrayList<String> getWinners() {
        return winners;
    }

    @Override
    public void setWinners(ArrayList<String> winners) {
        this.winners = winners;
    }

    public ArrayList<String> getLosers() {
        return losers;
    }

    public void setLosers(ArrayList<String> losers) {
        this.losers = losers;
    }
}
