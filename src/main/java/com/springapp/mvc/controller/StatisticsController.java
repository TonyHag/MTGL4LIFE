package com.springapp.mvc.controller;

import com.springapp.mvc.model.UserStatistics;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eirikskogland on 05.12.14.
 */
@Controller
public class StatisticsController {

    @RequestMapping(value = "stats/{username}", method = RequestMethod.GET)
    public String getStatisticsPage(@PathVariable("username") String username, ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);

        UserStatistics stats = MockDB.getUserStats(username);
        model.addAttribute("stats", stats);

        return "stats";
    }



}
