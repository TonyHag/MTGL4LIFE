package com.springapp.mvc.controller;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.GameData;
import com.springapp.mvc.model.LobbyData;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.IdService;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eirikskogland on 03.12.14.
 */
@Controller
@RequestMapping("/lobby")
public class LobbyController {


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getLobbyPage(ModelMap model, HttpServletRequest request) {

        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn

            LobbyData lobbyData;

            // Hvis det er første gang man besøker, vil lobbydata være null, og denne må opprettes og settes i session
            if(SessionService.getLobbyData(request) == null) {
                lobbyData = new LobbyData();
                lobbyData.setHost(SessionService.getLoggedInUser(request));
                lobbyData.setHostId(MockDB.getUserId(lobbyData.getHost()));
                SessionService.setLobbyData(lobbyData, request);

            } else {
                lobbyData = SessionService.getLobbyData(request);
            }

            // attributter legges til modellen
            model.addAttribute("host", lobbyData.getHost());
            model.addAttribute("inviteError", lobbyData.getInviteError());
            model.addAttribute("invitedPlayers", lobbyData.getInvitedPlayerUsernames());


            return "lobby";
        }

        return "redirect:/login";

    }

    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    public String invitePlayer(ModelMap model, HttpServletRequest request, @RequestParam(value="invitePlayer") String invitePlayer) {

        if(SessionService.getLoggedInUser(request) != null &&
                SessionService.getLobbyData(request) != null) { // hvis logget inn og har opprettet lobby

            LobbyData lobbyData = SessionService.getLobbyData(request);

            // Sjekk at man ikke inviterer seg selv
            if(lobbyData.getHost().equals(invitePlayer)) {
                lobbyData.setInviteError("Invite a friend!");
                SessionService.setLobbyData(lobbyData, request);
                return "redirect:main";
            }

            // Sjekk at man ikke inviterer samme flere ganger
            for(String name : lobbyData.getInvitedPlayerUsernames()) {
                if(name.equals(invitePlayer)) {
                    lobbyData.setInviteError("Player already invited");
                    return "redirect:main";
                }
            }

            // Sjekk at spiller eksisterer
            if(MockDB.isUser(invitePlayer)) {

                lobbyData.setInviteError("");
                lobbyData.getInvitedPlayerUsernames().add(invitePlayer);
                return "redirect:main";

            } else {

                lobbyData.setInviteError("Player not found");
                return "redirect:main";

            }
        }

        return "redirect:/login";
    }

    @RequestMapping(value ="/startGame", method = RequestMethod.GET)
    public String startGame(ModelMap model, HttpServletRequest request) {

        if(SessionService.getLoggedInUser(request) != null &&
                SessionService.getLobbyData(request) != null) { // hvis logget inn

            LobbyData lobbyData = SessionService.getLobbyData(request);
            // Legg til host som player
            Player hostPlayer = new Player();
            hostPlayer.setUserId(lobbyData.getHostId());
            hostPlayer.setUsername(lobbyData.getHost());
            hostPlayer.setHp(20);
            lobbyData.getPlayers().add(hostPlayer);

            // legg til resten av inviterte spillere til players
            for(String username : lobbyData.getInvitedPlayerUsernames()) {
                Player p = new Player();
                p.setUserId(MockDB.getUserId(username));
                p.setUsername(username);
                p.setHp(20);
                lobbyData.getPlayers().add(p);
            }


            // Opprett nytt game
            Game game = new Game();
            int gameId = IdService.getGameId();
            game.setPlayers(lobbyData.getPlayers());
            game.setId(gameId);
            game.setHostId(lobbyData.getHostId());
            MockDB.addGame(game);

            GameData gameData = new GameData();
            gameData.setHost(lobbyData.getHost());
            gameData.setHostId(lobbyData.getHostId());
            gameData.setGameId(gameId);
            gameData.setPlayers(lobbyData.getPlayers());

            SessionService.setGameData(gameData, request);
            SessionService.deleteLobbyData(request);

            return ("redirect:/game/" + gameId);

        }

        return "redirect:/login";

    }

}
