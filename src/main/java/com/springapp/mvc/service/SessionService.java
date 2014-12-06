package com.springapp.mvc.service;

import com.springapp.mvc.model.Lobby;
import com.springapp.mvc.model.SessionData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class SessionService {


    public static SessionData getSessionData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (SessionData) session.getAttribute("sessionData");

    }

    public static void setSessionData(HttpServletRequest request, SessionData sessionData) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionData", sessionData);

    }

    // returnerer null om bruker ikke er logget inn
    public static String getLoggedInUser(HttpServletRequest request) {

        SessionData sessionData = getSessionData(request);
        if(sessionData != null) {
            return sessionData.getUsername();
        }

        return null;
    }


    public static int getLoggedInUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        return sessionData.getUserId();
    }


    public static void logOut(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("sessionData");

        System.out.println("user logged out");

    }

    public static Lobby getLobby(HttpServletRequest request) {

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");

        return sessionData.getLobby();
    }

    public static void setLobby(Lobby lobby, HttpServletRequest request) {

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");

        sessionData.setLobby(lobby);

    }

    public static void deleteLobby(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        sessionData.setLobby(null);

    }


}
