package com.springapp.mvc.model.notifications;

/**
 * Created by eirikskogland on 18.12.14.
 */
public class LeaderboardInvitation extends Notification {

    private String leaderboardID;
    private String leaderboardName;
    private String leaderboardOwner;

    public String getLeaderboardID() {
        return leaderboardID;
    }

    public void setLeaderboardID(String leaderboardID) {
        this.leaderboardID = leaderboardID;
    }

    public String getLeaderboardName() {
        return leaderboardName;
    }

    public void setLeaderboardName(String leaderboardName) {
        this.leaderboardName = leaderboardName;
    }

    public String getLeaderboardOwner() {
        return leaderboardOwner;
    }

    public void setLeaderboardOwner(String leaderboardOwner) {
        this.leaderboardOwner = leaderboardOwner;
    }

    public LeaderboardInvitation() {
        super();
    }

    public LeaderboardInvitation(String salt) {
        super(salt);
    }
}
