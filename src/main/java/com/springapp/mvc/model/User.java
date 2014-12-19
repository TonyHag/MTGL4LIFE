package com.springapp.mvc.model;

import com.springapp.mvc.model.statistics.FFAStats;
import com.springapp.mvc.model.statistics.THGStats;
import com.springapp.mvc.model.statistics.TotalStats;
import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 02.12.14.
 */
public class User {

    private String username, password, email;
    private String id;

    private TotalStats totalStats;
    private FFAStats ffaStats;
    private THGStats thgStats;

    private ArrayList<String> leaderBoardIds;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

        IdService idService = new IdService();
        this.id = idService.getUserId(username);

        totalStats = new TotalStats(this.id);
        totalStats.setUsername(username);

        ffaStats = new FFAStats(this.id);
        ffaStats.setUsername(username);

        thgStats = new THGStats(this.id);
        thgStats.setUsername(username);


        leaderBoardIds = new ArrayList<String>();

    }

    public ArrayList<String> getLeaderBoardIds() {
        return leaderBoardIds;
    }

    public void setLeaderBoardIds(ArrayList<String> leaderBoardIds) {
        this.leaderBoardIds = leaderBoardIds;
    }

    public void addWin(String gameMode) {
        if(gameMode.equals("ffa")) {
            ffaStats.addWin();
        } else if(gameMode.equals("thg")) {
            thgStats.addWin();
        }
        totalStats.addWin();
    }

    public void addLoss(String gameMode) {
        if (gameMode.equals("ffa")) {
            ffaStats.addLoss();
        } else if (gameMode.equals("thg")) {
            thgStats.addLoss();
        }
        totalStats.addLoss();
    }

    public FFAStats getFfaStats() {
        return ffaStats;
    }

    public void setFfaStats(FFAStats ffaStats) {
        this.ffaStats = ffaStats;
    }

    public THGStats getThgStats() {
        return thgStats;
    }

    public void setThgStats(THGStats thgStats) {
        this.thgStats = thgStats;
    }

    public TotalStats getTotalStats() {
        return totalStats;
    }

    public void setTotalStats(TotalStats stats) {
        this.totalStats = stats;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
