package com.springapp.mvc.model;

/**
 * Created by eirikskogland on 08.12.14.
 */
public class LeaderboardInfo {

    private int id;
    private String name;

    public LeaderboardInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
