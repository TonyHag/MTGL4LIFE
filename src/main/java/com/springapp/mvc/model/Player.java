package com.springapp.mvc.model;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class Player {

    private String username;
    private int userId;
    private int hp;
    private int poison;  //Vi trenger denne og :)

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
