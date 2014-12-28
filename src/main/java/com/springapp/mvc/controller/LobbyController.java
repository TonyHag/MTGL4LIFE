package com.springapp.mvc.controller;

import com.springapp.mvc.model.game.Game;
import com.springapp.mvc.model.game.Lobby;
import com.springapp.mvc.model.game.Player;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eirikskogland on 03.12.14.
 */
@Controller
public class LobbyController {


    @RequestMapping(value = "/lobby", method = RequestMethod.GET)
    public String getLobbyPage(ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        String userID = sessionService.getUserId();
        model.addAttribute("user", username);


        int numberOfNotifications = sessionService.getNumberOfNotifications();
        model.addAttribute("numberOfNotifications", numberOfNotifications);

        // Hvis bruker har et aktivt game, redirect til game
        if(sessionService.getActiveGame() != null) {
            return "redirect:/game";
        }

        Lobby lobby;
        // Hvis lobby ikke finnes
        if(sessionService.getLobby() == null) {

            lobby = createLobby(userID, username);
            sessionService.setLobby(lobby);

        } else {
            lobby = sessionService.getLobby();
        }

        model.addAttribute(lobby);
        model.addAttribute("inviteError",sessionService.getErrorMessage("inviteError"));
        model.addAttribute("startError",sessionService.getErrorMessage("startError"));

        return "lobby";


    }

    @RequestMapping(value = "/lobby/selectTeam", method = RequestMethod.POST)
    public String changeTeam(ModelMap model, HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("team") String team){
        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);

        Lobby lobby = sessionService.getLobby();
        String lobbyId = sessionService.getLobby().getId();

