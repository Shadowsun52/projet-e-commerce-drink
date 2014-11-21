/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.MessageDigest;

/**
 *
 * @author Alexandre
 */
public class Encryption {
    private static final String SALT = "UNESUITERANDOM";
    
    public static String encryption(String input)
    {
        try{
            String password = input + SALT;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        }
        catch(Exception e){
            System.err.println(e);
            return null;
        }    
    }
}
