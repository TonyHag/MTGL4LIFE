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

        if(SessionService.getLoggedInUser(request) != null) {
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            ArrayList<Integer> leaderboardIds = MockDB.getLeaderboardIdsForUser(SessionService.getLoggedInUserId(request));

            ArrayList<LeaderboardInfo> leaderboardInfos = new ArrayList<LeaderboardInfo>();

            for(Integer i : leaderboardIds) {
                leaderboardInfos.add(new LeaderboardInfo(i, MockDB.getLeaderboardNameById(i)));
            }

            model.addAttribute("leaderboardInfos", leaderboardInfos);

            return "leaderboards";

        }

        return "redirect:/login";
    }

    @RequestMapping(value ="/leaderboard/{leaderboardId}", method = RequestMethod.GET)
    public String getLeaderboardPage(ModelMap model, HttpServletRequest request, @PathVariable("leaderboardId") int leaderboardId) {

        if(SessionService.getLoggedInUser(request) != null) {
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);
            Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
            model.addAttribute("leaderboard", leaderboard);


            return "leaderboard";

        }

        return "redirect:/login";
    }


    @RequestMapping(value = "/leaderboard/create", method = RequestMethod.GET)
    public String getLeaderboardCreateForm(ModelMap model, HttpServletRequest request) {

        if(SessionService.getLoggedInUser(request) != null) {
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);


                return "createLeaderboard";

        }

        return "redirect:/login";

    }

    @RequestMapping(value = "/leaderboard/create", method = RequestMethod.POST)
    public String submitLeaderboardForm(ModelMap model, HttpServletRequest request, @RequestParam(value= "name") String name, @RequestParam(value = "description") String description) {

        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            boolean valid = true;
            ValidationService validationService = new ValidationService();
            // validate name
            if(validationService.validateUsername(name)) {


            }
            // validate description

            if(validationService.validateUsername(description)){

            }


            if(valid) {

                Leaderboard leaderboard = new Leaderboard(SessionService.getLoggedInUserId(request));
                leaderboard.setName(name);
                leaderboard.setDescription(description);
                leaderboard.getPlayerStats().add(new UserStatistics(SessionService.getLoggedInUserId(request)));
                MockDB.addLeaderboard(leaderboard);
                MockDB.addLeaderboardIdToUser(SessionService.getLoggedInUserId(request), leaderboard.getId());

                int leaderBoardId = leaderboard.getId();
                return "redirect:/leaderboard/manage/" + leaderBoardId;

            }


        }

        return "redirect:/login";

    }

    @RequestMapping(value = "/leaderboard/manage/{leaderboardId}")
    public String getManageLeaderboardPage(ModelMap model, HttpServletRequest request, @PathVariable("leaderboardId") int leaderboardId) {

        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            // hvis eier
            if(SessionService.getLoggedInUserId(request) == MockDB.getOwnerId(leaderboardId))  {
                Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
                model.addAttribute("leaderboard", leaderboard);
                return "manageLeaderboard";
            }

            return "redirect:/main";

        }
        return "redirect:/login";
    }


    @RequestMapping(value = "/leaderboard/manage/{leaderboardId}/removePlayer")
    public String removePlayer(ModelMap model, HttpServletRequest request, @PathVariable("leaderboardId") int leaderboardId, @RequestParam(value="removePlayer") String removePlayer) {

        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            // hvis eier

            // hent leaderboard
            // slett spiller
            // oppdater leaderboard
            // redirect til manage



            return "redirect:/";

        }


        return "redirect:/login";
    }

    @RequestMapping(value = "/leaderboard/manage/{leaderboardId}/invite", method = RequestMethod.POST)
    public String invitePlayer(ModelMap model, HttpServletRequest request,@PathVariable("leaderboardId") int leaderboardId , @RequestParam(value="invitePlayer") String invitePlayer) {

        if(SessionService.getLoggedInUser(request) != null ) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            // Hvis eier
            if(SessionService.getLoggedInUserId(request) == MockDB.getOwnerId(leaderboardId)) {
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
                    notificationService.sendLeaderboardInvitation(MockDB.getUserId(invitePlayer), SessionService.getLoggedInUserId(request), leaderboardId);

                    return "redirect:/leaderboard/manage/" + leaderboard.getId();

                } else {

                    leaderboard.setInviteErrorMessage("Player not found");
                    MockDB.updateLeaderboard(leaderboard);

                    return "redirect:/leaderboard/manage/" + leaderboard.getId();

                }

            }

        }

        return "redirect:/login";
    }


    @RequestMapping(value = "/leaderboard/accept/{leaderboardId}", method = RequestMethod.POST)
    public String acceptInvitation(@PathVariable("leaderboardId") int leaderboardId, @RequestParam("notificationId") int notificationId, HttpServletRequest request, ModelMap model) {

        if(SessionService.getLoggedInUser(request) != null ) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
            if(leaderboard.getInvitedPlayerUsernames().contains(username)) { // Hvis spiller er invitert

                MockDB.deleteNotification(notificationId);
                leaderboard.getInvitedPlayerUsernames().remove(username);
                int userId = MockDB.getUserId(username);
                leaderboard.getPlayerStats().add(new UserStatistics(userId));
                MockDB.updateLeaderboard(leaderboard);
                MockDB.addLeaderboardIdToUser(userId, leaderboardId);

                return "redirect:/leaderboard/" + leaderboardId;
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/leaderboard/reject/{leaderboardId}", method = RequestMethod.POST)
    public String rejectInvitation(@PathVariable("leaderboardId") int leaderboardId, @RequestParam("notificationId") int notificationId, HttpServletRequest request, ModelMap model) {

        if(SessionService.getLoggedInUser(request) != null ) { // hvis logget inn
            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);

            Leaderboard leaderboard = MockDB.getLeaderboard(leaderboardId);
            if(leaderboard.getInvitedPlayerUsernames().contains(username)) { // Hvis spiller er invitert

                MockDB.deleteNotification(notificationId);
                leaderboard.getInvitedPlayerUsernames().remove(username);
                MockDB.updateLeaderboard(leaderboard);

                return "redirect:/notifications";
            }
        }
        return "redirect:/login";
    }


}
