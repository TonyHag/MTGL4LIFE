package com.springapp.mvc.service;

/**
 * Created by eirikskogland on 02.12.14.
 */
public class ValidationService {

    public boolean validateLoginCredentials(String username, String password) {
        boolean valid = false;

        EncryptionService encryptionService = new EncryptionService();
        if(MockDB.isUser(username) && MockDB.passwordMatch(username, encryptionService.encryptPassword((password)))) {
            valid = true;
        }
        return valid;
    }

    public boolean validateUsername(String username) {
        boolean valid = true;

        //String regex = "/^[A-Za-z0-9_-]{3,16}$/";
        if(username == null){
            valid = false;
        } else if(username.length() < 3 || username.length() > 30) {
            valid = false;
        }

        // Sjekk med regex

        // Sjekk med db
        if(!MockDB.isUsernameAvailable(username)) {
            valid = false;
        }
        return valid;
    }

    public boolean validatePassword(String password) {
        boolean valid = true;

        if(password == null) {
            valid = false;
        }  else if(password.length() < 4 || password.length() > 30) {
            valid = false;
        }


        // Sjekk med regex

        return valid;
    }

    public boolean validateEmail(String email) {
        boolean valid = true;

        if(email == null) {
            valid = false;
        } else  if(email.length() < 4 || email.length() > 30) {
            valid = false;
        }


        // Sjekk med regex

        return valid;
    }

    public boolean description(String description) {
        boolean valid = true;

        if(description == null){
            valid = false;

        } else if(description.length() < 3 || description.length() > 100) {
            valid = false;

        }


        // Sjekk med regex

        return valid;
    }


}
