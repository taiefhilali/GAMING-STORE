/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelup;

import interfaces.ICommande;
import interfaces.ICommande_elem;
import interfaces.IPanier;
import interfaces.IPanier_elem;
import interfaces.Iadministrateur;
import interfaces.Icategorie;
import interfaces.Iclient;
import interfaces.Icomment;
import interfaces.Ifacture;
import interfaces.Ifournisseur;
import interfaces.Ilivraison;
import interfaces.Ilivreur;
import interfaces.Ipost;
import interfaces.Istock;
import interfaces.Iuser;
import interfaces.Iproduit;
import interfaces.Ireclamation;
import java.sql.Connection;
import models.Administrateur;
import models.Client;
import models.Fournisseur;
import models.Livreur;
import models.User;
import services.ServiceAdministrateur;
import services.ServiceClient;
import services.ServiceFournisseur;
import services.ServiceLivreur;
import services.ServiceUser;
import utils.MaConnexion;
import java.sql.Date;
import models.Categorie;
import models.Commande;
import models.Commande_elem;
import models.Comment;
import models.Facture;
import models.Livraison;
import models.Panier;
import models.Panier_elem;
import models.Post;
import models.Produit;
import models.Reclamation;
import models.Stock;
import services.ServiceCategorie;
import services.ServiceProduit;
import services.ServiceCommande;
import services.ServiceCommande_elem;
import services.ServiceFacture;
import services.ServiceLivraison;
import services.ServicePanier_elem;
import services.ServiceReclamation;
import services.ServiceStock;
import services.Servicecomment;
import services.Servicepost;
import services.servicePanier;

/**
 *
 * @author beldi
 */
public class Levelup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection cnx = MaConnexion.getInstance().getCnx();

        String str = "2017-03-31";
        Date date = Date.valueOf(str);
        String adr = "Ben arouse/Mourouj 1/Rue 234/2074";

        //  ******************************************* SERVICES *******************************************  //
        Iuser sp = new ServiceUser();
        Iclient sc = new ServiceClient();
        Ifournisseur sf = new ServiceFournisseur();
        Ilivreur sl = new ServiceLivreur();
        Iadministrateur sa = new ServiceAdministrateur();
        Iproduit sproduit = new ServiceProduit();
        Ifacture sfacture = new ServiceFacture();
        Istock ss = new ServiceStock();
        Icategorie scategorie = new ServiceCategorie();
        Ipost spost = new Servicepost();
        Icomment scomment = new Servicecomment();
        ICommande scommande = new ServiceCommande();
        IPanier spanier = new servicePanier();
        IPanier_elem spanier2 = new ServicePanier_elem();
        ICommande_elem spanier3 = new ServiceCommande_elem();
        Ilivraison slivraison = new ServiceLivraison();
        Ireclamation sr = new ServiceReclamation();

          //  ******************************************* USERS ******************************************* //
        
                //Affichage des utilisateurs
        
        
        //Ajout des utilisateurs
        // Client   
      User user1 = new User(1, "Amalouira@gmail.com", "test", "client", "beldi", "mariem", adr, "26386558",date);
      // Client c = new Client("femme", (int)sp.ajouterPersonne(user1));
    // sc.ajouterPersonne(c);
        // AFFICHER Client
       //  System.out.println(sc.afficherPersonnes());
        
        // Fournisseur   
//        User user2 = new User("beldi.mohamed@gmail.com", "test", "fournisseur", "beldi", "mariem", adr, "26386558",date);
//        Fournisseur f =new Fournisseur("07227308","Arvea", (int)sp.ajouterPersonne(user2));
//        sf.ajouterPersonne(f);
          // AFFICHER Fournisseur
        // System.out.println(sf.afficherPersonnes());
        
        // Livreur   
//        User user3 = new User("beldi.mariem@gmail.com", "test", "livreur", "beldi", "mariem", adr, "26386558",date);
//        Livreur l =new Livreur("00000","07227308", (int)sp.ajouterPersonne(user3));
//        sl.ajouterPersonne(l);
        // AFFICHER Livreur
        //System.out.println(sl.afficherPersonnes());
        
        // Administrateur   
//        User user4 = new User("beldi.mariem@gmail.com", "test", "administrateur", "beldi", "mariem", adr, "26386558", date);
//        Administrateur a = new Administrateur("07227308", (int) sp.ajouterPersonne(user4));
//        sa.ajouterPersonne(a);
        //AFFICHER Administrateur
        //System.out.println(sa.afficherPersonnes());

        
        //Modification des utilisateurs
        // Client 
        Client cu = new Client("homme", 6, "beldi.mariem@gmail.com", "test", "client", "beldi", "amal", adr, "26386558", date);
       // System.out.println(sp.modifierPersonne(cu));
        //System.out.println(sc.modifierPersonne(cu));
        //System.out.println(sc.afficherPersonnes());
        //Fournisseur
        Fournisseur fu = new Fournisseur("08227308", "Arvea", 40, "beldi.mariem@gmail.com", "test", "fournissuer", "beldi", "mariem", adr, "26386558", date);
