package com.springapp.mvc.model;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class GameData {
    // Objekt som legges i session for å ta vare på info som skal brukes når gamet er over

    ArrayList<Player> players = new ArrayList<Player>();
    String host = null;
    int hostId = 0;
    int gameId = 0;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
