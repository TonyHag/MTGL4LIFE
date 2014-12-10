package com.springapp.mvc.service;

import com.springapp.mvc.model.ErrorMessage;
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
            boolean cookieFound = false;

            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("mtgsid")) { // sjekker om sessionID cookien finnes
                    String cSID = cookie.getValue();  // henter sid lagret i cookie
                    cookieFound = true;
                    if(!cSID.equals(SID)) {
                        isLoggedIn = false; // Hvis IDene ikke stemmer invalideres session
                        session.invalidate();
                        break;
                    }
                }
            }
            if(!cookieFound) { // returnerer false hvis cookie ikke finnes
                isLoggedIn = false;
            }

        } else {
            isLoggedIn = false; // Hvis session er null er ikke brukeren logget inn
        }
        return isLoggedIn;

    }

    /**
     * Setter en ny errormessage. Hvis det finnes en errormessage av samme type,
     * vil message byttes ut, ellers legges til helt ny errormessage
     * @param type
     * @param message
     */
    public void setErrorMessage(String type, String message) {
        boolean messageSet = false;
        for(ErrorMessage em : getSessionData().getErrorMessages()) {
            if(em.getType().equals(type)) {
                em.setMessage(message);
                messageSet = true;
                break;
            }
        }
        if(!messageSet) {
            getSessionData().getErrorMessages().add(new ErrorMessage(type, message));
        }
    }
    public String getErrorMessage(String type) {
        SessionData sessionData = getSessionData();
        if(sessionData.getErrorMessages() != null) {
           for(ErrorMessage em : sessionData.getErrorMessages()) {
               if (em.getType().equals(type)) {
                   return em.getMessage();
               }
           }
        }
        return null;
    }

    /**
     * Returnerer lagret brukernavn fra sessionData
     * @return
     */
    public String getUsername() {
        SessionData sessionData = (SessionData) request.getSession().getAttribute("sessionData");
        return sessionData.getUsername();
    }

    /**
     * Henter brukerid fra sessiondata
     * @return
     */
    public int getUserId() {
        SessionData sessionData = (SessionData) request.getSession().getAttribute("sessionData");
        return sessionData.getUserId();
    }

    /**
     * Henter lobby fra sessiondata
     * @return
     */
    public Lobby getLobby() {

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        return sessionData.getLobby();

    }

    /**
     * Setter lobby til sessionData
     * @param lobby
     */
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

    public void setActiveGame(int gameId) {
        getSessionData().setActiveGameId(gameId);
    }

    public void inActivateGame() {
        getSessionData().setActiveGameId(0);
    }

    public int getActiveGame() {
        return getSessionData().getActiveGameId();
    }

    // -------------------------------------------------------------------------
    // --- Ny version over, gammel under. Skriver om/flytter opp etterhvert ----
    // -------------------------------------------------------------------------




}