//        System.out.println(sp.modifierPersonne(f));
//        System.out.println(sf.modifierPersonne(f));
        // System.out.println(sf.afficherPersonnes());
        //Livreur
        Livreur lu = new Livreur("0234", "09227308", 41, "beldi.mariem@gmail.com", "test", "livreur", "beldi", "mariem", adr, "26386558", date);
//        System.out.println(sp.modifierPersonne(l));
//        System.out.println(sl.modifierPersonne(l));
         //System.out.println(sl.afficherPersonnes());
        //Administrateur
        Administrateur au = new Administrateur("06227308", 42, "beldi.mariem@gmail.com", "test", "administrateur", "beldi", "mariem", adr, "26386558", date);
//        System.out.println(sp.modifierPersonne(a));
//        System.out.println(sa.modifierPersonne(a));
        //System.out.println(sa.afficherPersonnes());

        //Supression des utilisateurs
        //Client
      // System.out.println(sp.supprimerPersonne(cu));
       //System.out.println(sc.supprimerPersonne(cu));
        //Fournisseur
//        System.out.println(sp.supprimerPersonne(fu));
//        System.out.println(sf.supprimerPersonne(fu));
        //Livreur
//        System.out.println(sp.supprimerPersonne(lu));
//        System.out.println(sl.supprimerPersonne(lu));
        //Administrateur
