/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {
    private static MessageDigest digester;
    private static Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

   public static String crypt(String password) {

        String hash = argon2.hash(4, 65536, 1, password);
        argon2.wipeArray(password.toCharArray()); //tnahilek data zeydin
        return hash;
    }
   
   public static boolean matches(String hash, String password) {
       return argon2.verify(hash, password);
   }
}