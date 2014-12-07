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


        /*
        !!!!!! Lag ny lobby, redirect til lobby/{lobbyId}
         */

        if(SessionService.getLoggedInUser(request) != null) {
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            if(SessionService.getLobby(request) == null) { // lobby ikke finnes


                Lobby lobby = new Lobby();

                lobby.setHostId(SessionService.getLoggedInUserId(request));
                lobby.setHostUsername(SessionService.getLoggedInUser(request));

                MockDB.addLobby(lobby);

                model.addAttribute(lobby);
                SessionService.setLobby(lobby, request);


                return "redirect:lobby/" + lobby.getId();
            } else {
                return "redirect:lobby/" + SessionService.getLobby(request).getId();

            }
        }

        return "redirect:/login";

    }

    @RequestMapping(value = "/lobby/{lobbyId}")
    public String getExistingLobby(ModelMap model, HttpServletRequest request, @PathVariable("lobbyId") int lobbyId) {

        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            Lobby lobby = MockDB.getLobby(lobbyId);

            if(lobby != null && SessionService.getLoggedInUserId(request) == lobby.getHostId()) {  // lobby finnes og bruker er eier

                model.addAttribute(lobby);
                SessionService.setLobby(lobby, request);
                return "lobby";

            }

            return "redirect:/main";

        }

        return "redirect:/login";

    }

    @RequestMapping(value = "/lobby/invite", method = RequestMethod.POST)
    public String invitePlayer(ModelMap model, HttpServletRequest request, @RequestParam(value="invitePlayer") String invitePlayer) {

        if(SessionService.getLoggedInUser(request) != null &&
                SessionService.getLobby(request) != null) { // hvis logget inn og har opprettet lobby
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            Lobby lobby = SessionService.getLobby(request);

            // Sjekk at man ikke inviterer seg selv
            if(lobby.getHostUsername().equals(invitePlayer)) {
                lobby.setInviteError("Invite a friend!");
                SessionService.setLobby(lobby, request);
                MockDB.updateLobby(lobby);
                return "redirect:" + lobby.getId();
            }

            // Sjekk at man ikke inviterer samme flere ganger
            for(String name : lobby.getInvitedPlayerUsernames()) {
                if(name.equals(invitePlayer)) {
                    lobby.setInviteError("Player already invited");
                    SessionService.setLobby(lobby, request);
                    MockDB.updateLobby(lobby);

                    return "redirect:" + lobby.getId();
                }
            }

            // Sjekk at spiller eksisterer
            if(MockDB.isUser(invitePlayer)) {

                lobby.setInviteError("");
                lobby.getInvitedPlayerUsernames().add(invitePlayer);
                SessionService.setLobby(lobby, request);
                MockDB.updateLobby(lobby);

                return "redirect:" + lobby.getId();

            } else {

                lobby.setInviteError("Player not found");
                SessionService.setLobby(lobby, request);
                MockDB.updateLobby(lobby);

                return "redirect:" + lobby.getId();

            }
        }

        return "redirect:/login";
    }

    @RequestMapping(value ="/lobby/startGame", method = RequestMethod.GET)
    public String startGame(ModelMap model, HttpServletRequest request) {

        if(SessionService.getLoggedInUser(request) != null &&
                SessionService.getLobby(request) != null) { // hvis logget inn
            String user = SessionService.getLoggedInUser(request);
            model.addAttribute("user", user);

            Lobby lobby = MockDB.getLobby(SessionService.getLobby(request).getId());
            lobby.setPlayers(new ArrayList<Player>()); // hvis lobby har spillere fra før//  hindrer dobbelt opp med spillere

            // Legg til host som player
            Player hostPlayer = new Player();
            hostPlayer.setUserId(lobby.getHostId());
            hostPlayer.setUsername(lobby.getHostUsername());
            hostPlayer.setHp(20);
            lobby.getPlayers().add(hostPlayer);

            // legg til resten av inviterte spillere til players
            for(String username : lobby.getInvitedPlayerUsernames()) {
                Player p = new Player();
                p.setUserId(MockDB.getUserId(username));
                p.setUsername(username);
                p.setHp(20);
                lobby.getPlayers().add(p);
            }


            // Opprett nytt game

            Game game = new Game(hostPlayer.getUserId(), lobby.getId());
            game.setPlayers(lobby.getPlayers());
            game.setHostId(lobby.getHostId());
            MockDB.addGame(game);

            return ("redirect:/game/" + game.getId());

        }

        return "redirect:/login";

    }

}
