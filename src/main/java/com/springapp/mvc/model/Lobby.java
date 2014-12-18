package com.springapp.mvc.model;

import com.springapp.mvc.service.IdService;

import java.util.ArrayList;

/**
 * Created by eirikskogland on 05.12.14.
 */
public class Lobby {

    private String id;
    private String hostId;
    private String hostUsername;
    private ArrayList<Player> players = new ArrayList<Player>();
    private String inviteError = null;
    private String startError = null;

    private ArrayList<Team> teams = new ArrayList<Team>();

    private ArrayList<Player> team1 = new ArrayList<Player>();
    private ArrayList<Player> team2 = new ArrayList<Player>();
    private boolean ready = false;


    public ArrayList<Player> getTeam1() {
        return team1;
    }

    public void setTeam1(ArrayList<Player> team1) {
        this.team1 = team1;
    }

    public ArrayList<Player> getTeam2() {
        return team2;
    }

    public void setTeam2(ArrayList<Player> team2) {
        this.team2 = team2;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void assignTeam(String userId, String team) {
        System.out.println("Assigning team");
        boolean onTeam = false;



        if(team.equals("1")) {
            onTeam = false;
            for(Player p : team1) {
                if(p.getUserId().equals(userId)) { // hvis spiller er på laget fra før
                    System.out.println("already on team 1");
                    onTeam = true;
                }
            }
            if(!onTeam) {
                changeTeam(userId);
            }

        } else if(team.equals("2")) {
            onTeam = false;
            for(Player p : team2) {
                if(p.getUserId().equals(userId)) { // hvis spiller er på laget fra før
                    System.out.println("already on team 2");
                    onTeam = true;
                }
            }
            if(!onTeam) {
                changeTeam(userId);
            }
        }


    }

    public void setPlayerTeam(String userId, String team) {
        for(Player p : players) {
            if(p.getUserId().equals(userId)) {
                p.setTeam(team);
                break;
            }
        }
    }


    public void changeTeam(String userId) {
        System.out.println("changing team");
        boolean teamChanged = false;
        for(Player p : team1) {
            if(p.getUserId().equals(userId)) {
                setPlayerTeam(p.getUserId(), "2");
                p.setTeam("2");
                team2.add(p); // legger til andre laget

                team1.remove(p); // fjerner fra opprinnelig lag
                teamChanged = true;
                break;
            }
        }

        if(!teamChanged) {
            for(Player p : team2) {
                if(p.getUserId().equals(userId)) {
                    setPlayerTeam(p.getUserId(), "1");
                    p.setTeam("1");
                    team1.add(p); // legger til andre laget
                    team2.remove(p); // fjerner fra opprinnelig lag
                    teamChanged = true;
                    break;
                }
            }
        }
        System.out.println("teamChanged: " + teamChanged);


    }

/*
    public void changeTeam(String userId) {
        // hvis spiller er i team 1
        Team team1 = teams.get(0);
        if(team1.hasPlayer(userId)) {

            Team team2 = teams.get(1);
            team2.addPlayer(team);
        }
            // flytt til team 2

        // flytt til team 1



    } */

    public boolean isTeamsReady(int maxPlayers) {
        if(team1.size() == maxPlayers/2 && team2.size() == maxPlayers/2) {
            return true;
        }
        return false;
    }

    private String gameMode = null;

    public String getStartError() {
        return startError;
    }

    public void setStartError(String startError) {
        this.startError = startError;
    }

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Lobby() {

        IdService idService = new IdService();
        id = idService.getLobbyId("lobby");
        gameMode = "ffa";



    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getInviteError() {
        return inviteError;
    }

    public void setInviteError(String inviteError) {
        this.inviteError = inviteError;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }
}
