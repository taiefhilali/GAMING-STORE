/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class AdminCommandes {
    int id;
    String nom;
    String prenom;
    Date d;
    double prix_Liv;
    double prix_Produits;
    double prix_Total;

    public AdminCommandes() {
    }

    public AdminCommandes(int id, String nom, String prenom, Date d, double prix_Liv, double prix_Produits, double prix_Total) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.d = d;
        this.prix_Liv = prix_Liv;
        this.prix_Produits = prix_Produits;
        this.prix_Total = prix_Total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public double getPrix_Liv() {
        return prix_Liv;
    }

    public void setPrix_Liv(double prix_Liv) {
        this.prix_Liv = prix_Liv;
    }

    public double getPrix_Produits() {
        return prix_Produits;
    }

    public void setPrix_Produits(double prix_Produits) {
        this.prix_Produits = prix_Produits;
    }

    public double getPrix_Total() {
        return prix_Total;
    }

    public void setPrix_Total(double prix_Total) {
        this.prix_Total = prix_Total;
    }

    @Override
    public String toString() {
        return "AdminCommandes{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", d=" + d + ", prix_Liv=" + prix_Liv + ", prix_Produits=" + prix_Produits + ", prix_Total=" + prix_Total + '}';
    }
    
    
}
