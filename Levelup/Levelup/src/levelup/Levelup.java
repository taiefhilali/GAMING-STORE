/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelup;

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
import service.ServiceCategorie;
import service.ServiceProduit;
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

        String str = "2015-03-31";
        Date date = Date.valueOf(str);
        String adr = "Ben arouse/Mourouj 1/Rue 234/2074";

        //  ******************************************* SERVICES *******************************************  //
        ServiceUser sp = new ServiceUser();
        ServiceClient sc = new ServiceClient();
        ServiceFournisseur sf = new ServiceFournisseur();
        ServiceLivreur sl = new ServiceLivreur();
        ServiceAdministrateur sa = new ServiceAdministrateur();
        ServiceProduit sproduit = new ServiceProduit();
        ServiceFacture sfacture = new ServiceFacture();
        ServiceStock ss = new ServiceStock();
        ServiceCategorie scactegorie = new ServiceCategorie();
        Servicepost spost = new Servicepost();
        Servicecomment scomment = new Servicecomment();
        ServiceCommande scommande = new ServiceCommande();
        servicePanier spanier = new servicePanier();
        ServicePanier_elem spanier2 = new ServicePanier_elem();
        ServiceCommande_elem spanier3 = new ServiceCommande_elem();
        ServiceLivraison slivraison = new ServiceLivraison();
        ServiceReclamation sr = new ServiceReclamation();

          //  ******************************************* USERS ******************************************* //
        
                //Affichage des utilisateurs
        
        
        //Ajout des utilisateurs
        // Client   
//        User user1 = new User("beldi.mariem@gmail.com", "test", "client", "beldi", "mariem", adr, "26386558",date);
//        Client c = new Client("femme", (int)sp.ajouterPersonne(user1));
//        sc.ajouterPersonne(c);
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
        Client cu = new Client("femme", 39, "beldi.mariem@gmail.com", "test", "client", "beldi", "mariem", adr, "26386558", date);
//        System.out.println(sp.modifierPersonne(c));
//        System.out.println(sc.modifierPersonne(c));
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
//        System.out.println(sp.supprimerPersonne(cu));
//        System.out.println(sc.supprimerPersonne(cu));
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
        Stock s = new Stock("accessoire", 50, "en stock");
        Facture f = new Facture(date, "90.990dt");
        //Ajout  stock et facture
        //ss.ajouterStock(s);
        //sfacture.ajouterFacture(f);
        //Affichage stocks et factures
       // System.out.println(ss.afficherStock());
        //System.out.println(sfacture.afficherFacture());
        //Modification stock et facture
        Stock s1 = new Stock(55, "ordinateur", 220, "en stock");
        //System.out.println(ss.modifierStock(s1));
        Facture f1 = new Facture(25, date, "60.650dt");
        //System.out.println(sfacture.modifierFacture(f1));

        //Suppression stock et facture
        //System.out.println(ss.supprimerStock(s1));
        //System.out.println(sfacture.supprimerFacture(f1));
        
        //  *******************************************  CATEGORIE ET PRODUIT *******************************************   //    
        // Ajout de deux produits 
        Produit p = new Produit("HyperX Cloud II", "0x001", 1, 0, " Un casque pour les Gamers pro.", " En Stock ", 365); //en TND
        Produit p1 = new Produit("Razer Krake Pro v7", "0x001", 2, 600, " Un casque pour les Gamers pro.", " En Stock ", 2);
        // Ajout des produits 
       // sproduit.ajouterProduit(p1);
        // sproduit.ajouterProduit(p);
        // Modification des produits
        //sproduit.modifierProduit(p1);

        // Suppression des produits
        Produit p2 = new Produit(41, "Chaise", "0x005", 2, 100, "Chaise pour Gamers", "Out of stock", 1);
        //sproduit.modifierProduit(p2);
        // sproduit.ajouterProduit(p2);
        // sproduit.supprimerProduit(p2);

        // Affichage ajout produit
       // System.out.println(sproduit.afficherProduit());

        // Ajout d'une catégorie
        Categorie c = new Categorie("Chaises");
        Categorie c1 = new Categorie(16, "Souris");

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
        Livraison l1 = new Livraison(7, 3, 4, date, "confirmée");
        //System.out.println(slivraison.modifierLivraison(l1));
        
        Reclamation r1 = new Reclamation(1, 1, 4, "déçu");
         //System.out.println(sr.modifierReclamation(r1));

        //SUPPRIMER
     
        //System.out.println(slivraison.supprimerLivraison(l1));
        // System.out.println(sr.supprimerReclamation(r1));
        
        //  *******************************************  Frorum *******************************************   //
        Post post1 = new Post("GAMING", "ACCESOIRES ET JEUX VIDEOS", date, 1, 2);
        Post post2 = new Post("Sujet", " ETCETCETCJEUX VIDEOS", date, 3, 4);
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
        Post p3 = new Post(33, "sujet3", "videogame", date, 2, 4);
        //System.out.println("POST Updated\n");
        //System.out.println(spost.modifierPost(p3));
        //updatecomment

        Comment c3 = new Comment(14, "THIRDcomment", "HAHAAHA", 13, 1);
        //System.out.println("COMMENT Updated\n");
        // System.out.println(scomment.modifierComment(c3));

        //deletepost
        // System.out.println("POST Deleted");
        // System.out.println(spost.supprimerPost(p3));
        //deleteComment
        // System.out.println("COMMENT Deleted");
        //System.out.println(scomment.supprimerComment(c3));
        
        //  *******************************************  COMMANDE ET PANIER *******************************************  //
        Commande commande1 = new Commande(1, 4, 19, date);
        Panier panier1 = new Panier(2, 3);
        Panier_elem e = new Panier_elem(1, 1, 4);
        Commande_elem e1 = new Commande_elem(2, 3, 3, 3);

        //System.err.println(spanier2.afficherPanier(1));
        //System.err.println(spanier3.AfficherCommande(5));
        //System.err.println(scommande.AfficherCommande());
        //AJOUT
//        spanier.ajouterPanier(panier1);
//        spanier2.AjouterElementPanier(e);
//        spanier3.ajouterElementCommande(e1);
//        scommande.ajouterCommande(commande1);
        //spanier3.ajouterElementCommande(e1);

        ///Modification
        //spanier3.modifierElementCommande(e1);
        //scommande.modifierCommande(commande1);
        //SUPPRESSION
        //spanier.supprimerPanier(panier1);
        // spanier3.supprimerElementCommande(e1);
        //  spanier2.supprimerElementPanier(e);
        //  scommande.SupprimerCommande(commande1);
    }

}
