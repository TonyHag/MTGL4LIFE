package com.springapp.mvc.service;

import com.springapp.mvc.model.Lobby;
import com.springapp.mvc.model.SessionData;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by eirikskogland on 04.12.14.
 */
public class SessionService {

    private HttpServletRequest request;

    public SessionService(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Oppretter ny session og setter SessionData med brukernavn.
     * Returnerer en cookie med session id som må settes i response.
     * @param username
     * @return
     */
    public Cookie logIn(String username) {
        HttpSession session = request.getSession(); // henter session
        session.invalidate(); // invaliderer session om den fantes fra før
        session = request.getSession(true); // generer ny session

        SessionData sessionData = new SessionData();
        sessionData.setUsername(username);
        sessionData.setUserId(MockDB.getUserId(username));
        session.setAttribute("sessionData", sessionData); // Setter sessionData med brukernavn og id

        String sid = session.getId();
        Cookie sidCookie = new Cookie("mtgsid", sid);
        sidCookie.setMaxAge(60*60*24*7); // sek*min*timer*dager, gyldig i en uke
        return sidCookie;

    }

    /**
     * Invaliderer gjeldene session
     */
    public void logout() {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

    }


    /**
     * Sjekker om sid fra cookie stemmer med session id, returner true hvis de er like.
     * Hvis false invalideres session.
     * @return
     */
    public boolean isLoggedIn() {

        boolean isLoggedIn = true;

        HttpSession session = request.getSession(false); // henter session fra request, null om det ikke er en session

        if(session != null) {
            String SID = session.getId(); // henter sessionID fra session

            Cookie[] cookies = request.getCookies(); // henter cookies fra request
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("mtgsid")) { // sjekker om sessionID cookien finnes
                    String cSID = cookie.getValue();  // henter sid lagret i cookie
                    if(!cSID.equals(SID)) {
                        isLoggedIn = false; // Hvis IDene ikke stemmer invalideres session
                        session.invalidate();
                        break;
                    }

                }
            }
        } else {
            isLoggedIn = false; // Hvis session er null er ikke brukeren logget inn
        }
        return isLoggedIn;

    }

    /**
     * Returnerer lagret brukernavn fra sessionData
     * @return
     */
    public String getUsername() {
        SessionData sessionData = (SessionData) request.getSession().getAttribute("sessionData");
        return sessionData.getUsername();
    }

    public int getUserId() {
        SessionData sessionData = (SessionData) request.getSession().getAttribute("sessionData");
        return sessionData.getUserId();
    }

    public Lobby getLobby() {

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        return sessionData.getLobby();

    }

    public void setLobby(Lobby lobby) {

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        sessionData.setLobby(lobby);

    }

    /**
     * Returnerer lagret sessiondata
     * @return
     */
    public SessionData getSessionData() {
        HttpSession session = request.getSession();
        return (SessionData) session.getAttribute("sessionData");
    }

    // -------------------------------------------------------------------------
    // --- Ny version over, gammel under. Skriver om/flytter opp etterhvert ----
    // -------------------------------------------------------------------------


    public static void setSessionData(HttpServletRequest request, SessionData sessionData) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionData", sessionData);

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


    public static void deleteLobby(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        sessionData.setLobby(null);

    }


}
