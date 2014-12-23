package com.springapp.mvc.model.leaderboard;

import com.springapp.mvc.model.statistics.FFAStats;
import com.springapp.mvc.model.statistics.THGStats;
import com.springapp.mvc.model.statistics.TotalStats;
import com.springapp.mvc.service.IdService;
import com.springapp.mvc.service.MockDB;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 07.12.14.
 */
public class Leaderboard {
    private String id;
    private String ownerId;
    private String name;
    private String description;
    private ArrayList<TotalStats> totalStats;
    private ArrayList<FFAStats> ffaStats;
    private ArrayList<THGStats> thgStats;


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

    public Leaderboard(String ownerId) {
        this.ownerId = ownerId;
        IdService idService = new IdService();
        this.id = idService.getLeaderboardId("leaderboard");
        this.ownerUsername = MockDB.getUsername(ownerId);
        totalStats = new ArrayList<TotalStats>();
        ffaStats = new ArrayList<FFAStats>();
        thgStats = new ArrayList<THGStats>();
    }

    public void addUser(String userId) {
        totalStats.add(new TotalStats(userId));
        ffaStats.add(new FFAStats(userId));
        thgStats.add(new THGStats(userId));

    }

    public void removeUser(String userId) {

        for(TotalStats stat : totalStats) {
            if(stat.getUserID().equals(userId)) {
                totalStats.remove(stat);
                break;
            }
        }

        for(FFAStats stat : ffaStats) {
            if(stat.getUserID().equals(userId)) {
                ffaStats.remove(stat);
                break;
            }
        }

        for(THGStats stat : thgStats) {
            if(stat.getUserID().equals(userId)) {
                thgStats.remove(stat);
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
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

    public ArrayList<TotalStats> getTotalStats() {
        return totalStats;
    }

    public void setTotalStats(ArrayList<TotalStats> playerStats) {
        this.totalStats = playerStats;
    }

    public ArrayList<FFAStats> getFfaStats() {
        return ffaStats;
    }

    public void setFfaStats(ArrayList<FFAStats> ffaStats) {
        this.ffaStats = ffaStats;
    }

    public ArrayList<THGStats> getThgStats() {
        return thgStats;
    }

    public void setThgStats(ArrayList<THGStats> thgStats) {
        this.thgStats = thgStats;
    }
}