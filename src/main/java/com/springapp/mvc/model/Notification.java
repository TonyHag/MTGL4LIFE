package com.springapp.mvc.model;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class Notification {

    private int id, receiverId, gameId;
    private ArrayList<Integer> winners;



    public ArrayList<Integer> getWinners() {
        return winners;
    }

    public void setWinners(ArrayList<Integer> winners) {
        this.winners = winners;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int recieverId) {
        this.receiverId = recieverId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
