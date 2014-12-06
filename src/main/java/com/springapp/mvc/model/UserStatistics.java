package com.springapp.mvc.model;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class UserStatistics {

    private String username;
    private int wins, losses, total;
    private double winPercentage;

    public UserStatistics() {
        username = "n/a";
        wins = 0;
        losses = 0;
        total = 0;
        winPercentage = 0.0;
    }

    public void addWin() {
        wins++;
        total++;
        winPercentage = (double) wins/total*100;
    }

    public void addLoss() {
        losses++;
        total++;
        winPercentage = (double) wins/total*100;

    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
