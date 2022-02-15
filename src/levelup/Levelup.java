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
        //Date d= new Date(1377249026);  

        //services users
        ServiceUser sp = new ServiceUser();
        ServiceClient sc = new ServiceClient();
        ServiceFournisseur sf = new ServiceFournisseur();
        ServiceLivreur sl = new ServiceLivreur();
        ServiceAdministrateur sa = new ServiceAdministrateur();

        String str="2015-03-31";  
        Date date=Date.valueOf(str);
        //Ajout des utilisateurs
       
        String adr="Ben arouse/Mourouj 1/Rue 234/2074";
        // Client   
//        User user1 = new User("beldi.mariem@gmail.com", "password", "client", "beldi", "mariem", adr, "26386558",date);
//        Client c = new Client("femme", (int)sp.ajouterPersonne(user1));
//        sc.ajouterPersonne(c);
        
         // Fournisseur   
//        User user2 = new User("beldi.mariem@gmail.com", "password", "fournisseur", "beldi", "mariem", adr, "26386558",date);
//        Fournisseur f =new Fournisseur("07227308","Arvea", (int)sp.ajouterPersonne(user2));
//        sf.ajouterPersonne(f);
        
          // Livreur   
//           User user3 = new User("beldi.mariem@gmail.com", "password", "livreur", "beldi", "mariem", adr, "26386558",date);
//           Livreur l =new Livreur("00000","07227308", (int)sp.ajouterPersonne(user3));
//           sl.ajouterPersonne(l);
        
          // Administrateur   
//        User user4 = new User("beldi.mariem@gmail.com", "password", "administrateur", "beldi", "mariem", adr, "26386558", date);
//       Administrateur a = new Administrateur("07227308", (int) sp.ajouterPersonne(user4));
//       sa.ajouterPersonne(a);
        
        //Affichage des utilisateurs
        
        // AFFICHER Administrateur
        System.out.println(sa.afficherPersonnes());
        // AFFICHER Fournisseur
        System.out.println(sf.afficherPersonnes());
        // AFFICHER Livreur
        System.out.println(sl.afficherPersonnes());
        // AFFICHER Client
        System.out.println(sc.afficherPersonnes());
        
        
       //Modification des utilisateurs
        
        
        // Client 
         Client cu = new Client("femme", 39,"beldi.mariem@gmail.com", "test", "client", "beldi", "mariem",adr, "26386558",date);
//        System.out.println(sp.modifierPersonne(c));
//        System.out.println(sc.modifierPersonne(c));
         //Fournisseur
        Fournisseur fu = new Fournisseur("08227308", "Arvea", 40,"beldi.mariem@gmail.com", "test", "fournissuer", "beldi", "mariem", adr, "26386558",date);
//        System.out.println(sp.modifierPersonne(f));
//        System.out.println(sf.modifierPersonne(f));
         //Livreur
        Livreur lu = new Livreur("0234", "09227308", 41,"beldi.mariem@gmail.com", "test", "livreur", "beldi", "mariem", adr, "26386558",date);
//        System.out.println(sp.modifierPersonne(l));
//        System.out.println(sl.modifierPersonne(l));
        //Administrateur
        Administrateur au = new Administrateur("06227308",42,"beldi.mariem@gmail.com", "test", "administrateur", "beldi", "mariem", adr, "26386558",date);
//        System.out.println(sp.modifierPersonne(a));
//        System.out.println(sa.modifierPersonne(a));
        
        
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
//        
        
        
        
        
        
        
    }

}
