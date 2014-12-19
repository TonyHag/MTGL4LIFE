package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import com.springapp.mvc.model.Leaderboard;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.NotificationService;
import com.springapp.mvc.service.SessionService;
import com.springapp.mvc.service.ValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by eirikskogland on 07.12.14.
 */
@Controller
public class LeaderboardController {


    @RequestMapping(value ="/leaderboard", method = RequestMethod.GET)
    public String getLeaderboardFormPage(ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

            ArrayList<String> leaderboardIds = MockDB.getLeaderboardIdsForUser(sessionService.getUserId());
            if(leaderboardIds != null) {
                ArrayList<LeaderboardInfo> leaderboardInfos = new ArrayList<LeaderboardInfo>();

                for(String id : leaderboardIds) {
                    // Hvis bruker er eier av leaderboard, vil det være en manage-knapp ved siden av navnet
                    LeaderboardInfo info = new LeaderboardInfo(id, MockDB.getLeaderboardNameById(id));
                    if(MockDB.getOwnerId(id).equals(sessionService.getUserId())) {
                        info.setOwner(true);
                    }
                    leaderboardInfos.add(info);
                }

                model.addAttribute("leaderboardInfos", leaderboardInfos);


            }

            return "leaderboards";



    }

    @RequestMapping(value ="/leaderboard/{leaderboardId}", method = RequestMethod.GET)
    public String getLeaderboardPage(ModelMap model, HttpServletRequest request, @PathVariable("leaderboardId") String leaderboardId) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

        Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
        model.addAttribute("leaderboard", leaderboard);

