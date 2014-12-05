package com.springapp.mvc.controller;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.GameData;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.NotificationService;
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
@RequestMapping("/game")
public class GameController {

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public String getGame(@PathVariable("gameId") int gameId, ModelMap model, HttpServletRequest request) {

        if(SessionService.getLoggedInUser(request) != null) {

            // hent game info fra db
            Game game = MockDB.getGame(gameId);

            if(game != null) {


                // Sjekk at innlogget bruker er host
                if(SessionService.getLoggedInUserId(request) != game.getHostId()) {
                    // returner ikke tilgang
                    return "redirect:/mainMenu";
                }
                model.addAttribute("game", game);

            } else {
                System.out.println("GameController: Game is null!");
                return "redirect:/mainMenu";
            }

            // gjør klar model

            return "game";

        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/declareWinner", method = RequestMethod.POST)
    public String declareWinner(ModelMap model, @RequestParam("winner") String winnerName, HttpServletRequest request) {


        System.out.println("Winner is: " + winnerName);
        //System.out.println("Other player is: " + players.get(1));

        GameData gameData = SessionService.getGameData(request);
        int winnerId = MockDB.getUserId(winnerName);
        NotificationService.sendNotifications(gameData.getPlayers(), gameData.getHostId(), gameData.getGameId(), winnerName, winnerId);

        // invalider game

        // redirect til lobby

        return "redirect:" + gameData.getGameId();

    }





}
