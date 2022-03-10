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

    private int id_produit;
    private String nom;
    private String reference;  // 0x00001 // How to?
    private Categorie categorie;
    private double prix;
    private String description;
    // private String etat ? // présent ou non dans le stock // Stock  // id du stock
    private User user; // = Fid_fournisseur
    private double promotion;
    private String image;

    // Constructeur vide
    public Produit() {
    }

    // Constructeur paramétrés (tout les attributs)
    public Produit(int id_produit, String nom, String reference, Categorie categorie, double prix, String description, User user, double promotion, String image) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
        this.image = image;
    }

    // Constructeur sans l'attribut ID
    public Produit(String nom, String reference, Categorie categorie, double prix, String description, User user, double promotion, String image) {
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
        this.image = image;
    }

    public Produit(int id_produit, String nom, double prix, String description, String image) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    
    public Produit(int id_produit, String nom, String reference, double prix, String description) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.description = description;
    }

    
    
    public Produit(int id, String nom, double prix, String description) {
        this.id_produit = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }
    // Constructeur sans image

    public Produit(int id_produit, String nom, String reference, Categorie categorie, double prix, String description, User user, double promotion) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
    }

    /// TEST 
    // Constructeur sans id et sans image
    public Produit(String nom, String reference, Categorie categorie, double prix, String description, User user, double promotion) {
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
    }

    // Création des getters et Setters
    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // To String
    @Override
    public String toString() {
        return " ** Les produits sont : {" + "id_produit=" + id_produit + ", nom=" + nom + ", reference=" + reference + ", categorie=" + categorie + ", prix=" + prix + ", description=" + description + ", user=" + user + ", promotion=" + promotion + ", image=" + image + '}';
    }

    // La méthode de concaténation pour le 1ér métier
    public String concat() {
        return id_produit + "-" + nom + "-" + reference + "-" + categorie.getId_categorie() + "-" + categorie.getNom_categorie() + "-" + prix + "-" + description + "-" + user.getEmail()+ "-" + promotion + "-" + image + "-"; // probléme d'espace / path : 7othom f dossier sans espace
    }
}
