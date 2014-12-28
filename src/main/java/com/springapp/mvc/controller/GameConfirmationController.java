package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import com.springapp.mvc.model.game.Game;
import com.springapp.mvc.model.game.Player;
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
import java.util.ArrayList;

/**
 * Created by eirikskogland on 06.12.14.
 */
@Controller
public class GameConfirmationController {

    @RequestMapping(value = "/gameConfirmation/accept/{gameId}", method = RequestMethod.GET)
    public String accept(@PathVariable("gameId") String gameId, ModelMap model, HttpServletRequest request, @RequestParam("notificationId") String notificationId) {
        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);


        String userId = sessionService.getUserId();

        if(MockDB.getConfirmationData(gameId).getAccepted().contains(userId)) {

            MockDB.getConfirmationData(gameId).playerAccepted(userId);

            System.out.println("GameConfirmationController: Removing notifications for user " + MockDB.getUsername(userId));
            NotificationService notificationService = new NotificationService();
            notificationService.deleteNotification(userId, notificationId);

            System.out.println("GameConfirmationController: Player accepted");
            GameConfirmationData confirmationData = MockDB.getConfirmationData(gameId);

            // Hvis alle har acceptet
            if(confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() == 0) {
                System.out.println("GameConfirmationController: Updating players");

                Game game = MockDB.getGame(gameId);
                if(game.isDraw()) {
                    ArrayList<Player> players = game.getPlayers();

                    int ratingSum = 0;
                    String sameLeaderboardId = "";
                    for(Player p : players) { // sjekker for vinnerens leaderboard om alle andre tilhører samme

                        ratingSum += MockDB.getRatingById(p.getUserId());

                        ArrayList<String> leaderboardIds = MockDB.getLeaderboardIdsForUser(p.getUserId());
                        for(String leaderboardId : leaderboardIds) {
                            if(playersInSameLeaderboard(leaderboardId, players)) {
                                sameLeaderboardId = leaderboardId; // siste leaderboard med alle vil være gjeldene
                                // her bør det lages en liste av sammeLeaderBoardIds hvis alle er med i flere leaderboards
                            }
                        }
                    }

                    for(Player p : players) {
                        if(!sameLeaderboardId.equals("")) {
                            System.out.println("Adding draw to leaderboard");
                            MockDB.addUserDrawToLeaderboard(p.getUserId(), sameLeaderboardId, game.getGameMode());
                        }
                        double averageRating = (ratingSum-MockDB.getRatingById(p.getUserId()))/(players.size()-1);
                        MockDB.addUserDraw(p.getUserId(), (int) averageRating ,game.getGameMode());

                        MockDB.addUserDrawToLeaderboard(p.getUserId(), "worldLeaderboard", game.getGameMode());
                        System.out.println("adding user draw to user : " + p.getUsername());
                    }


                } else {
                    ArrayList<Player> players = game.getPlayers();
                    // oppdater spillere
                    ArrayList<String> winners = game.getWinners();
                    ArrayList<String> losers = MockDB.getGame(gameId).getLosers();

                    int winnerRatingSum = 0;
                    double winnerRatingAverage = 0;
                    for(String winner : winners) {
                        winnerRatingSum += MockDB.getRatingById(winner);
                    }
                    winnerRatingAverage = winnerRatingSum/winners.size();

                    int loserRatingSum = 0;
                    double loserRatingAverage = 0;
                    for(String loser : losers) {
                        loserRatingSum += MockDB.getRatingById(loser);
                    }
                    loserRatingAverage = loserRatingSum/losers.size();

                    // Sjekke om alle tilhører samme leaderboard
                    String sameLeaderboardId = "";
                    for(String id : winners) { // sjekker for vinnerens leaderboard om alle andre tilhører samme
                        ArrayList<String> leaderboardIds = MockDB.getLeaderboardIdsForUser(id);

                        for(String leaderboardId : leaderboardIds) {
                            if(playersInSameLeaderboard(leaderboardId, players)) {
                                sameLeaderboardId = leaderboardId; // siste leaderboard med alle vil være gjeldene
                                // her bør det lages en liste av sammeLeaderBoardIds hvis alle er med i flere leaderboards
                            }
                        }


                    }

                    for (String winnerId : winners) {
                        if(!sameLeaderboardId.equals("")) {
                            System.out.println("Adding win to leaderboard");
                            MockDB.addUserWinToLeaderboard(winnerId, sameLeaderboardId, game.getGameMode());
                        }

                        MockDB.addUserWin(winnerId, (int) loserRatingAverage, game.getGameMode());

                        MockDB.addUserWinToLeaderboard(winnerId, "worldLeaderboard", game.getGameMode());
                        System.out.println("adding user win to user : " + MockDB.getUsername(winnerId));
                    }

                    for(String loserId : losers) {
                        if(!sameLeaderboardId.equals("")) {
                            System.out.println("Adding win to leaderboard");
                            MockDB.addUserLossToLeaderboard(loserId, sameLeaderboardId, game.getGameMode());
                        }
                        MockDB.addUserLoss(loserId, (int)winnerRatingAverage, game.getGameMode());

                        MockDB.addUserLossToLeaderboard(loserId, "worldLeaderboard", game.getGameMode());
                        System.out.println("adding user loss to user : " + MockDB.getUsername(loserId));
                    }

                }


                // slett game og gameconfirmation


                // Hvis alle har svart på notifications
            } else if(confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() > 0) {
                System.out.println("GameConfirmationController: Enough players rejected, not updating players");

                // slett game og gameconfirmation
                MockDB.deleteGame(confirmationData.getGameId());
                MockDB.deleteGameConfirmation(confirmationData.getGameId());


            }

            return "redirect:/notifications";

        }

