/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelup;

import model.Categorie;
import model.Produit;
import service.ServiceCategorie;
import service.ServiceProduit;

/**
 *
 * @author Iskander
 */
public class Levelup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //                *************** PRODUIT *********************  //
        // Essai d'ajout d'un produit
        // Ajout de deux produits 
        Produit p = new Produit("HyperX Cloud II", "0x001", 1, 0, " Un casque pour les Gamers pro.", " En Stock ", 365); //en TND
        Produit p1 = new Produit("Razer Krake Pro v7", "0x001", 2, 500, " Un casque pour les Gamers pro.", " En Stock ", 2);
        Produit p3 = new Produit(1, "AAAAAAAAAAAAAA", "0x001", 2, 500, " Un casque pour les Gamers pro.", " En Stock ", 2);
        Produit p4 = new Produit(1, "BBBBBBBBBBBBBBBBBBBB", "0x001", 2, 500, " Un casque pour les Gamers pro.", " En Stock ", 2);
        // Ajout des produits 
        ServiceProduit sp = new ServiceProduit();
        sp.ajouterProduit(p1);

        // Modification des produits
        
        sp.modifierProduit(p4);
        // Suppression des produits
        Produit p2 = new Produit(10, "Chaise", "0x005", 2, 100, "Chaise pour Gamers", "Out of stock", 1);
        // sp.ajouterProduit(p2);
        
        sp.supprimerProduit(p4);
        
        // sp.supprimerProduit(p2);

        // Affichage ajout produit
        System.out.println(sp.afficherProduit());

        //        *********************************** CATEGORIES ************************************* //
        // Ajout d'une catégorie
        Categorie c = new Categorie("Chaises");
        Categorie c1 = new Categorie(2, "Bureaux");

        ServiceCategorie sc = new ServiceCategorie();
      //  sc.ajouterCategorie(c1);
      
        sc.modifierCategorie(c1);
        
        //sc.supprimerCategorie(c1);

    }
}
