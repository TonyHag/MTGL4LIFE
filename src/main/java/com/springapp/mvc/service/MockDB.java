package com.springapp.mvc.service;

import com.springapp.mvc.model.*;
import com.springapp.mvc.model.leaderboard.Leaderboard;
import com.springapp.mvc.model.notifications.GameConfirmation;
import com.springapp.mvc.model.notifications.LeaderboardInvitation;
import com.springapp.mvc.model.statistics.FFAStats;
import com.springapp.mvc.model.statistics.Statistics;
import com.springapp.mvc.model.statistics.THGStats;
import com.springapp.mvc.model.statistics.TotalStats;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 02.12.14.
 * Gidder ikke sette opp ekte database, så det får holde med en mock med samme funksjonalitet,
 * så blir det enkelt å sette opp en ekte utifra denne.
 */
public class MockDB {

    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Game> games = new ArrayList<Game>();
    public static ArrayList<GameConfirmation> gameConfirmations = new ArrayList<GameConfirmation>();
    public static ArrayList<LeaderboardInvitation> leaderboardInvitations = new ArrayList<LeaderboardInvitation>();

    public static ArrayList<GameConfirmationData> gameConfirmationDatas = new ArrayList<GameConfirmationData>();
    public static ArrayList<Lobby> lobbies = new ArrayList<Lobby>();
    public static ArrayList<Leaderboard> leaderboards = new ArrayList<Leaderboard>();






    // --------------- User operations -------------
    // ---------------------------------------------

    public static void addLeaderboardIdToUser(String userId, String leaderboardId) {
        for(User u : users) {
            if(u.getId().equals(userId)) {
                u.getLeaderBoardIds().add(leaderboardId);
            }
        }
    }

    public static ArrayList<String> getLeaderboardIdsForUser(String userId) {
        for(User u : users) {
            if(u.getId().equals(userId)) {
                return u.getLeaderBoardIds();
            }
        }
        return null;
    }

    // Sjekker om et brukernavn eksisterer
    public static boolean isUser (String username){
        boolean isUser = false;

        for(User u : users) {
            if(u.getUsername().equals(username)) {
                isUser = true;
                break;
            }
        }
        return isUser;
    }

    public static String getUsername(String id) {
        for(User u : users) {
            if(u.getId().equals(id)) {
                return u.getUsername();
            }
        }
        return null;
    }

    public static String getUserId(String username) {
        for(User u : users) {
            if(u.getUsername().equals(username)) {
                return u.getId();
            }
        }
        return null;
    }

    public static void addUser(User user) {
        boolean userAdded = users.add(user);
        System.out.println("User("+ user.getUsername() + ") added: " + userAdded);
    }

    public static boolean isUsernameAvailable(String username) {

        boolean available = true;

        if(isUser(username)) {
            System.out.println("MockDB: username taken");
            available = false;
        }

        return available;
    }

    public static void addUserWin(String userId, String gameMode) {
        for (User u : users) {
            if(u.getId().equals(userId)) {
                u.addWin(gameMode);
            }
        }
    }

    public static void addUserLoss(String userId, String gameMode) {
        for (User u : users) {
            if(u.getId().equals(userId)) {
                u.addLoss(gameMode);
            }
        }
    }

    public static Statistics getUserStatsByName(String username, String gameMode) {

        for(User u : users) {
            if(u.getUsername().equals(username)) {
                System.out.println("MockDB: Stats found for " + u.getTotalStats().getUsername());
                if(gameMode.equals("ffa")) {
                    return u.getFfaStats();
                } else if(gameMode.equals("thg")) {
                    return u.getThgStats();
                } else {
                    return u.getTotalStats();
                }
            }
        }
        return null;
    }

    public static Statistics getUserStatsById(String userId, String gameMode) {

        for(User u : users) {
            if(u.getId().equals(userId)) {
                System.out.println("MockDB: Stats found for " + u.getUsername());
                if(gameMode.equals("ffa")) {
                    return u.getFfaStats();
                } else if(gameMode.equals("thg")) {
                    return u.getThgStats();
                } else {
                    return u.getTotalStats();
                }
            }
        }
        return null;
    }

    // ---------------------------------------------
    // ---------------------------------------------





    // --------------- Password --------------------
    // ---------------------------------------------


    // Sjekker om passord oppgitt er det samme som lagret
    public static boolean passwordMatch(String username, String password) {
        boolean match = false;

        for(User u : users) {
            if(u.getUsername().equals(username)) {
                if(u.getPassword().equals(password))
                match = true;
                break;
            }
        }

        if(!match) {
            System.out.println("MockDB: incorrect password");
        }

        return match;
    }

    // ---------------------------------------------
    // ---------------------------------------------







    // ------------------ Game ---------------------
    // ---------------------------------------------


