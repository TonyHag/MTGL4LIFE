package com.springapp.mvc.model;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 02.12.14.
 */
public class User {

    private String username, password, email;
    private int id;

    private UserStatistics stats;
    private ArrayList<Integer> leaderBoardIds;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

        this.id = IdService.getUserId();
        stats = new UserStatistics(this.id);
        stats.setUsername(username);
        leaderBoardIds = new ArrayList<Integer>();

    }

    public ArrayList<Integer> getLeaderBoardIds() {
        return leaderBoardIds;
    }

    public void setLeaderBoardIds(ArrayList<Integer> leaderBoardIds) {
        this.leaderBoardIds = leaderBoardIds;
    }

    public void addWin() {
        stats.addWin();
    }

    public void addLoss() {
        stats.addLoss();
    }

    public UserStatistics getStats() {
        return stats;
    }

    public void setStats(UserStatistics stats) {
        this.stats = stats;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
