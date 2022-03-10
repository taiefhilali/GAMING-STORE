/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author beldi
 */
public class Session {
    static int id;
    static String email;
    static String password;
    static String role;
    static String nom;
    static String prenom;
    static String adresse;
    static String tel;
    static Date dns;
    static String image;

    public static int getId() {
        return id;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        Session.image = image;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Session.role = role;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Session.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Session.prenom = prenom;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        Session.adresse = adresse;
    }

    public static String getTel() {
        return tel;
    }

    public static void setTel(String tel) {
        Session.tel = tel;
    }

    public static Date getDns() {
        return dns;
    }

    public static void setDns(Date dns) {
        Session.dns = dns;
    }

    
    
    
    
}
