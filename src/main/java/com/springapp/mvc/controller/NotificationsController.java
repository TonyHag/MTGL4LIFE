package com.springapp.mvc.controller;

import com.springapp.mvc.model.notifications.GameConfirmation;
import com.springapp.mvc.model.notifications.LeaderboardInvitation;
import com.springapp.mvc.model.notifications.Notification;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.NotificationService;
import com.springapp.mvc.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by eirikskogland on 04.12.14.
 */
@Controller
public class NotificationsController {



    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public String getNotificationsPage(ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        String username = sessionService.getUsername();
        model.addAttribute("user", username);


        int numberOfNotifications = sessionService.getNumberOfNotifications();
        model.addAttribute("numberOfNotifications", numberOfNotifications);
        // -- autentisering ferdig


        NotificationService notificationService = new NotificationService();
        // Henter notifications
        ArrayList<LeaderboardInvitation> invitations = MockDB.getLeaderboardInvitations(sessionService.getUserId());
        model.addAttribute("leaderboardInvitations", invitations);

        ArrayList<GameConfirmation> gameConfirmations = MockDB.getGameConfirmations(sessionService.getUserId());
        model.addAttribute("gameConfirmations", gameConfirmations);

        return "notifications";

    }


}
