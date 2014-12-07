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

        if(SessionService.getLoggedInUser(request) != null) {

            String username = SessionService.getLoggedInUser(request);
            model.addAttribute("user", username);


            return "main";

        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/main/logout", method = RequestMethod.GET)
    public String logOut(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("Log out");
       // HttpSession session = request.getSession();
       // session.removeAttribute("username");
        SessionService.logOut(request);

        return "redirect:/login";
    }


}