        return "redirect:/notifications";

    }

    @RequestMapping(value = "/gameConfirmation/reject/{gameId}", method = RequestMethod.GET)
    public String reject(@PathVariable("gameId") String gameId, ModelMap model, HttpServletRequest request,@RequestParam("notificationId") String notificationId) {
        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);


        String userId = sessionService.getUserId();

        if (MockDB.getConfirmationData(gameId).getAccepted().contains(userId)) {

            MockDB.getConfirmationData(gameId).playerRejected(userId);
            NotificationService notificationService = new NotificationService();
            notificationService.deleteNotification(userId, notificationId);

            System.out.println("GameConfirmationController: Player rejected");
            GameConfirmationData confirmationData = MockDB.getConfirmationData(gameId);


            // Hvis alle har acceptet
            if (confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() == 0) {
                System.out.println("GameConfirmationController: Updating players");

                Game game = MockDB.getGame(gameId);

                if(game.isDraw()) {
                    ArrayList<Player> players = game.getPlayers();

                    int ratingSum = 0;
                    String sameLeaderboardId = "";
                    for(Player p : players) { // sjekker for vinnerens leaderboard om alle andre tilhører samme

                        ratingSum += MockDB.getRatingById(p.getUserId());

                        ArrayList<String> leaderboardIds = MockDB.getLeaderboardIdsForUser(p.getUserId());

                        for(String leaderboardId : leaderboardIds) {
                            if(playersInSameLeaderboard(leaderboardId, players)) {
                                sameLeaderboardId = leaderboardId; // siste leaderboard med alle vil være gjeldene
                                // her bør det lages en liste av sammeLeaderBoardIds hvis alle er med i flere leaderboards
                            }
                        }
                    }

                    for(Player p : players) {
                        if(!sameLeaderboardId.equals("")) {
                            System.out.println("Adding draw to leaderboard");
                            MockDB.addUserDrawToLeaderboard(p.getUserId(), sameLeaderboardId, game.getGameMode());
                        }

                        double averageRating = (ratingSum-MockDB.getRatingById(p.getUserId()))/(players.size()-1);
                        MockDB.addUserDraw(p.getUserId(), (int) averageRating, game.getGameMode());

                        MockDB.addUserDrawToLeaderboard(p.getUserId(), "worldLeaderboard", game.getGameMode());
                        System.out.println("adding user draw to user : " + p.getUsername());
                    }


                } else {
                    ArrayList<Player> players = game.getPlayers();
                    // oppdater spillere
                    ArrayList<String> winners = game.getWinners();
                    ArrayList<String> losers = MockDB.getGame(gameId).getLosers();


                    int winnerRatingSum = 0;
                    double winnerRatingAverage = 0;
                    for(String winner : winners) {
                        winnerRatingSum += MockDB.getRatingById(winner);
                    }
                    winnerRatingAverage = winnerRatingSum/winners.size();

                    int loserRatingSum = 0;
                    double loserRatingAverage = 0;
                    for(String loser : losers) {
                        loserRatingSum += MockDB.getRatingById(loser);
                    }
                    loserRatingAverage = loserRatingSum/losers.size();
                    String sameLeaderboardId = "";

                    for(String id : winners) {
                        ArrayList<String> leaderboardIds = MockDB.getLeaderboardIdsForUser(id);

                        for(String leaderboardId : leaderboardIds) {
                            if(playersInSameLeaderboard(leaderboardId, players)) {
                                sameLeaderboardId = leaderboardId;
                            }
                        }


                    }

                    for (String winnerId : winners) {
                        if(!sameLeaderboardId.equals("")) {
                            MockDB.addUserWinToLeaderboard(winnerId, sameLeaderboardId, game.getGameMode());
                        }
                        MockDB.addUserWin(winnerId, (int) loserRatingAverage, game.getGameMode());

                        MockDB.addUserWinToLeaderboard(winnerId, "worldLeaderboard", game.getGameMode());
                    }

                    for(String loserId : losers) {
                        if(!sameLeaderboardId.equals("")) {
                            MockDB.addUserLossToLeaderboard(loserId, sameLeaderboardId, game.getGameMode());
                        }
                        MockDB.addUserLoss(loserId, (int) winnerRatingAverage, game.getGameMode());

                        MockDB.addUserLossToLeaderboard(loserId, "worldLeaderboard", game.getGameMode());
                    }
                    // slett game og gameconfirmation

                }



                // Hvis alle har svart på notifications
            } else if (confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() > 0) {

                // slett game og gameconfirmation
                MockDB.deleteGame(confirmationData.getGameId());
                MockDB.deleteGameConfirmation(confirmationData.getGameId());

            }
            return "redirect:/notifications";
        }

        return "redirect:/notifications";
    }


    private boolean playersInSameLeaderboard(String leaderboardId, ArrayList<Player> players) {

        for(Player p : players) {
            if(!MockDB.isPlayerInLeaderboard(p.getUserId(), leaderboardId)) {
               return false;
            }
        }
        return true;
    }





}
