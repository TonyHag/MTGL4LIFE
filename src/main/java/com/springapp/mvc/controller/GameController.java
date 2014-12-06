package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import com.springapp.mvc.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

            if(game != null && game.isActive()) {


                // Sjekk at innlogget bruker er host
                if(SessionService.getLoggedInUserId(request) != game.getHostId()) {
                    // returner ikke tilgang
                    return "redirect:/mainMenu";
                }
                model.addAttribute("game", game);

            } else {

                System.out.println("GameController: Game inactive!");
                System.out.println("GameController: Game is null!");


                return "redirect:/mainMenu";
            }


            return "game";

        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/declareWinner", method = RequestMethod.POST)
    public String declareWinner(@RequestParam("gameId") int gameId, ModelMap model, @RequestParam("winner") String winnerName, HttpServletRequest request) {

        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn

            HttpSession session = request.getSession();
            SessionData sessionData = (SessionData) session.getAttribute("sessionData");
            int userId = sessionData.getUserId();

            Game game = MockDB.getGame(gameId);
            if(game.getHostId() == userId) { // Sjekker at man er host / evt senere om man er med i game
                game.setActive(false);

                ArrayList<Integer> winners = new ArrayList<Integer>();
                ArrayList<Player> players = game.getPlayers();

                winners.add(MockDB.getUserId(winnerName)); // må endres dersom flere vinnere
                game.setWinners(winners);


                ArrayList<Integer> losers = new ArrayList<Integer>();

                for(Player p : players) {
                    if(!p.getUsername().equals(winnerName)) {
                        losers.add(p.getUserId());
                        System.out.println("Setting loser: " + p.getUsername() );
                    }
                }
                game.setLosers(losers);


                GameConfirmationData confirmationData = new GameConfirmationData(game);

                MockDB.addGameConfirmationData(confirmationData);
                NotificationService.sendNotifications(game);

                return "redirect:/lobby/main";

            }



        }




        return "redirect:/login";


    }





}
