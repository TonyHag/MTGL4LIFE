package com.springapp.mvc.service;

import com.springapp.mvc.model.*;

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


    // --------------- User operations -------------
    // ---------------------------------------------


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

    public static String getUsername(int id) {
        for(User u : users) {
            if(u.getId() == id) {
                return u.getUsername();
            }
        }

        return null;
    }

    public static int getUserId(String username) {
        for(User u : users) {
            if(u.getUsername().equals(username)) {
                return u.getId();
            }
        }
        return 0;
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


    public static void addUserWin(int userId) {
        for (User u : users) {
            if(u.getId() == userId) {

                u.addWin();

            }
        }
    }

    public static void addUserLoss(int userId) {
        for (User u : users) {
            if(u.getId() == userId) {
                u.addLoss();
            }
        }
    }

    public static UserStatistics getUserStats(String username) {

        for(User u : users) {
            if(u.getUsername().equals(username)) {
                System.out.println("MockDB: Stats found for " + u.getStats().getUsername());
                return u.getStats();
            }
        }
        return null;
    }

    public static UserStatistics getUserStats(int userId) {

        for(User u : users) {
            if(u.getId() == userId) {
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

    public static Game getGame(int id) {
        for(Game g : games) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public static void deleteGame(int gameId) {
        for(Game game : games) {
            if(game.getId() == gameId) {
                games.remove(game);
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
    public static ArrayList<Notification> getNotifications(int userId) {

        ArrayList<Notification> userNotifications = new ArrayList<Notification>();

        for(Notification n : notifications) {
            if(n.getReceiverId() == userId) {
               userNotifications.add(n);
            }
        }
        return userNotifications;
    }

    // Henter alle notifikasjonene til en mottaker
    public static Notification getNotification(int notificationId) {

        for(Notification n : notifications) {
            if(n.getId() == notificationId) {
                return n;
            }
        }
        return null;
    }

    public static void deleteNotification(int notificationId) {

        for(Notification n : notifications) {
            if(n.getId() == notificationId) {
                notifications.remove(n);
                System.out.println("MockDB: Notification removed");
                break;
            }
        }
    }

    public static int getNotificationOwnerId(int notificationId) {
        for(Notification n : notifications) {
            if(n.getId() == notificationId) {
                return n.getReceiverId();
            }
        }
        return 0;
    }

    // ---------------------------------------------
    // ---------------------------------------------








    // ------------- Game confirmation -------------
    // ---------------------------------------------



    public static GameConfirmationData getConfirmationData(int gameId) {
        for (GameConfirmationData data : gameConfirmations) {

            if(data.getGameId() == gameId) {
                return  data;
            }

        }
        return null;
    }

    public static void addGameConfirmationData(GameConfirmationData data) {
        gameConfirmations.add(data);
        System.out.println("MockDB: ConfirmationData added, id: " + data.getGameId());
    }

    public static void deleteGameConfirmation(int gameId)  {
        for(GameConfirmationData data : gameConfirmations) {
            if (data.getGameId() == gameId) {
                System.out.println("MockDB: ConfirmationData deletede, id: " + data.getGameId());
                gameConfirmations.remove(data);
                break;
            }

        }
    }



    // ---------------------------------------------
    // ---------------------------------------------

}
