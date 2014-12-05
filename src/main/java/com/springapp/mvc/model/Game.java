package com.springapp.mvc.model;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class Game {

    private ArrayList<Player> players;
    private int id;
    private int startingHp;
    private int hostId;

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartingHp() {
        return startingHp;
    }

    public void setStartingHp(int startingHp) {
        this.startingHp = startingHp;
    }
}
