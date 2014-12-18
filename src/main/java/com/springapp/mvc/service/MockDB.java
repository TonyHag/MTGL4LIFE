package com.springapp.mvc.service;

import com.springapp.mvc.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by eirikskogland on 02.12.14.
 * Gidder ikke sette opp ekte database, så det får holde med en mock med samme funksjonalitet,
 * så blir det enkelt å sette opp en ekte utifra denne.
 */
public class MockDB {

    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Game> games = new ArrayList<Game>();
    public static ArrayList<Notification> notifications = new ArrayList<Notification>();
    public static ArrayList<GameConfirmationData> gameConfirmations = new ArrayList<GameConfirmationData>();
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

    public static void addUserWin(String userId) {
        for (User u : users) {
            if(u.getId().equals(userId)) {

                u.addWin();

            }
        }
    }

    public static void addUserLoss(String userId) {
        for (User u : users) {
            if(u.getId().equals(userId)) {
                u.addLoss();
            }
        }
    }

    public static UserStatistics getUserStatsByName(String username) {

        for(User u : users) {
            if(u.getUsername().equals(username)) {
                System.out.println("MockDB: Stats found for " + u.getStats().getUsername());
                return u.getStats();
            }
        }
        return null;
    }

    public static UserStatistics getUserStatsById(String userId) {

        for(User u : users) {
            if(u.getId().equals(userId)) {
                System.out.println("MockDB: Stats found for " + u.getUsername());
                return u.getStats();
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
                for(UserStatistics stats : leaderboard.getPlayerStats()) {
                    if(stats.getUserId().equals(userId)) {
                        return true;
                    }
                }
            }
        }
         return false;
    }

    public static void addUserWinToLeaderboard(String userId, String leaderboardId) {
        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                ArrayList<UserStatistics> stats = leaderboard.getPlayerStats();
                for(UserStatistics s : stats) {
                    if(s.getUserId().equals(userId)) {
                        s.addWin();
                    }
                }
                leaderboard.setPlayerStats(stats);
                break;
            }

        }
    }

    public static void addUserLossToLeaderboard(String userId, String leaderboardId) {
        for(Leaderboard leaderboard : leaderboards) {
            if(leaderboard.getId().equals(leaderboardId)) {
                ArrayList<UserStatistics> stats = leaderboard.getPlayerStats();
                for(UserStatistics s : stats) {
                    if(s.getUserId().equals(userId)) {
                        s.addLoss();
                    }
                }
                leaderboard.setPlayerStats(stats);
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


    public static void addNotification(Notification notification) {
        notifications.add(notification);
    }

    // Henter alle notifikasjonene til en mottaker
    public static ArrayList<Notification> getNotifications(String userId) {

        ArrayList<Notification> userNotifications = new ArrayList<Notification>();

        for(Notification n : notifications) {
            if(n.getReceiverId().equals(userId)) {
               userNotifications.add(n);
            }
        }
        return userNotifications;
    }

    // Henter alle notifikasjonene til en mottaker
    public static Notification getNotification(String notificationId) {

        for(Notification n : notifications) {
            if(n.getId().equals(notificationId)) {
                return n;
            }
        }
        return null;
    }

    public static void deleteNotification(String notificationId) {

        for(Notification n : notifications) {
            if(n.getId().equals(notificationId)) {
                System.out.println("MockDB: Notification removed for user " + getUsername(n.getReceiverId()));
                notifications.remove(n);
                break;
            }
        }
    }

    public static String getNotificationOwnerId(String notificationId) {
        for(Notification n : notifications) {
            if(n.getId().equals(notificationId)) {
                return n.getReceiverId();
            }
        }
        return null;
    }

    // ---------------------------------------------
    // ---------------------------------------------








    // ------------- Game confirmation -------------
    // ---------------------------------------------




    public static GameConfirmationData getConfirmationData(String gameId) {
        for (GameConfirmationData data : gameConfirmations) {

            if(data.getGameId().equals(gameId)) {
                return  data;
            }

        }
        return null;
    }

    public static void addGameConfirmationData(GameConfirmationData data) {
        gameConfirmations.add(data);
        System.out.println("MockDB: ConfirmationData added, id: " + data.getGameId());
    }

    public static void deleteGameConfirmation(String gameId)  {
        for(GameConfirmationData data : gameConfirmations) {
            if (data.getGameId().equals(gameId)) {
                System.out.println("MockDB: ConfirmationData deletede, id: " + data.getGameId());
                gameConfirmations.remove(data);
                break;
            }

        }
    }



    // ---------------------------------------------
    // ---------------------------------------------

}
