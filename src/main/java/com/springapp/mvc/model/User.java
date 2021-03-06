package com.springapp.mvc.model;

import com.springapp.mvc.model.statistics.FFAStats;
import com.springapp.mvc.model.statistics.OneVsOneStats;
import com.springapp.mvc.model.statistics.THGStats;
import com.springapp.mvc.model.statistics.TotalStats;
import com.springapp.mvc.service.IdService;
import com.springapp.mvc.service.LeaderboardService;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.RatingService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 02.12.14.
 */
public class User {

    private String username, password, email;
    private String id;
    private String country;
    private String city;

    private TotalStats totalStats;
    private FFAStats ffaStats;
    private THGStats thgStats;
    private OneVsOneStats oneVsOneStats;

    private int rating;

    private ArrayList<String> leaderBoardIds;

    public User(String username, String password, String email, String country, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.city = city;

        IdService idService = new IdService();
        this.id = idService.getUserId(username);

        rating = 1500;

        totalStats = new TotalStats(this.id);
        totalStats.setUsername(username);

        ffaStats = new FFAStats(this.id);
        ffaStats.setUsername(username);

        thgStats = new THGStats(this.id);
        thgStats.setUsername(username);

        oneVsOneStats = new OneVsOneStats(this.id);
        oneVsOneStats.setUsername(username);

        leaderBoardIds = new ArrayList<String>();

        MockDB.addUser(this);

        LeaderboardService leaderboardService = new LeaderboardService();
        leaderboardService.addPlayerToWorldLeaderboard(username, id);
    }

    public ArrayList<String> getLeaderBoardIds() {
        return leaderBoardIds;
    }

    public void setLeaderBoardIds(ArrayList<String> leaderBoardIds) {
        this.leaderBoardIds = leaderBoardIds;
    }

    public void addWin(String gameMode, int opponentRating) {
        System.out.println("User: Adding win to " + username);

        if(gameMode.equals("ffa")) {
            ffaStats.addWin();
        } else if(gameMode.equals("thg")) {
            thgStats.addWin();
        } else if(gameMode.equals("1v1")) {
            oneVsOneStats.addWin();
        }
        totalStats.addWin();
        rating = updateRating(opponentRating, 1);
        totalStats.setRating(rating);

    }

    public void addDraw(String gameMode, int opponentRating) {
        System.out.println("User: Adding draw to " + username);

        if(gameMode.equals("ffa")) {
            ffaStats.addDraw();
        } else if(gameMode.equals("thg")) {
            thgStats.addDraw();
        } else if(gameMode.equals("1v1")) {
            oneVsOneStats.addDraw();
        }
        totalStats.addDraw();
        rating = updateRating(opponentRating, 0.5);
        totalStats.setRating(rating);

    }

    public void addLoss(String gameMode, int opponentRating) {
        if (gameMode.equals("ffa")) {
            ffaStats.addLoss();
        } else if (gameMode.equals("thg")) {
            thgStats.addLoss();
        } else if(gameMode.equals("1v1")) {
            oneVsOneStats.addLoss();
        }
        totalStats.addLoss();
        rating = updateRating(opponentRating, 0);
        totalStats.setRating(rating);

    }

    public int updateRating(int opponentRating, double score) {
        RatingService ratingService = new RatingService();
        return ratingService.updateRating(rating, opponentRating, score);

    }

    public FFAStats getFfaStats() {
        return ffaStats;
    }

    public OneVsOneStats getOneVsOneStats() {
        return oneVsOneStats;
    }

    public void setOneVsOneStats(OneVsOneStats oneVsOneStats) {
        this.oneVsOneStats = oneVsOneStats;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
