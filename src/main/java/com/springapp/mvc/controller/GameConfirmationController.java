package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
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
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;

/**
 * Created by eirikskogland on 06.12.14.
 */
@Controller
public class GameConfirmationController {

    @RequestMapping(value = "/gameConfirmation/accept/{gameId}", method = RequestMethod.GET)
    public String accept(@PathVariable("gameId") int gameId, ModelMap model, HttpServletRequest request, @RequestParam("notificationId") int notificationId) {
        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);


        int userId = sessionService.getUserId();

        if(MockDB.getConfirmationData(gameId).getAccepted().contains(userId)) {

            MockDB.getConfirmationData(gameId).playerAccepted(userId);
            MockDB.deleteNotification(notificationId);

            System.out.println("GameConfirmationController: Player accepted");
            GameConfirmationData confirmationData = MockDB.getConfirmationData(gameId);

            // Hvis alle har acceptet
            if(confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() == 0) {
                System.out.println("GameConfirmationController: Updating players");

                Game game = MockDB.getGame(gameId);

                ArrayList<Player> players = game.getPlayers();
                // oppdater spillere
                ArrayList<Integer> winners = game.getWinners();

                // Sjekke om alle tilhører samme leaderboard
                int sameLeaderboardId = -1;
                for(Integer i : winners) { // sjekker for vinnerens leaderboard om alle andre tilhører samme
                    ArrayList<Integer> leaderboardIds = MockDB.getLeaderboardIdsForUser(i);

                    for(Integer leaderboardId : leaderboardIds) {
                        if(playersInSameLeaderboard(leaderboardId, players)) {
                            sameLeaderboardId = leaderboardId; // siste leaderboard med alle vil være gjeldene
                            // her bør det lages en liste av sammeLeaderBoardIds hvis alle er med i flere leaderboards
                        }
                    }


                }

                for (Integer winnerId : winners) {
                    if(sameLeaderboardId != -1) {
                        System.out.println("Adding win to leaderboard");
                        MockDB.addUserWinToLeaderboard(winnerId, sameLeaderboardId);
                    }
                    MockDB.addUserWin(winnerId);
                }

                ArrayList<Integer> losers = MockDB.getGame(gameId).getLosers();
                for(Integer loserId : losers) {
                    if(sameLeaderboardId != -1) {
                        System.out.println("Adding win to leaderboard");
                        MockDB.addUserLossToLeaderboard(loserId, sameLeaderboardId);
                    }
                    MockDB.addUserLoss(loserId);
                }

                // slett game og gameconfirmation


                // Hvis alle har svart på notification
            } else if(confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() > 0) {
                System.out.println("GameConfirmationController: Enough players rejected, not updating players");

                // slett game og gameconfirmation

            }

            return "redirect:/notifications";

        }

        return "redirect:/notifications";

    }

    @RequestMapping(value = "/gameConfirmation/reject/{gameId}", method = RequestMethod.GET)
    public String reject(@PathVariable("gameId") int gameId, ModelMap model, HttpServletRequest request,@RequestParam("notificationId") int notificationId) {
        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);


        int userId = sessionService.getUserId();

        if (MockDB.getConfirmationData(gameId).getAccepted().contains(userId)) {

            MockDB.getConfirmationData(gameId).playerRejected(userId);
            MockDB.deleteNotification(notificationId);

            System.out.println("GameConfirmationController: Player rejected");
            GameConfirmationData confirmationData = MockDB.getConfirmationData(gameId);


            // Hvis alle har acceptet
            if (confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() == 0) {
                System.out.println("GameConfirmationController: Updating players");

                Game game = MockDB.getGame(gameId);
                ArrayList<Player> players = game.getPlayers();
                // oppdater spillere
                ArrayList<Integer> winners = game.getWinners();

                int sameLeaderboardId = -1;
                for(Integer i : winners) {
                    ArrayList<Integer> leaderboardIds = MockDB.getLeaderboardIdsForUser(i);

                    for(Integer leaderboardId : leaderboardIds) {
                        if(playersInSameLeaderboard(leaderboardId, players)) {
                            sameLeaderboardId = leaderboardId;
                        }
                    }


                }

                for (Integer winnerId : winners) {
                    if(sameLeaderboardId != -1) {
                        System.out.println("Adding win to leaderboard");
                        MockDB.addUserWinToLeaderboard(winnerId, sameLeaderboardId);
                    }
                    MockDB.addUserWin(winnerId);
                }

                ArrayList<Integer> losers = MockDB.getGame(gameId).getLosers();
                for(Integer loserId : losers) {
                    if(sameLeaderboardId != -1) {
                        System.out.println("Adding win to leaderboard");
                        MockDB.addUserLossToLeaderboard(loserId, sameLeaderboardId);
                    }
                    MockDB.addUserLoss(loserId);
                }
                // slett game og gameconfirmation


                // Hvis alle har svart på notification
            } else if (confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() > 0) {
                System.out.println("GameConfirmationController: Enough players rejected, not updating players");

                // slett game og gameconfirmation

            }
            return "redirect:/notifications";
        }

        return "redirect:/notifications";
    }


    private boolean playersInSameLeaderboard(int leaderboardId, ArrayList<Player> players) {

        for(Player p : players) {
            if(!MockDB.isPlayerInLeaderboard(p.getUserId(), leaderboardId)) {
               return false;
            }
        }
        return true;
    }





}
