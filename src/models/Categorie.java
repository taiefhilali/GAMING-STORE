/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Iskander
 */
public class Categorie {

    private int id_categorie;
    private String nom_categorie;

    // Constructeurr vide
    public Categorie() {
    }

    // Constructeur paramétré
    public Categorie(int id_categorie, String nom_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
    }
    // Constructeur avec id seuelemnt

    public Categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    // Constructor sans id
    public Categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    // Getters & Setters
    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    // Affichage catégories
    @Override
    public String toString() {
        return " {" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + '}';
    }

    public void add(Categorie categorie) {

    }

}