        return "leaderboard";
    }


    @RequestMapping(value = "/leaderboard/create", method = RequestMethod.GET)
    public String getLeaderboardCreateForm(ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

        return "createLeaderboard";
    }

    @RequestMapping(value = "/leaderboard/create", method = RequestMethod.POST)
    public String submitLeaderboardForm(ModelMap model, HttpServletRequest request, @RequestParam(value= "name") String name, @RequestParam(value = "description") String description) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);


        boolean valid = true;

        ValidationService validationService = new ValidationService();
        // validate name
        if(!validationService.validateUsername(name)) {
            valid = false;
        }

        // validate description
        if(!validationService.validateUsername(description)){
             valid = false;
        }


        if(valid) {

            String userId = sessionService.getUserId();

            Leaderboard leaderboard = new Leaderboard(userId);
            leaderboard.setName(name);
            leaderboard.setDescription(description);
            leaderboard.getPlayerStats().add(new UserStatistics(userId));
            MockDB.addLeaderboard(leaderboard);
            MockDB.addLeaderboardIdToUser(userId, leaderboard.getId());

            String leaderBoardId = leaderboard.getId();
            return "redirect:/leaderboard/manage/" + leaderBoardId;

        }

        return "redirect:/leaderboard";

    }

    @RequestMapping(value = "/leaderboard/manage/{leaderboardId}")
    public String getManageLeaderboardPage(ModelMap model, HttpServletRequest request, @PathVariable("leaderboardId") String leaderboardId) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);


        // hvis eier
        if(sessionService.getUserId().equals(MockDB.getOwnerId(leaderboardId)))  {
            Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
            model.addAttribute("leaderboardRemoveError", sessionService.getErrorMessage("leaderboardRemoveError"));
            model.addAttribute("leaderboard", leaderboard);
            return "manageLeaderboard";
        }

        return "redirect:/main";

    }


    @RequestMapping(value = "/leaderboard/manage/{leaderboardId}/removePlayer")
    public String removePlayer(ModelMap model, HttpServletRequest request, @PathVariable("leaderboardId") String leaderboardId, @RequestParam(value="removePlayer") String removePlayer) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

        String ownerId =  MockDB.getOwnerId(leaderboardId);
        // hvis eier
        if(sessionService.getUserId().equals(ownerId)) {

            // Sjekk at man ikke sletter seg selv
            String removeId = MockDB.getUserId(removePlayer);
            if(!removeId.equals(ownerId)) {
                MockDB.removePlayerFromLeaderboard(leaderboardId, removeId);
                sessionService.setErrorMessage("leaderboardRemoveError", "");

            } else {
                sessionService.setErrorMessage("leaderboardRemoveError", "You cannot remove yourself");
            }
            return "redirect:/leaderboard/manage/" + leaderboardId ;



        }

        // hent leaderboard
        // slett spiller
        // oppdater leaderboard
        // redirect til manage



        return "redirect:/main";

    }

    @RequestMapping(value = "/leaderboard/manage/{leaderboardId}/invite", method = RequestMethod.POST)
    public String invitePlayer(ModelMap model, HttpServletRequest request,@PathVariable("leaderboardId") String leaderboardId , @RequestParam(value="invitePlayer") String invitePlayer) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

        // Hvis eier
        if(sessionService.getUserId().equals(MockDB.getOwnerId(leaderboardId))) {
            Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);

            // Sjekk at man ikke inviterer seg selv
            if(leaderboard.getOwnerUsername().equals(invitePlayer)) {
                leaderboard.setInviteErrorMessage("Invite a friend!");
                MockDB.updateLeaderboard(leaderboard);
                return "redirect:/leaderboard/manage/" + leaderboard.getId();
            }

            // Sjekk at man ikke inviterer samme flere ganger
            for(String name : leaderboard.getInvitedPlayerUsernames()) {
                if(name.equals(invitePlayer)) {
                    leaderboard.setInviteErrorMessage("Player already invited");
                    MockDB.updateLeaderboard(leaderboard);

                    return "redirect:/leaderboard/manage/" + leaderboard.getId();
                }
            }

            // Sjekk at spiller eksisterer
            if(MockDB.isUser(invitePlayer)) {

                leaderboard.setInviteErrorMessage("Player " + invitePlayer + " invited!");
                leaderboard.getInvitedPlayerUsernames().add(invitePlayer);
                MockDB.updateLeaderboard(leaderboard);

                NotificationService notificationService = new NotificationService();
                notificationService.sendLeaderboardInvitation(MockDB.getUserId(invitePlayer), sessionService.getUserId(), leaderboardId);

                return "redirect:/leaderboard/manage/" + leaderboard.getId();

            } else {

                leaderboard.setInviteErrorMessage("Player not found");
                MockDB.updateLeaderboard(leaderboard);

                return "redirect:/leaderboard/manage/" + leaderboard.getId();

            }

        }
        return "redirect:/leaderboard";


    }


    @RequestMapping(value = "/leaderboard/accept/{leaderboardId}", method = RequestMethod.POST)
    public String acceptInvitation(@PathVariable("leaderboardId") String leaderboardId, @RequestParam("notificationId") String notificationId, HttpServletRequest request, ModelMap model) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

        Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
        if(leaderboard.getInvitedPlayerUsernames().contains(user)) { // Hvis spiller er invitert

            MockDB.deleteLeaderboardInvitation(notificationId);
            leaderboard.getInvitedPlayerUsernames().remove(user);

            String userId = sessionService.getUserId();
            leaderboard.getPlayerStats().add(new UserStatistics(userId));

            MockDB.updateLeaderboard(leaderboard);
            MockDB.addLeaderboardIdToUser(userId, leaderboardId);

            return "redirect:/leaderboard/" + leaderboardId;
        }
        return "redirect:/notifications";

    }

    @RequestMapping(value = "/leaderboard/reject/{leaderboardId}", method = RequestMethod.POST)
    public String rejectInvitation(@PathVariable("leaderboardId") String leaderboardId, @RequestParam("notificationId") String notificationId, HttpServletRequest request, ModelMap model) {
        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

        String userId = sessionService.getUserId();


        Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
        if(leaderboard.getInvitedPlayerUsernames().contains(user)) { // Hvis spiller er invitert

            NotificationService notificationService = new NotificationService();
            notificationService.deleteNotification(userId, notificationId);
            leaderboard.getInvitedPlayerUsernames().remove(user);
            MockDB.updateLeaderboard(leaderboard);

        }

        return "redirect:/notifications";

    }


}
