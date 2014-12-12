package com.springapp.mvc.model;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class Game {

    private boolean active;
    private String id;
    private String hostId;
    private ArrayList<Player> players;
    private String lobbyId;

    private ArrayList<String> winners;
    private ArrayList<String> losers;
    private int numberOfPlayers;

    private String winnerUsername;
    private String hostUsername;

    private int startingHp;


    public Game(String hostId, String lobbyId) {
        active = true;
        IdService idService = new IdService();
        id = idService.getGameId("game");
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
        IdService idService = new IdService();
        this.id = idService.getGameId("game");
        this.hostId = game.getHostId();
        this.players = game.getPlayers();
        this.lobbyId = game.getLobbyId();

        winners = new ArrayList<String>();
        losers = new ArrayList<String>();

        this.startingHp = game.getStartingHp();

    }


    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public ArrayList<String> getWinners() {
        return winners;
    }

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
