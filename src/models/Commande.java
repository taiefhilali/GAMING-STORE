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
public class Commande {
    
    //var
   private int id_commande;
   private Client client;
   private double prix_livraison;
   private double prix_produits;
   private double prix_total;
   private Date date_commande;
   
    
   //Constructeur
    public Commande() {
    }  
    public Commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Commande(int id_commande, Client client, double prix_livraison, double prix_produits, double prix_total, Date date_commande) {
        this.id_commande = id_commande;
        this.client = client;
        this.prix_livraison = prix_livraison;
        this.prix_produits = prix_produits;
        this.prix_total = prix_total;
        this.date_commande = date_commande;
    }

    public Commande(int id_commande, double prix_livraison, double prix_produits, double prix_total, Date date_commande) {
        this.id_commande = id_commande;
        this.prix_livraison = prix_livraison;
        this.prix_produits = prix_produits;
        this.prix_total = prix_total;
        this.date_commande = date_commande;
    }
    
    
    public Commande(int id_commande, Client client, float prix_livraison, Date date_commande) {
        this.id_commande = id_commande;
        this.client = client;
        this.prix_livraison = prix_livraison;
        this.date_commande = date_commande;
    }

    public Commande(int id_commande, float prix_livraison, Date date_commande) {
        this.id_commande = id_commande;
        this.prix_livraison = prix_livraison;
        this.date_commande = date_commande;
    }
    
    //Getters and setters
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPrix_produits() {
        return prix_produits;
    }

    public void setPrix_produits(double prix_produits) {
        this.prix_produits = prix_produits;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

 

    public double getPrix_livraison() {
        return prix_livraison;
    }

    public void setPrix_livraison(double prix_livraison) {
        this.prix_livraison = prix_livraison;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande +", Prix_Produits =" + prix_produits +", Prix_total =" + prix_total + ", client={ " + client +" }"+ ", prix_livraison=" + prix_livraison + ", date_commande=" + date_commande +'\n'+ '}';
    }
   
    
   
   
}
