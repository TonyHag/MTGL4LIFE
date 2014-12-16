package com.springapp.mvc.model;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 14.12.14.
 */
public class Team {
    ArrayList<Player> players;

    int hp;
    int poisonCounter;
    String teamName;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPoisonCounter() {
        return poisonCounter;
    }

    public void setPoisonCounter(int poisonCounter) {
        this.poisonCounter = poisonCounter;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
