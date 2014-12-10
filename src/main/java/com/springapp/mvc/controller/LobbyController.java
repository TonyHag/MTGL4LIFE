package com.springapp.mvc.controller;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.Lobby;
import com.springapp.mvc.model.Player;
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

        //TODO: Hvis bruker har et aktivt game, redirect til game

        if(sessionService.getLobby() == null) { // lobby ikke finnes


            Lobby lobby = new Lobby();

            lobby.setHostId(sessionService.getUserId());
            lobby.setHostUsername(sessionService.getUsername());

            MockDB.addLobby(lobby);

            model.addAttribute(lobby);
            sessionService.setLobby(lobby);


            return "redirect:lobby/" + lobby.getId();
        } else {
            return "redirect:lobby/" + sessionService.getLobby().getId();

        }

    }

    @RequestMapping(value = "/lobby/{lobbyId}")
    public String getExistingLobby(ModelMap model, HttpServletRequest request, @PathVariable("lobbyId") int lobbyId) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);

        Lobby lobby = MockDB.getLobby(lobbyId);
        lobby.setStartError("");

        if(sessionService.getUserId() == lobby.getHostId()) {  // lobby finnes og bruker er eier

            model.addAttribute(lobby);
            sessionService.setLobby(lobby);

            return "lobby";

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

        int lobbyId = sessionService.getLobby().getId();
        Lobby lobby = MockDB.getLobby(lobbyId);

        if(lobby != null && sessionService.getUserId() == lobby.getHostId()) {  // lobby finnes og bruker er eier

            // Fjerner invitert spiller hvis spiller er med
            if(lobby.getInvitedPlayerUsernames().contains(removePlayer)) {
                lobby.getInvitedPlayerUsernames().remove(removePlayer);
                System.out.println("Player removed: " + removePlayer);
            }

            model.addAttribute(lobby);
            sessionService.setLobby(lobby);
            MockDB.updateLobby(lobby);
            return "redirect:" + lobbyId;

        }

        return "redirect:/main";

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
        lobby.setStartError("");

        // Sjekk at man ikke inviterer seg selv
        if(lobby.getHostUsername().equals(invitePlayer)) {
            lobby.setInviteError("Invite a friend!");

            sessionService.setLobby(lobby);
            MockDB.updateLobby(lobby);
            return "redirect:" + lobby.getId();
        }

        // Sjekk at man ikke inviterer samme flere ganger
        for(String name : lobby.getInvitedPlayerUsernames()) {
            if(name.equals(invitePlayer)) {
                lobby.setInviteError("Player already invited");
                sessionService.setLobby(lobby);
                MockDB.updateLobby(lobby);

                return "redirect:" + lobby.getId();
            }
        }

        // Sjekk at spiller eksisterer
        if(MockDB.isUser(invitePlayer)) {

            lobby.setInviteError("");
            lobby.getInvitedPlayerUsernames().add(invitePlayer);
            sessionService.setLobby(lobby);
            MockDB.updateLobby(lobby);

            return "redirect:" + lobby.getId();

        } else {

            lobby.setInviteError("Player not found");
            sessionService.setLobby(lobby);
            MockDB.updateLobby(lobby);

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
            if(lobby.getInvitedPlayerUsernames().size() > 0) {  // sjekke at man ikke starter game med 1 spiller
                lobby.setPlayers(new ArrayList<Player>()); // hvis lobby har spillere fra før//  hindrer dobbelt opp med spillere
                lobby.setStartError("");

                // Legg til host som player
                Player hostPlayer = new Player();
                hostPlayer.setUserId(lobby.getHostId());
                hostPlayer.setUsername(lobby.getHostUsername());
                hostPlayer.setHp(20);
                lobby.getPlayers().add(hostPlayer);

                // legg til resten av inviterte spillere til players
                for(String invitedUsername : lobby.getInvitedPlayerUsernames()) {
                    Player p = new Player();
                    p.setUserId(MockDB.getUserId(invitedUsername));
                    p.setUsername(invitedUsername);
                    p.setHp(20);
                    lobby.getPlayers().add(p);
                }


                // Opprett nytt game

                Game game = new Game(hostPlayer.getUserId(), lobby.getId());
                game.setPlayers(lobby.getPlayers());
                game.setHostId(lobby.getHostId());
                game.setNumberOfPlayers(lobby.getPlayers().size());
                MockDB.addGame(game);

                return ("redirect:/game/" + game.getId());
            }

            lobby.setStartError("You cant start an empty game");
            MockDB.updateLobby(lobby);
            return "redirect:" + lobby.getId();

        } else {
            return "redirect:/main";
        }


    }

}
