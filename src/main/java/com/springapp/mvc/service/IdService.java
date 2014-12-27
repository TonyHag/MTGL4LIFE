package com.springapp.mvc.service;

import java.security.MessageDigest;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class IdService {

    public  String getLeaderboardId(String username) {
        return getHash(username);
    }

    public String getLobbyId(String username) {
        return getHash(username);
    }

    public String getGameId(String username) {
        return getHash(username);
    }

    public String getNotificationId(String username) {
        return getHash(username);
    }


    public String getUserId(String username) {
        return getHash(username);

    }


    public String getHash(String username) {

            long currentTime = System.currentTimeMillis();
            String id = "";
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                String salt = Long.toString(currentTime);

                byte[] hash = digest.digest((username+salt).getBytes("UTF-8"));

                // Konteverterer fra byte[] til hex-streng
                StringBuilder PasswordHexString = new StringBuilder();
                for (byte aHash : hash) {
                    String hex = Integer.toHexString(0xff & aHash);
                    if (hex.length() == 1) PasswordHexString.append('0');
                    PasswordHexString.append(hex);
                }

                id = PasswordHexString.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return id;


    }

}
