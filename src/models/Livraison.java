/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

public class Livraison {
    
    //var
    private int id_livraison;
    private int id_commande;
    private int id_livreur;
    private Date date;
    private String etat_livraison;
    
    //constructeur

    public Livraison() {
    }

    public Livraison(int id_livraison, int id_commande, int id_livreur, Date date, String etat_livraison) {
        this.id_livraison = id_livraison;
        this.id_commande = id_commande;
        this.id_livreur = id_livreur;
        this.date = date;
        this.etat_livraison = etat_livraison;
    }

    public Livraison(int id_commande, int id_livreur, Date date, String etat_livraison) {
        this.id_commande = id_commande;
        this.id_livreur = id_livreur;
        this.date = date;
        this.etat_livraison = etat_livraison;
    }

  

    
    //getter et setter

    public int getId_livraison() {
        return id_livraison;
    }

    public int getId_commande() {
        return id_commande;
    }

    public int getId_livreur() {
        return id_livreur;
    }

   

    public Date getDate() {
        return date;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

   

  

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    @Override
    public String toString() {
        return "livraison{" + "id_livraison=" + id_livraison + ", id_commande=" + id_commande + ", id_livreur=" + id_livreur + ", date=" + date + ", etat_livraison=" + etat_livraison + '}';
    }

   
    
}


