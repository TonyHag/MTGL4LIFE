package com.springapp.mvc.service;

import com.springapp.mvc.model.leaderboard.Leaderboard;
import com.springapp.mvc.model.statistics.FFAStats;
import com.springapp.mvc.model.statistics.OneVsOneStats;
import com.springapp.mvc.model.statistics.THGStats;
import com.springapp.mvc.model.statistics.TotalStats;

/**
 * Created by eirikskogland on 26.12.14.
 */
public class LeaderboardService {

    public void addPlayerToLeaderboard(String username, String userID, Leaderboard leaderboard) {

        leaderboard.getInvitedPlayerUsernames().remove(username);
        leaderboard.getTotalStats().add(new TotalStats(userID));
        leaderboard.getFfaStats().add(new FFAStats(userID));
        leaderboard.getThgStats().add(new THGStats(userID));
        leaderboard.getOneVsOneStats().add(new OneVsOneStats(userID));

        MockDB.updateLeaderboard(leaderboard);

    }

    public void addPlayerToWorldLeaderboard(String username, String userID) {

        Leaderboard leaderboard = MockDB.getLeaderboard("worldLeaderboard");

        leaderboard.getTotalStats().add(new TotalStats(userID));
        leaderboard.getFfaStats().add(new FFAStats(userID));
        leaderboard.getThgStats().add(new THGStats(userID));
        leaderboard.getOneVsOneStats().add(new OneVsOneStats(userID));
        MockDB.updateLeaderboard(leaderboard);

    }










}
