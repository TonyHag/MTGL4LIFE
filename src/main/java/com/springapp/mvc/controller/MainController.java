package com.springapp.mvc.controller;

import com.springapp.mvc.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by eirikskogland on 03.12.14.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainMenu(ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig
        String username = sessionService.getUsername();
        model.addAttribute("user", username);

        int numberOfNotifications = sessionService.getNumberOfNotifications();
        model.addAttribute("numberOfNotifications", numberOfNotifications);


        return "main";
    }

    @RequestMapping(value = "/main/logout", method = RequestMethod.GET)
    public String logOut(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        SessionService sessionService = new SessionService(request);
        if(!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        // -- autentisering ferdig


        sessionService.logout();
        return "redirect:/login";

    }


}
