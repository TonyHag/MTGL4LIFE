package com.springapp.mvc.model;

import com.springapp.mvc.service.MockDB;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class GameConfirmationData {

    private String gameId;
    private ArrayList<String> accepted;
    private int playersRejected;
    private int numberOfPlayers = 1;
    private Game game;


    public GameConfirmationData(Game game) {
        this.game = game;

        gameId = game.getId();
        accepted = new ArrayList<String>();
        for(Player player : game.getPlayers()) {
            accepted.add(player.getUserId());
        }

        numberOfPlayers = game.getPlayers().size();
        playersRejected = 0;

    }

    public void playerRejected(String playerId){
        if(accepted.contains(playerId)) {
            accepted.remove(playerId);
            playersRejected++;
            System.out.println("GameConfirmation: Player rejected");
        }
    }

    public void playerAccepted(String playerId) {
        if(accepted.contains(playerId)) {
            accepted.remove(playerId);
            System.out.println("GameConfirmation: Player accepted");
        }
    }

    public ArrayList<String> getAccepted() {
        return accepted;
    }

    public void setAccepted(ArrayList<String> accepted) {
        this.accepted = accepted;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public int getPlayersRejected() {
        return playersRejected;
    }

    public void setPlayersRejected(int playersRejected) {
        this.playersRejected = playersRejected;
    }

}
