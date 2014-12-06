package com.springapp.mvc.model;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class LobbyData {

    private int id;
    private int hostId;
    private String hostUsername;
    ArrayList<String> invitedPlayerUsernames = new ArrayList<String>();
    ArrayList<Player> players = new ArrayList<Player>();
    String inviteError = null;


    public LobbyData() {
        id = IdService.getLobbyId();
    }

    public ArrayList<String> getInvitedPlayerUsernames() {
        return invitedPlayerUsernames;
    }

    public void setInvitedPlayerUsernames(ArrayList<String> invitedPlayerUsernames) {
        this.invitedPlayerUsernames = invitedPlayerUsernames;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getInviteError() {
        return inviteError;
    }

    public void setInviteError(String inviteError) {
        this.inviteError = inviteError;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }
}
