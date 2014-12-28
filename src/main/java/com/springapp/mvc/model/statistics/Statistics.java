package com.springapp.mvc.model.statistics;

import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.RatingService;

/**
 * Created by eirikskogland on 19.12.14.
 */
public class Statistics {
    private String username;
    private String userID;
    private int wins, losses, draws, total;
    private double winPercentage;
    private int rating;

    public Statistics() {
    }

    public Statistics(String userID) {
        this.userID = userID;
        username = MockDB.getUsername(userID);
        wins = 0;
        losses = 0;
        total = 0;
        winPercentage = 0.0;
        rating = 1500;
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

    public void addDraw() {
        draws++;
        total++;

    }
    public void updateRating(int opponentRating, double score) {
        RatingService ratingService = new RatingService();
        rating = ratingService.updateRating(rating, opponentRating, score);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
