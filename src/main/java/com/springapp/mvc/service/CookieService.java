package com.springapp.mvc.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class CookieService {

    // returnerer null om bruker ikke er logget inn
    public static String getLoggedInUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies) {

            if(c.getName().equals("username")) {

                if(c.getValue() != null) {
                    System.out.println("CookieService: user logged in");
                    return c.getValue();
                }
            }
        }

        System.out.println("CookieService: User not logged in");
        return null;
    }





}
