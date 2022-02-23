/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class Commande_elem {
    
    //var 
    private int id_elemC;
    private int id_produit;
    private int id_commande;
    private int Quantite;

    //constructeur
    public Commande_elem() {
    }

    public Commande_elem(int id_elemC, int id_produit, int id_commande, int Quantite) {
        this.id_elemC = id_elemC;
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.Quantite = Quantite;
    }
    
    //getters and setters

    public int getId_elemC() {
        return id_elemC;
    }

    public void setId_elemC(int id_elemC) {
        this.id_elemC = id_elemC;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    @Override
    public String toString() {
        return "Commande_elem{" + "id_elemC=" + id_elemC + ", id_produit=" + id_produit + ", id_commande=" + id_commande + ", Quantite=" + Quantite + '}';
    }
    
    
    
}
