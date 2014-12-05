package com.springapp.mvc.service;

/**
 * Created by eirikskogland on 02.12.14.
 */
public class ValidationService {



    public static boolean validateLogin(String username, String password) {
        boolean valid = false;

        if(MockDB.isUser(username) && MockDB.passwordMatch(username, password)) {
            valid = true;
        }
        return valid;
    }

    public static boolean validateUsername(String username) {
        boolean valid = true;

        if(username == null){
            valid = false;
        }

        if(username.length() < 3 || username.length() > 30) {
            valid = false;
        }


        // Sjekk med regex

        // Sjekk med db
        if(!MockDB.isUsernameAvailable(username)) {
            valid = false;
        }
        return valid;
    }

    public static boolean validatePassword(String password) {
        boolean valid = true;

        if(password == null) {
            valid = false;
        }

        if(password.length() < 4 || password.length() > 30) {
            valid = false;
        }


        // Sjekk med regex

        return valid;
    }

    public static boolean validateEmail(String email) {
        boolean valid = true;

        if(email == null) {
            valid = false;
        }

        if(email.length() < 4 || email.length() > 30) {
            valid = false;
        }


        // Sjekk med regex

        return valid;
    }

}
