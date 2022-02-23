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
public class Panier_elem {
    
    //var
    private int id_elem;
    private int id_panier;
    private int id_produit;
    
    //constructeur

    public Panier_elem() {
    }

    public Panier_elem(int id_elem, int id_panier, int id_produit) {
        this.id_elem = id_elem;
        this.id_panier = id_panier;
        this.id_produit = id_produit;
    }
    
    //getters and setters

    public int getId_elem() {
        return id_elem;
    }

    public void setId_elem(int id_elem) {
        this.id_elem = id_elem;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public String toString() {
        return "Panier_elem{" + "id_elem=" + id_elem + ", id_panier=" + id_panier + ", id_produit=" + id_produit + '}';
    }
    
    
    
}
