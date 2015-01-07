package com.springapp.mvc.controller;

import com.springapp.mvc.model.RegisterFormData;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eirikskogland on 02.12.14.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterForm(ModelMap model, HttpServletRequest request) {

        SessionService sessionService = new SessionService(request);
        if(sessionService.isLoggedIn()) {
            return "redirect:/main";
        }
        // -- autentisering ferdig



        return "register";

    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitRegisterForm(ModelMap model, HttpServletRequest request, @ModelAttribute RegisterFormData data, BindingResult result) {

        SessionService sessionService = new SessionService(request);
        if(sessionService.isLoggedIn()) {
            return "redirect:/main";
        }
        // -- autentisering ferdig


        // Om input parametre har errors
        if(result.hasErrors()) {
            return "register";
        }

        // RegisterFromData data blir laget automatisk siden attributtene i RegisterFormData har samme navn som i formen
        String username = data.getUsername();
        String password1 = data.getPassword1();
        String password2 = data.getPassword2();
        String email = data.getEmail();
        String country = data.getCountry();
        String city = data.getCity();

        boolean validInput = true;

        ValidationService validation = new ValidationService();
        // valider brukernavn
        if(validation.validateUsername(username)) {
            model.remove("usernameError");
            model.addAttribute("validUsername", username);
        } else {
            model.addAttribute("usernameError", "Invalid username");
            validInput = false;
        }

        // Valider passord1
        if(validation.validatePassword(password1)) {
            model.remove("password1Error");

            // Valider passord2, ikke vits å validere passord2 hvis passord1 ikke er valid
            if(password1.equals(password2)) {
                model.remove("password2Error");
            } else {
                model.addAttribute("password2Error", "Passwords must match");
                validInput = false;
            }

        } else {
            model.addAttribute("password1Error", "Invalid password");
            validInput = false;
        }


        // Valider email
        if(validation.validateEmail(email)) {
            model.remove("emailError");
            model.addAttribute("validEmail", email);
        } else {
            model.addAttribute("emailError", "Invalid email");
            validInput = false;
        }

        if(validInput) {

            // Krypter passord
            EncryptionService encryptionService = new EncryptionService();
            String encryptedPassword = encryptionService.encryptPassword(password1);

            // Opprett ny bruker
            User newUser = new User(username, encryptedPassword, email, country, city);
            // lagre i database

            // legg til i world leaderboard


            // returner til login
            return "redirect:login";


        }

        return "register";
    }


}
