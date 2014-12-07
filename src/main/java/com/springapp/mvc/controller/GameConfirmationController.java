package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import com.springapp.mvc.service.GameConfirmationService;
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
        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            HttpSession session = request.getSession();
            SessionData sessionData = (SessionData) session.getAttribute("sessionData");
            int userId = sessionData.getUserId();

            System.out.println("GameConfirmationController: gameConfirmation is null:  " + (MockDB.getConfirmationData(gameId) == null) );

            if(MockDB.getConfirmationData(gameId).getAccepted().contains(userId)) {

                MockDB.getConfirmationData(gameId).playerAccepted(userId);
                MockDB.deleteNotification(notificationId);

                System.out.println("GameConfirmationController: Player accepted");
                GameConfirmationData confirmationData = MockDB.getConfirmationData(gameId);

                // Hvis alle har acceptet
                if(confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() == 0) {
                    System.out.println("GameConfirmationController: Updating players");

                    Game game = MockDB.getGame(gameId);

                    // oppdater spillere
                    ArrayList<Integer> winners = game.getWinners();
                    for(Integer winnerId : winners) {
                        MockDB.addUserWin(winnerId);
                    }

                    ArrayList<Integer> losers = MockDB.getGame(gameId).getLosers();
                    for(Integer loserId : losers) {
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

            // returnere "ikke din notification"?

        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/gameConfirmation/reject/{gameId}", method = RequestMethod.GET)
    public String reject(@PathVariable("gameId") int gameId, ModelMap model, HttpServletRequest request,@RequestParam("notificationId") int notificationId) {
        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            HttpSession session = request.getSession();
            SessionData sessionData = (SessionData) session.getAttribute("sessionData");
            int userId = sessionData.getUserId();

            if (MockDB.getConfirmationData(gameId).getAccepted().contains(userId)) {

                MockDB.getConfirmationData(gameId).playerRejected(userId);
                MockDB.deleteNotification(notificationId);

                System.out.println("GameConfirmationController: Player rejected");
                GameConfirmationData confirmationData = MockDB.getConfirmationData(gameId);


                // Hvis alle har acceptet
                if (confirmationData.getAccepted().size() == 0 && confirmationData.getPlayersRejected() == 0) {
                    System.out.println("GameConfirmationController: Updating players");

                    Game game = MockDB.getGame(gameId);

                    // oppdater spillere
                    ArrayList<Integer> winners = game.getWinners();
                    for (Integer winnerId : winners) {
                        MockDB.addUserWin(winnerId);
                    }

                    ArrayList<Integer> losers = MockDB.getGame(gameId).getLosers();
                    for (Integer loserId : losers) {
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
        }

        return "redirect:/login";
    }





}
