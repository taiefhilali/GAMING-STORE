/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelup;

import java.sql.Date;
import model.Commande;
import model.Commande_elem;
import model.Panier;
import model.Panier_elem;
import service.ServiceCommande;
import service.ServiceCommande_elem;
import service.ServicePanier_elem;
import service.servicePanier;

/**
 *
 * @author ASUS
 */
public class Levelup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String str="2011-02-28";  
        Date date=Date.valueOf(str);
        
        Commande c = new Commande(2,3,15,date);
        
       Panier p = new Panier(1,3);
       Panier_elem e = new Panier_elem(1,1,4);
       Commande_elem e1 = new Commande_elem(5,5,5,4);
       
       ServiceCommande p4 = new ServiceCommande();
       servicePanier p1 = new servicePanier();
       ServicePanier_elem p2 = new  ServicePanier_elem();
       ServiceCommande_elem p3 = new ServiceCommande_elem();
       
       //p1.ajouterPanier(p);
       //p1.supprimerPanier(p);
      //p2.AjouterElementPanier(e);
      //p3.ajouterElementCommande(e1);
       //p3.supprimerElementCommande(e1);
      // p2.supprimerElementPanier(e);
        //System.err.println(p2.afficherPanier(1));
        //System.err.println(p3.AfficherCommande(5));
        //p3.modifierElementCommande(e1);
        //p4.ajouterCommande(c);
        //p4.SupprimerCommande(c);
        //p4.modifierCommande(c);
        //System.err.println(p4.AfficherCommande());
    }
    
}
