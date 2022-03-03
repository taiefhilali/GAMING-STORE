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
public class Produit {

    private int id;
    private String nom;
    private String reference;
    private int id_categorie; // ou bien id categorie?
    private double prix;
    private String description;
    private String etat;  // présent ou non dans le stock
    private  User user;

    // Constructeur vide
    public Produit() {
    }

    // Constructeur paramétrés
    public Produit(int id, String nom, String reference, int id_categorie, double prix, String description, String etat, User user) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.id_categorie = id_categorie;
        this.prix = prix;
        this.description = description;
        this.etat = etat;
        this.user = user;
    }

    public Produit(int id, String nom, double prix, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }

    public Produit(int id, String nom, String reference, double prix, String description) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.description = description;
    }

     public Produit(int id, String nom, double prix){
        this.id= id;
        this.nom = nom;
        this.prix = prix;
    }
    
    // Constructeur sans l'attribut ID ( Doit-je enlever l'id fournisseur?)
    public Produit(String nom, String reference, int id_categorie, double prix, String description, String etat, User user) {
        this.nom = nom;
        this.reference = reference;
        this.id_categorie = id_categorie;
        this.prix = prix;
        this.description = description;
        this.etat = etat;
        this.user = user;
    }
    // Création des getters et Setters

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // To String
    @Override
    public String toString() {
        return "Le produit est : {" + "id=" + id + ", nom=" + nom + ", reference=" + reference + ", id_categorie=" + id_categorie + ", prix=" + prix + ", description=" + description + ", etat=" + etat + ", id_fournisseur=" + user + '}';
    }

}
