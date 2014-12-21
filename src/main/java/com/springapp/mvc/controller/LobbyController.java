package com.springapp.mvc.controller;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.Lobby;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.Team;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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
        model.addAttribute("user", username);

        // Hvis bruker har et aktivt game, redirect til game
        if(sessionService.getActiveGame() != null) {
            return "redirect:/game/" + sessionService.getActiveGame();
        }

        if(sessionService.getLobby() == null) { // lobby ikke finnes


            Lobby lobby = new Lobby();

            lobby.setHostId(sessionService.getUserId());
            lobby.setHostUsername(username);


            Player hostPlayer = new Player();
            hostPlayer.setUserId(lobby.getHostId());
            hostPlayer.setUsername(lobby.getHostUsername());
            hostPlayer.setHp(20);
            lobby.getPlayers().add(hostPlayer);
            lobby.getTeam1().add(hostPlayer);


            model.addAttribute(lobby);
            sessionService.setLobby(lobby);

            MockDB.addLobby(lobby);




            return "redirect:lobby/" + lobby.getId();
        } else {
            return "redirect:lobby/" + sessionService.getLobby().getId();

        }

    }

    @RequestMapping(value = "/lobby/{lobbyId}")
    public String getExistingLobby(ModelMap model, HttpServletRequest request, @PathVariable("lobbyId") String lobbyId) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);

        Lobby lobby = MockDB.getLobby(lobbyId);

        if(lobby != null) {



            if(sessionService.getUserId().equals(lobby.getHostId())) {  // lobby finnes og bruker er eier


                model.addAttribute(lobby);
                sessionService.setLobby(lobby);
                model.addAttribute("inviteError",sessionService.getErrorMessage("inviteError"));
                model.addAttribute("startError",sessionService.getErrorMessage("startError"));
                System.out.println("returning lobby " + sessionService.getErrorMessage("inviteError"));
                return "lobby";

            }

        } else { // returner til ny lobby om lobby ikke finnes
            return "redirect:/lobby";
        }


        return "redirect:/main";



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

        String lobbyId = sessionService.getLobby().getId();
        Lobby lobby = MockDB.getLobby(lobbyId);

        if(lobby != null && sessionService.getUserId().equals(lobby.getHostId())) {  // lobby finnes og bruker er eier

            System.out.println("Trying to change team");
            lobby.assignTeam(userId, team);
            sessionService.setLobby(lobby);
            MockDB.updateLobby(lobby);
            return "redirect:" + lobbyId;

        }

        return "redirect:" + lobbyId;

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

        Lobby lobby = MockDB.getLobby(lobbyId);

        if(lobby != null) {

            if(sessionService.getUserId().equals(lobby.getHostId())) {  // lobby finnes og bruker er eier


                // set gamemode
                lobby.setGameMode(gameMode);



                // update db
                MockDB.updateLobby(lobby);

                // redirect
                return "redirect:" + lobbyId;


            }

        } else { // returner til ny lobby om lobby ikke finnes
            return "redirect:/lobby";
        }


        return "redirect:/main";

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
        Lobby lobby = MockDB.getLobby(lobbyId);

        if(lobby != null && sessionService.getUserId().equals(lobby.getHostId())) {  // lobby finnes og bruker er eier

            // Fjerner invitert spiller hvis spiller er med
            for(Player p : lobby.getPlayers()) {
                if(p.getUsername().equals(removePlayer)) {
                    lobby.getPlayers().remove(p);
                     break;
                }
            }
            //if(lobby.getPlayers().contains(removePlayer)) {
             //   lobby.getPlayers().remove(removePlayer);
            //}

            //model.addAttribute(lobby);
            sessionService.setLobby(lobby);
            MockDB.updateLobby(lobby);
            return "redirect:" + lobbyId;

        }

        return "redirect:" + lobbyId;

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
        //lobby.setStartError("");

        // Sjekk at man ikke inviterer seg selv
        if(username.equals(invitePlayer)) {
            sessionService.setErrorMessage("inviteError", "Invite a friend!");
            //sessionService.setLobby(lobby);
            //sessionService.setLobby(lobby);
            //MockDB.updateLobby(lobby);
            return "redirect:" + lobby.getId();
        }

        // Sjekk at man ikke inviterer samme flere ganger
        for(Player player : lobby.getPlayers()) {
            if(player.getUsername().equals(invitePlayer)) {
                sessionService.setErrorMessage("inviteError", "Player already invited");
                //sessionService.setLobby(lobby);
                //MockDB.updateLobby(lobby);
                return "redirect:" + lobby.getId();
            }
        }

        // Sjekk at spiller eksisterer
        if(MockDB.isUser(invitePlayer)) {
            sessionService.setErrorMessage("inviteError", null);
            Player newPlayer = new Player();
            newPlayer.setUsername(invitePlayer);
            newPlayer.setUserId(MockDB.getUserId(invitePlayer));
            newPlayer.setHp(20);
            lobby.getPlayers().add(newPlayer);
            lobby.getTeam1().add(newPlayer);
            sessionService.setLobby(lobby);
            MockDB.updateLobby(lobby);

            return "redirect:" + lobby.getId();

        } else {

            sessionService.setErrorMessage("inviteError", "Player not found!");
            //sessionService.setLobby(lobby);
            //MockDB.updateLobby(lobby);

            return "redirect:" + lobby.getId();

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

            Lobby lobby = MockDB.getLobby(sessionService.getLobby().getId());

            if(lobby.getGameMode().equals("ffa")) {
                if(lobby.getPlayers().size() > 0) {  // sjekke at man ikke starter game med 1 spiller
                    //lobby.setPlayers(new ArrayList<Player>()); // hvis lobby har spillere fra før//  hindrer dobbelt opp med spillere
                    lobby.setStartError("");

                    // Legg til host som player


                    // legg til resten av inviterte spillere til players
                    //for(Player p : lobby.getPlayers()) {
                        //Player p = new Player();
                        //p.setUserId(MockDB.getUserId(p.getUsername()));
                        //p.setUsername(invitedUsername);
                        //p.setHp(20);
                        //lobby.getPlayers().add(p);
                    //}


                    // Opprett nytt game

                    Game game = new Game(lobby.getHostId(), lobby.getId());
                    game.setPlayers(lobby.getPlayers());
                    game.setHostId(lobby.getHostId());
                    game.setGameMode(lobby.getGameMode());
                    game.setNumberOfPlayers(lobby.getPlayers().size());
                    MockDB.addGame(game);
                    sessionService.setActiveGame(game.getId());

                    return ("redirect:/game/" + game.getId());
                } else {
                    sessionService.setErrorMessage("startError", "You cant start an empty game");
                }


            } else if(lobby.getGameMode().equals("thg")) {
                if(lobby.isTeamsReady(4)) {

                    sessionService.setErrorMessage("startError", "");
                    // Opprett nytt game

                    Game game = new Game(lobby.getHostId(), lobby.getId());
                    game.setStartingHp(30);
                    game.setHostId(lobby.getHostId());
                    game.setGameMode(lobby.getGameMode());
                    game.setTeam1(lobby.getTeam1());
                    game.setTeam2(lobby.getTeam2());
                    game.setNumberOfPlayers(lobby.getPlayers().size());
                    game.setPlayers(lobby.getPlayers());

                    MockDB.addGame(game);

                    sessionService.setActiveGame(game.getId());


                    return ("redirect:/game/" + game.getId());




                } else {
                    sessionService.setErrorMessage("startError", "Teams not ready");
                }

            }

            //MockDB.updateLobby(lobby);
            return "redirect:" + lobby.getId();

        } else {
            return "redirect:/main";
        }


    }

}
