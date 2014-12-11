package com.springapp.mvc.model;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class Game {

    private boolean active;
    private int id;
    private int hostId;
    private ArrayList<Player> players;
    private int lobbyId;

    private ArrayList<Integer> winners;
    private ArrayList<Integer> losers;
    private int numberOfPlayers;

    private String winnerUsername;
    private String hostUsername;

    private int startingHp;


    public Game(int hostId, int lobbyId) {
        active = true;
        id = IdService.getGameId();
        this.hostId = hostId;
        this.lobbyId = lobbyId;
        startingHp = 20;

    }

    public String getWinnerUsername() {
        return winnerUsername;
    }

    public void setWinnerUsername(String winnerUsername) {
        this.winnerUsername = winnerUsername;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    // Konstruktør til å kopiere et game, men med ny id
    public Game(Game game) {
        this.active = true;
        this.id = IdService.getGameId();
        this.hostId = game.getHostId();
        this.players = game.getPlayers();
        this.lobbyId = game.getLobbyId();

        winners = new ArrayList<Integer>();
        losers = new ArrayList<Integer>();

        this.startingHp = game.getStartingHp();

    }


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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public ArrayList<Integer> getWinners() {
        return winners;
    }

    public void setWinners(ArrayList<Integer> winners) {
        this.winners = winners;
    }

    public ArrayList<Integer> getLosers() {
        return losers;
    }

    public void setLosers(ArrayList<Integer> losers) {
        this.losers = losers;
    }
}
