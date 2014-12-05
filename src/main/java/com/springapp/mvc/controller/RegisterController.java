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
        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            return "redirect:mainMenu";
        }

        System.out.println("Getting register page");
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitRegisterForm(ModelMap model, HttpServletRequest request, @ModelAttribute RegisterFormData data, BindingResult result) {
        if(SessionService.getLoggedInUser(request) != null) { // hvis logget inn
            return "redirect:mainMenu";
        }

        System.out.println("Registerform submitted");

        // Om input parametre har errors
        if(result.hasErrors()) {
            return "register";
        }

        // RegisterFromData data blir laget automatisk siden attributtene i RegisterFormData har samme navn som i formen
        String username = data.getUsername();
        String password1 = data.getPassword1();
        String password2 = data.getPassword2();
        String email = data.getEmail();

        System.out.println("Entered:" );
        System.out.println("Username: " + username);
        System.out.println("Password: " + password1 + ", " + password2);
        System.out.println("email: " + email);
        boolean validInput = true;

        // valider brukernavn
        if(ValidationService.validateUsername(username)) {
            model.remove("usernameError");
            model.addAttribute("validUsername", username);
        } else {
            model.addAttribute("usernameError", "Invalid username");
            validInput = false;
        }

        // Valider passord1
        if(ValidationService.validatePassword(password1)) {
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
        if(ValidationService.validateEmail(email)) {
            model.remove("emailError");
            model.addAttribute("validEmail", email);
        } else {
            model.addAttribute("emailError", "Invalid email");
            validInput = false;
        }

        if(validInput) {

            // Krypter passord
            String encryptedPassword = EncryptionService.encryptPassword(password1);



            // generer unik id
            int id = IdService.getUserId();

            // Opprett ny bruker
            User newUser = new User(id, username, encryptedPassword, email);
            // lagre i database
            MockDB.addUser(newUser);
            // returner til login
            return "redirect:login";


        }

        return "register";
    }


}
