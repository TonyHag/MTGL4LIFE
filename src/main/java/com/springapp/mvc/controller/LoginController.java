package com.springapp.mvc.controller;

import com.springapp.mvc.model.SessionData;
import com.springapp.mvc.service.EncryptionService;
import com.springapp.mvc.service.MockDB;
import com.springapp.mvc.service.SessionService;
import com.springapp.mvc.service.ValidationService;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by eirikskogland on 02.12.14.
 */

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(ModelMap model, HttpServletRequest request) {
        SessionService sessionService = new SessionService(request);
        if(sessionService.isLoggedIn()) {
            return "redirect:/main";
        }
        // -- autentisering ferdig
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitLoginForm(ModelMap model,  HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        SessionService sessionService = new SessionService(request);
        if(sessionService.isLoggedIn()) {
            return "redirect:/main";
        }
        // -- autentisering ferdig


        System.out.println("Login form submitted");

        ValidationService validation = new ValidationService();
        EncryptionService encryptionService = new EncryptionService();

        if(validation.validateLogin(username, encryptionService.encryptPassword(password))){ // hvis gyldig login

            Cookie sidCookie = sessionService.logIn(username); // Logger inn bruker
            response.addCookie(sidCookie); // setter cookie med sid

            /*
            // Opprett ny session id + andre ting som må gjøres ved login
            SessionData sessionData = new SessionData();
            sessionData.setUserId(MockDB.getUserId(username));
            sessionData.setUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("sessionData", sessionData);
            */

            return "redirect:main";

        } else {

            model.addAttribute("loginError", "Username or password is incorrect");
            return "login";
        }

    }

}