            System.out.println("Trying to change team");
            lobby.assignTeam(userId, team);
            sessionService.setLobby(lobby);
            return "redirect:/lobby";


    }

    @RequestMapping(value = "/lobby/changeGameMode", method = RequestMethod.POST)
    public String changeGameMode(ModelMap model, HttpServletRequest request, @RequestParam(value = "gameMode") String gameMode, @RequestParam(value = "lobbyId") String lobbyId) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);

        Lobby lobby =  sessionService.getLobby();

        if(lobby != null) {

                // set gamemode
                lobby.setGameMode(gameMode);

                return "redirect:/lobby";



        } else { // returner til ny lobby om lobby ikke finnes
            return "redirect:/lobby";
        }


    }

    @RequestMapping(value = "/lobby/removePlayer")
    public String removePlayer(ModelMap model, HttpServletRequest request, @RequestParam(value="removePlayer") String removePlayer) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);

        String lobbyId = sessionService.getLobby().getId();
        Lobby lobby = sessionService.getLobby();

            // Fjerner invitert spiller hvis spiller er med
            for(Player p : lobby.getPlayers()) {
                if(p.getUsername().equals(removePlayer)) {
                    lobby.getPlayers().remove(p);
                     break;
                }
            }
            sessionService.setLobby(lobby);
            return "redirect:/lobby";

    }

    @RequestMapping(value = "/lobby/invite", method = RequestMethod.POST)
    public String invitePlayer(ModelMap model, HttpServletRequest request, @RequestParam(value="invitePlayer") String invitePlayer) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);

        Lobby lobby = sessionService.getLobby();

        // Sjekk at man ikke inviterer seg selv
        if(username.equals(invitePlayer)) {
            sessionService.setErrorMessage("inviteError", "Invite a friend!");
            sessionService.setLobby(lobby);
            return "redirect:/lobby";

        }

        // Sjekk at man ikke inviterer samme flere ganger
        for(Player player : lobby.getPlayers()) {
            if(player.getUsername().equals(invitePlayer)) {
                sessionService.setErrorMessage("inviteError", "Player already invited");
                sessionService.setLobby(lobby);
                return "redirect:/lobby";

            }
        }

        // Sjekk at spiller eksisterer
        if(MockDB.isUser(invitePlayer)) {
            sessionService.setErrorMessage("inviteError", null);
            Player newPlayer = new Player();
            newPlayer.setUsername(invitePlayer);
            newPlayer.setUserId(MockDB.getUserId(invitePlayer));
            newPlayer.setHp(20);
            newPlayer.setRating(MockDB.getRatingById(newPlayer.getUserId()));
            lobby.getPlayers().add(newPlayer);
            lobby.getTeam1().add(newPlayer);
            sessionService.setLobby(lobby);
            return "redirect:/lobby";

        } else {

            sessionService.setErrorMessage("inviteError", "Player not found!");
            sessionService.setLobby(lobby);
            return "redirect:/lobby";
        }
    }



    @RequestMapping(value ="/lobby/startGame", method = RequestMethod.GET)
    public String startGame(ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);


        if(sessionService.getLobby() != null) { // hvis lobby finnes

            Lobby lobby = sessionService.getLobby();

            if(lobby.getGameMode().equals("ffa")) {
                if(lobby.getPlayers().size() > 2) {  // sjekke at man ikke starter game med 1 spiller
                    //lobby.setPlayers(new ArrayList<Player>()); // hvis lobby har spillere fra før//  hindrer dobbelt opp med spillere
                    lobby.setStartError("");

                    // Opprett nytt game

                    Game game = createFFAGame(lobby);
                    sessionService.setGame(game);
                    sessionService.setActiveGame(game.getId());


                    return "redirect:/game";
                } else {
                    sessionService.setErrorMessage("startError", "FFA requires 3 or more players");
                }


            } else if(lobby.getGameMode().equals("thg")) {
                if(lobby.isTeamsReady(4)) {

                    sessionService.setErrorMessage("startError", "");
                    Game game = createTHGGame(lobby);
                    sessionService.setGame(game);

                    sessionService.setActiveGame(game.getId());

                    return "redirect:/game";

                } else {
                    sessionService.setErrorMessage("startError", "Teams not ready, there must be two players on each team");
                }

            } else if(lobby.getGameMode().equals("1v1")) {
                if(lobby.getPlayers().size() == 2){
                    lobby.setStartError("");

                    // Opprett nytt game

                    Game game = createFFAGame(lobby);
                    sessionService.setGame(game);
                    sessionService.setActiveGame(game.getId());
                    return "redirect:/game";

                }  else {
                    if(lobby.getPlayers().size() > 2){
                        sessionService.setErrorMessage("startError", "Choose FFA for more than two players");
                    } else {
                        sessionService.setErrorMessage("startError", "You need to be two to start a 1v1");

                    }

                }
            }

            return "redirect:/lobby";
        } else {
            return "redirect:/main";
        }


    }

    private Lobby createLobby(String userId, String username) {
        Lobby lobby = new Lobby();

        lobby.setHostId(userId);
        lobby.setHostUsername(username);
        lobby.setGameMode("1v1");

        Player hostPlayer = new Player();
        hostPlayer.setUserId(lobby.getHostId());
        hostPlayer.setUsername(lobby.getHostUsername());
        hostPlayer.setHp(20);
        hostPlayer.setRating(MockDB.getRatingById(hostPlayer.getUserId()));

        lobby.getPlayers().add(hostPlayer);
        lobby.getTeam1().add(hostPlayer);

        return lobby;
    }


    private Game createFFAGame(Lobby lobby) {
        Game game = new Game(lobby.getHostId(), lobby.getId());
        game.setPlayers(lobby.getPlayers());
        game.setHostId(lobby.getHostId());
        game.setGameMode(lobby.getGameMode());
        game.setNumberOfPlayers(lobby.getPlayers().size());
        return game;
    }

    private Game createTHGGame(Lobby lobby) {
        Game game = new Game(lobby.getHostId(), lobby.getId());
        game.setStartingHp(30);
        game.setHostId(lobby.getHostId());
        game.setGameMode(lobby.getGameMode());
        game.setTeam1(lobby.getTeam1());
        game.setTeam2(lobby.getTeam2());
        game.setNumberOfPlayers(lobby.getPlayers().size());
        game.setPlayers(lobby.getPlayers());

        return game;
    }

}
