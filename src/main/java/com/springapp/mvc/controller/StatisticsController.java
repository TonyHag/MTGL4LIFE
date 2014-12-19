package com.springapp.mvc.controller;

import com.springapp.mvc.model.statistics.FFAStats;
import com.springapp.mvc.model.statistics.THGStats;
import com.springapp.mvc.model.statistics.TotalStats;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eirikskogland on 05.12.14.
 */
@Controller
public class StatisticsController {

    @RequestMapping(value = "stats/{username}", method = RequestMethod.GET)
    public String getStatisticsPage(@PathVariable("username") String username, @RequestParam(value = "statsType", required = false) String statsType, ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);


        if(statsType == null || statsType.equals("total")) {
            TotalStats totalStats = (TotalStats) MockDB.getUserStatsByName(username, "total");
            model.addAttribute("stats", totalStats);

        } else if(statsType.equals("ffa")) {
            FFAStats ffaStats = (FFAStats) MockDB.getUserStatsByName(username, "ffa");
            model.addAttribute("stats", ffaStats);

        } else if(statsType.equals("thg")) {
            THGStats thgStats = (THGStats) MockDB.getUserStatsByName(username, "thg");
            model.addAttribute("stats", thgStats);
        }

        model.addAttribute("statsType", statsType);



        return "stats";
    }

    @RequestMapping(value = "stats/search", method = RequestMethod.POST)
    public String search(@RequestParam("searchForPlayer") String player, ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String user = sessionService.getUsername();
        model.addAttribute("user", user);


         return "redirect:/stats/" + player;

    }




}
