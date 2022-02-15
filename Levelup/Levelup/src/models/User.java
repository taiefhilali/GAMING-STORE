/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author 21694
 */
public class User {

    //var
    private int id_user;
    private String email;
    private String password;
    private String role;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private Date dns;

    //constructeur sans paramétres
    public User() {
    }

    //constructeur all
    public User(int id_user, String email, String password, String role, String nom, String prenom, String adresse, String tel, Date dns) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.dns = dns;
    }
      //constructeur id
    
    public User(int id_user) {
         this.id_user = id_user;
    }

    //constructeur sans id 

    public User(String email, String password, String role, String nom, String prenom, String adresse, String tel,Date dns) {

        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.dns=dns;
    
    }



 //getter et setter
    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDns() {
        return dns;
    }

    public void setDns(Date dns) {
        this.dns = dns;
    }

     //affichage
    @Override
    public String toString() {
        return "User{" + "id=" + id_user + ", email=" + email + ", password=" + password + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", dns=" + dns + '}';
    }

}
