package com.springapp.mvc.service;

import java.security.MessageDigest;

/**
 * Created by eirikskogland on 03.12.14.
 */
public class EncryptionService {

    public String encryptPassword(String password){
        String encryptedPassword = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String salt = "hemmeligsalt";

            byte[] hash = digest.digest((password+salt).getBytes("UTF-8"));

            // Konteverterer fra byte[] til hex-streng
            StringBuilder PasswordHexString = new StringBuilder();
            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) PasswordHexString.append('0');
                PasswordHexString.append(hex);
            }

            encryptedPassword = PasswordHexString.toString();

        } catch (Exception e) {
            System.out.println("EncryptionService: Noe gikk galt med passordkryptering");
            e.printStackTrace();
        }

        return encryptedPassword;
    }

}
