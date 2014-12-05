package com.springapp.mvc.model;

/**
 * Created by eirikskogland on 02.12.14.
 */
public class User {

    private String username, password, email;
    private int id;

    private UserStatistics stats;

    public User(int id, String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

        this.id = id;
        stats = new UserStatistics();
        stats.setUsername(username);

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
