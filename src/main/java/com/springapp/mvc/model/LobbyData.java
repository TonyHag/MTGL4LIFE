package com.springapp.mvc.model;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class LobbyData {

    ArrayList<String> invitedPlayerUsernames = new ArrayList<String>();
    ArrayList<Player> players = new ArrayList<Player>();
    String host = null;
    int hostId = 0;
    String inviteError = null;


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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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
}
