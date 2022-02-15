/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelup;

import java.sql.Connection;
import models.Livraison;
import models.Reclamation;
import services.ServiceLivraison;
import services.ServiceReclamation;
import utils.Maconnexion;
import java.sql.Date;  


/**
 *
 * @author 21694
 */
public class Levelup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Connection cnx = Maconnexion.getInstance().getCnx();
       
        String str="2015-03-31";  
        Date date=Date.valueOf(str);

       Livraison l = new Livraison(3, 4, date , "livré");
       Reclamation r = new Reclamation(1, 3, "déçu");

        // SERVICE
        ServiceLivraison sp = new ServiceLivraison();
        ServiceReclamation sr = new ServiceReclamation();
      

        // AJOUT 
        //System.out.println(sp.ajouterLivraison(l));
         //System.out.println(sr.ajouterReclamation(r));
        
        // AFFICHER 
        //System.out.println(sp.afficherLivraison());
        //System.out.println(sr.afficherReclamation());

        // MODIFIER
      Livraison l1= new Livraison(4,3, 4, date , "livrée");
       
        //System.out.println(sp.modifierLivraison(l1));
         Reclamation r1 = new Reclamation(1,1, 4, "déçu");
         //System.out.println(sr.modifierReclamation(r1));
         
        //SUPPRIMER
        //System.out.println("Delete");
        //System.out.println(sp.supprimerLivraison(l1));
       // System.out.println(sr.supprimerReclamation(r1));
    }
    
}