    public static void addGame(Game game) {
        games.add(game);
    }

    public static Game getGame(String id) {
        for(Game g : games) {
            if(g.getId().equals(id)) {
                return g;
            }
        }
        return null;
    }

    public static void deleteGame(String gameId) {
        for(Game game : games) {
            if(game.getId().equals(gameId)) {
                games.remove(game);
                break;
            }
        }
    }

    // ---------------------------------------------
    // ---------------------------------------------





    // ------------------ Lobby --------------------
    // ---------------------------------------------

    public static void addLobby(Lobby lobby) {
        lobbies.add(lobby);
    }

    public static Lobby getLobby(String lobbyId) {

        for(Lobby lobby : lobbies) {
            if(lobby.getId().equals(lobbyId)) {
                return lobby;
            }
        }

        System.out.println("Lobby not found");
        return null;
    }

    public static void deleteLobby(String lobbyId) {

        for(Lobby lobby : lobbies) {
            if(lobby.getId().equals(lobbyId)) {
                lobbies.remove(lobby);
                break;
            }
        }
    }

    public static void updateLobby(Lobby lobby) {
        for(Lobby l : lobbies) {
            if(l.getId().equals(lobby.getId())) {
                lobbies.remove(l);
                lobbies.add(lobby);
                break;
            }
        }
    }




    // ---------------------------------------------
    // ---------------------------------------------









    // --------------- Leaderboards ----------------
    // ---------------------------------------------

