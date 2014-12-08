package com.springapp.mvc.model;

import com.springapp.mvc.service.IdService;
import com.springapp.mvc.service.MockDB;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 07.12.14.
 */
public class Leaderboard {
    private int id;
    private int ownerId;
    private String name;
    private String description;
    private ArrayList<UserStatistics> playerStats;
    private String inviteErrorMessage;
    private String ownerUsername;
    private ArrayList<String> invitedPlayerUsernames = new ArrayList<String>();

    public ArrayList<String> getInvitedPlayerUsernames() {
        return invitedPlayerUsernames;
    }

    public void setInvitedPlayerUsernames(ArrayList<String> invitedPlayerUsernames) {
        this.invitedPlayerUsernames = invitedPlayerUsernames;
    }

    public String getInviteErrorMessage() {
        return inviteErrorMessage;
    }

    public void setInviteErrorMessage(String inviteErrorMessage) {
        this.inviteErrorMessage = inviteErrorMessage;
    }

    public Leaderboard(int ownerId) {
        this.ownerId = ownerId;
        this.id = IdService.getLeaderboardId();
        this.ownerUsername = MockDB.getUsername(ownerId);
        playerStats = new ArrayList<UserStatistics>();
    }

    public void addUser(int userId) {
        UserStatistics newPlayerStats = new UserStatistics(userId);
        playerStats.add(newPlayerStats);
    }

    public void removeUser(int userId) {
        for(UserStatistics stat : playerStats) {
            if(stat.getUserId() == userId) {
                playerStats.remove(stat);
                break;
            }
        }
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<UserStatistics> getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(ArrayList<UserStatistics> playerStats) {
        this.playerStats = playerStats;
    }
}
