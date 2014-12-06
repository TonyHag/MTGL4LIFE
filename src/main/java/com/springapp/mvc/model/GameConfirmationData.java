package com.springapp.mvc.model;

import com.springapp.mvc.service.MockDB;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class GameConfirmationData {

    private int gameId;
    private ArrayList<Integer> accepted;
    private int playersRejected;
    private int numberOfPlayers = 1;
    private Game game;


    public GameConfirmationData(Game game) {
        this.game = game;

        gameId = game.getId();
        accepted = new ArrayList<Integer>();
        for(Player player : game.getPlayers()) {
            accepted.add(player.getUserId());
        }

        numberOfPlayers = game.getPlayers().size();
        playersRejected = 0;

    }

    public void playerRejected(int playerId){
        if(accepted.contains(playerId)) {
            accepted.remove(new Integer(playerId));
            playersRejected++;
            System.out.println("GameConfirmation: Player rejected");
        }
    }

    public void playerAccepted(int playerId) {
        if(accepted.contains(playerId)) {
            accepted.remove(new Integer(playerId));
            System.out.println("GameConfirmation: Player accepted");
        }
    }

    public ArrayList<Integer> getAccepted() {
        return accepted;
    }

    public void setAccepted(ArrayList<Integer> accepted) {
        this.accepted = accepted;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
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