    public static boolean isPlayerInLeaderboard(String userId, String leaderboardId) {
        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                for(TotalStats stats : leaderboard.getTotalStats()) {
                    if(stats.getUserID().equals(userId)) {
                        return true;
                    }
                }
            }
        }
         return false;
    }

    public static void addUserWinToLeaderboard(String userId, String leaderboardId, String gameMode) {

        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                ArrayList<TotalStats> totalStats = leaderboard.getTotalStats();
                for(TotalStats s : totalStats) {
                    if(s.getUserID().equals(userId)) {
                        s.addWin();
                    }
                }
                leaderboard.setTotalStats(totalStats);

                if(gameMode.equals("ffa")) {
                    ArrayList<FFAStats> ffaStat = leaderboard.getFfaStats();
                    for(FFAStats s : ffaStat) {
                        if(s.getUserID().equals(userId)) {
                            s.addWin();
                        }
                    }
                    leaderboard.setFfaStats(ffaStat);
                } else if(gameMode.equals("thg")) {
                    ArrayList<THGStats> thgStats = leaderboard.getThgStats();
                    for(THGStats s : thgStats) {
                        if(s.getUserID().equals(userId)) {
                            s.addWin();
                        }
                    }
                    leaderboard.setThgStats(thgStats);
                }


                break;
            }

        }
    }

    public static void addUserLossToLeaderboard(String userId, String leaderboardId, String gameMode) {
        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                ArrayList<TotalStats> totalStats = leaderboard.getTotalStats();
                for(TotalStats s : totalStats) {
                    if(s.getUserID().equals(userId)) {
                        s.addLoss();
                    }
                }
                leaderboard.setTotalStats(totalStats);

                if(gameMode.equals("ffa")) {
                    ArrayList<FFAStats> ffaStat = leaderboard.getFfaStats();
                    for(FFAStats s : ffaStat) {
                        if(s.getUserID().equals(userId)) {
                            s.addLoss();
                        }
                    }
                    leaderboard.setFfaStats(ffaStat);
                } else if(gameMode.equals("thg")) {
                    ArrayList<THGStats> thgStats = leaderboard.getThgStats();
                    for(THGStats s : thgStats) {
                        if(s.getUserID().equals(userId)) {
                            s.addLoss();
                        }
                    }
                    leaderboard.setThgStats(thgStats);
                }

                break;
            }

        }
    }


    public static String getLeaderboardNameById(String leaderboardId) {
        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                return leaderboard.getName();
            }
        }
        return null;

    }

    public static String getOwnerId(String leaderboardId) {

        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                return leaderboard.getOwnerId();
            }
        }

        System.out.println("MockDB: Leaderboard not found");
        return null;
    }

    public static String getOwnerName(String leaderboardId) {

        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                return leaderboard.getOwnerUsername();
            }
        }

        System.out.println("MockDB: Leaderboard not found");
        return null;
    }

    public static void addLeaderboard(Leaderboard leaderboard) {
        leaderboards.add(leaderboard);
    }

    public static Leaderboard getLeaderboard(String id) {

        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(id)) {
                return leaderboard;
            }
        }

        System.out.println("MockDB: Leaderboard not found");
        return null;
    }

    public static void deleteLeaderboard(String id) {

        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(id)) {
                leaderboards.remove(leaderboard);
                break;
            }
        }
    }

    public static void updateLeaderboard(Leaderboard leaderboard) {
        for(Leaderboard l : leaderboards) {
            if(l.getId().equals(leaderboard.getId())) {
                leaderboards.remove(l);
                leaderboards.add(leaderboard);
                break;
            }
        }
    }

    public static void addPlayerToLeaderboard(String leaderboardId, String userId) {
        for(Leaderboard l : leaderboards) {
            if(l.getId().equals(leaderboardId)) {
                l.addUser(userId);
                break;
            }
        }
    }

    public static void removePlayerFromLeaderboard(String leaderboardId, String userId) {
        for(Leaderboard l : leaderboards) {
            if(l.getId().equals(leaderboardId)) {
                l.removeUser(userId);
                break;
            }
        }
    }



    // ---------------------------------------------
    // ---------------------------------------------








    // --------------- Notification ----------------
    // ---------------------------------------------


    public static void addNotification(GameConfirmation notification) {
        gameConfirmations.add(notification);
    }

    // Henter alle notifikasjonene til en mottaker
    public static ArrayList<GameConfirmation> getGameConfirmations(String userId) {

        ArrayList<GameConfirmation> userGameConfirmations = new ArrayList<GameConfirmation>();

        for(GameConfirmation n : gameConfirmations) {
            if(n.getReceiverId().equals(userId)) {
               userGameConfirmations.add(n);
            }
        }
        return userGameConfirmations;
    }

    // Henter alle notifikasjonene til en mottaker
    public static GameConfirmation getGameConfirmation(String notificationId) {

        for(GameConfirmation n : gameConfirmations) {
            if(n.getId().equals(notificationId)) {
                return n;
            }
        }
        return null;
    }

    public static void deleteGameConfirmation(String notificationId) {

        for(GameConfirmation n : gameConfirmations) {
            if(n.getId().equals(notificationId)) {
                System.out.println("MockDB: GameConfirmation removed for user " + getUsername(n.getReceiverId()));
                gameConfirmations.remove(n);
                break;
            }
        }
    }

    public static String getGameConfirmationOwnerId(String notificationId) {
        for(GameConfirmation n : gameConfirmations) {
            if(n.getId().equals(notificationId)) {
                return n.getReceiverId();
            }
        }
        return null;
    }

    // ---------------------------------------------
    // ---------------------------------------------



    public static void addLeaderboardInvitation(LeaderboardInvitation invitation) {
        leaderboardInvitations.add(invitation);
    }

    // Henter alle notifikasjonene til en mottaker
    public static ArrayList<LeaderboardInvitation> getLeaderboardInvitations(String userId) {

        ArrayList<LeaderboardInvitation> usersLeaderboardInvitations = new ArrayList<LeaderboardInvitation>();

        for(LeaderboardInvitation n : leaderboardInvitations) {
            if(n.getReceiverId().equals(userId)) {
                usersLeaderboardInvitations.add(n);
                System.out.println("found invitation");
            }
        }
        return usersLeaderboardInvitations;
    }

    // Henter alle notifikasjonene til en mottaker
    public static LeaderboardInvitation getLeaderboardInvitation(String invitationId) {

        for(LeaderboardInvitation n : leaderboardInvitations) {
            if(n.getId().equals(invitationId)) {
                return n;
            }
        }
        return null;
    }

    public static void deleteLeaderboardInvitation(String invitationId) {

        for(LeaderboardInvitation n : leaderboardInvitations) {
            if(n.getId().equals(invitationId)) {
                System.out.println("MockDB: LeaderboardInvitation removed for user " + getUsername(n.getReceiverId()));
                leaderboardInvitations.remove(n);
                break;
            }
        }
    }

    public static String getLeaderboardInvitationOwnerId(String invitationId) {
        for(LeaderboardInvitation n : leaderboardInvitations) {
            if(n.getId().equals(invitationId)) {
                return n.getReceiverId();
            }
        }
        return null;
    }

    // ------------- Game confirmation -------------
    // ---------------------------------------------




    public static GameConfirmationData getConfirmationData(String gameId) {
        for (GameConfirmationData data : gameConfirmationDatas) {

            if(data.getGameId().equals(gameId)) {
                return  data;
            }

        }
        return null;
    }

    public static void addGameConfirmationData(GameConfirmationData data) {
        gameConfirmationDatas.add(data);
        System.out.println("MockDB: ConfirmationData added, id: " + data.getGameId());
    }

    public static void deleteGameConfirmationData(String gameId)  {
        for(GameConfirmationData data : gameConfirmationDatas) {
            if (data.getGameId().equals(gameId)) {
                System.out.println("MockDB: ConfirmationData deletede, id: " + data.getGameId());
                gameConfirmationDatas.remove(data);
                break;
            }

        }
    }



    // ---------------------------------------------
    // ---------------------------------------------

}
