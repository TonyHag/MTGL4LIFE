package com.springapp.mvc.model.game;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class Player {

    private String username;
    private String userId;
    private int rating;
    private int hp;
    private int poison;  //Vi trenger denne og :)
    private String team = "1";

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPoison () {return poison;}

    public void setPoison(int poison) {this.poison = poison;}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
