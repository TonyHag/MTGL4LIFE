package com.springapp.mvc.controller;

import com.springapp.mvc.model.UserStatistics;
import com.springapp.mvc.service.MockDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eirikskogland on 05.12.14.
 */
@Controller
public class StatisticsController {

    @RequestMapping(value = "stats/{username}", method = RequestMethod.GET)
    public String getStatisticsPage(@PathVariable("username") String username, ModelMap model) {

        UserStatistics stats = MockDB.getUserStats(username);
        System.out.println("Stats for " + stats.getUsername());
        model.addAttribute("stats", stats);

        return "stats";

    }


}
