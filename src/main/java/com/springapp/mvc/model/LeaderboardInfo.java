package com.springapp.mvc.model;

/**
 * Created by eirikskogland on 08.12.14.
 */
public class LeaderboardInfo {

    private String id;
    private String name;
    private boolean isOwner;

    public LeaderboardInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