//        System.out.println(sp.supprimerPersonne(au));
//        System.out.println(sa.supprimerPersonne(au));
        
        // *******************************************  STOCKS *******************************************   //
        Stock s = new Stock("accessoire", 12, "en stock");
        Facture f = new Facture(date, "90.990dt");
        //Ajout  stock et facture
        //ss.ajouterStock(s);
        //sfacture.ajouterFacture(f);
        //Affichage stocks et factures
        //System.out.println(ss.afficherStock());
       //System.out.println(sfacture.afficherFacture());
        //Modification stock et facture
        Stock s1 = new Stock(56, "pc", 220, "en stock");
        //System.out.println(ss.modifierStock(s1));
        Facture f1 = new Facture(26, date, "61.650dt");
        //System.out.println(sfacture.modifierFacture(f1));

        //Suppression stock et facture
        //System.out.println(ss.supprimerStock(s1));
      // System.out.println(sfacture.supprimerFacture(f1));
        
        //  *******************************************  PRODUITS ET CATEGORIES *******************************************   //    
        
        
        // Test 2 : aprés jointure
                // Insertion d'apres phpmyadmin insert value vide id
                
         Categorie c1 = new Categorie(1,"Souris");
         Produit p1 = new Produit("HyperX Cloud II", "0x001", c1, 350 , " Un casque pour les Gamers pro ", user1);
         Produit p2 = new Produit(1, "HyperX Cloud II", "0x001", c1, 350 , " Un casque pour les Gamers pro ", user1);
         
         //sproduit.ajouterProduit(p2);
        
         sproduit.afficherProduit();
         System.out.println(sproduit.afficherProduit());
         System.out.println(scategorie.afficherCategorie());
         System.out.println(scategorie.retrieveCategorieById(1));
        // sproduit.afficherProduit();
        
        // Ajout de deux produits 
       // Produit p = new Produit("HyperX Cloud II", "0x001", 1, 0, " Un casque pour les Gamers pro.", " En Stock ", 365); //en TND
       // Produit p1 = new Produit("Razer Krake Pro v7", "0x001", 2, 600, " Un casque pour les Gamers pro.", " En Stock ", 2);
        // Produit p2 = new Produit("Chaise v1", "0X000", 2, 60, "Chaise pour les Gamers", " En stock", 4);    // produit avec id
       // Test 1 : (without joints)  Produit p2 = new Produit(38, "Chaise", ")Xààç", 3, 660, "chaise pour les gamer ", " En stock", 6);
        //test modification
        //Produit p2 = new Produit("Chaise v1 modifiée", "0X000", 2, 60, "Chaise pour les Gamers", " En stock", 4);    // produit avec id
        // (Test1 : Without joints) Produit p3 = new Produit(7, " Bureaux TX510", "0X0070", 3, 250, " Un bureau pour les gamers ", "  Out of stock ", 4);
        // Ajout des produits 
        //sproduit.ajouterProduit(p2);
       //  sproduit.modifierProduit(p2);
                                            // TEST AJOUT //
       // sproduit.supprimerProduit(p2);
        //sproduit.ajouterProduit(p2);
        // sproduit.ajouterProduit(p3);
                                            // TEST MODIFICATION //
        //sproduit.modifierProduit(p2);
        //sproduit.supprimerProduit(p3);
        // sproduit.ajouterProduit(p);
        // Modification des produits
        //sproduit.modifierProduit(p1);

        // Suppression des produits
        //Produit p2 = new Produit(41, "Chaise", "0x005", 2, 100, "Chaise pour Gamers", "Out of stock", 1);
        //sproduit.modifierProduit(p2);
        // sproduit.ajouterProduit(p2);
        // sproduit.supprimerProduit(p2);

        // Affichage ajout produit
       // System.out.println(sproduit.afficherProduit());

        // Ajout d'une catégorie
       // Categorie c = new Categorie("Chaises");
            // Test ajout categorie
            
     //  Categorie c1 = new Categorie(16, "Souris");
      //  Categorie c4 = new Categorie(17, "Bureau 1");
      
       //  scactegorie.ajouterCategorie(c4);
         
        // scategorie.supprimerCategorie(c4); //
        
        //scactegorie.ajouterCategorie(c1);
        //scactegorie.modifierCategorie(c1);
        //scactegorie.supprimerCategorie(c1);
        
        //******************************************* LIVRAISON ET RECLAMATION *******************************************  //
        Livraison l = new Livraison(3, 4, date, "livrée");
        Reclamation r = new Reclamation(1, 3, "déçu");
        
        // AJOUT et affichage livraison
        //System.out.println(slivraison.ajouterLivraison(l));
        //System.out.println("les livraison:" +slivraison.afficherLivraison());
        
        //AJOUT et affichage reclamation
        //System.out.println(sr.ajouterReclamation(r));
        //System.out.println(sr.afficherReclamation());
  
        
        // MODIFIER
        Livraison l1 = new Livraison(9, 3, 4, date, "en cours");
        //System.out.println(slivraison.modifierLivraison(l1));
        
        Reclamation r1 = new Reclamation(5, 1, 4, "satisfait");
         //System.out.println(sr.modifierReclamation(r1));

        //SUPPRIMER
     
        //System.out.println(slivraison.supprimerLivraison(l1));
        System.out.println(sr.supprimerReclamation(r1));
        
        //  *******************************************  Frorum *******************************************   //
        
        
        
        
        Post post1 = new Post("sujet3", "ACCESOIRES ET JEUX VIDEOS", date, 1, 2);
        Post post2 = new Post("Sujet2", " ETCETCETCJEUX VIDEOS", date, 3, 4);
        //Comment
        Comment comment1 = new Comment("firstcomment", "comm", 12, 1);
        Comment comment2 = new Comment("secondcomment", "commtwo", 10, 2);
        
        // AJOUT post
        //   System.out.println("POST added!");
        // System.out.println(spost.ajouterPost(post1 ));
        // System.out.println(spost.ajouterPost(post2));
        //afficherpost
        //System.out.println(spost.afficherPost());
            //ajoutComment
        // System.out.println("COMMENT added!");
        //System.out.println(scomment.ajouterComment(comment1));
        //affichercomment
        // System.out.println(scomment.afficherComment());

        // MODIFIER
        Post post3 = new Post(35, "sujet2", "videogame", date, 2, 4);
        //System.out.println("POST Updated\n");
        //System.out.println(spost.modifierPost(post3));
        //updatecomment

        Comment comment3 = new Comment(14, "THIRDcomment", "HAHAAHA", 13, 1);
        //System.out.println("COMMENT Updated\n");
        // System.out.println(scomment.modifierComment(comment3));

        //deletepost
        // System.out.println("POST Deleted");
      //   System.out.println(spost.supprimerPost(post3));
        //deleteComment
        // System.out.println("COMMENT Deleted");
        //System.out.println(scomment.supprimerComment(comment3));
        
        //  *******************************************  COMMANDE ET PANIER *******************************************  //
        Commande commande1 = new Commande(2, 5, 20, date);
        Panier panier1 = new Panier(4, 7);
        Panier_elem e = new Panier_elem(1, 1, 4);
        Commande_elem e1 = new Commande_elem(2, 3, 3, 3);

        //System.err.println(spanier2.afficherPanier(1));
        //System.err.println(spanier3.AfficherCommande(5));
        //System.err.println(scommande.AfficherCommande());
        //AJOUT
        //spanier.ajouterPanier(panier1);
        //spanier2.AjouterElementPanier(e);
        //spanier3.ajouterElementCommande(e1);
        //scommande.ajouterCommande(commande1);
        //spanier3.ajouterElementCommande(e1);

        ///Modification
        //spanier3.modifierElementCommande(e1);
        scommande.modifierCommande(commande1);
        //SUPPRESSION
        //spanier.supprimerPanier(panier1);
        // spanier3.supprimerElementCommande(e1);
        //  spanier2.supprimerElementPanier(e);
          scommande.SupprimerCommande(commande1);
    }

}
