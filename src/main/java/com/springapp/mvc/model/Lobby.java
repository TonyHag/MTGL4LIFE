package com.springapp.mvc.model;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class Lobby {

    private String id;
    private String hostId;
    private String hostUsername;
    private ArrayList<String> invitedPlayerUsernames = new ArrayList<String>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private String inviteError = null;
    private String startError = null;

    public String getStartError() {
        return startError;
    }

    public void setStartError(String startError) {
        this.startError = startError;
    }

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Lobby() {

        IdService idService = new IdService();
        id = idService.getLobbyId("lobby");
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

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }
}
