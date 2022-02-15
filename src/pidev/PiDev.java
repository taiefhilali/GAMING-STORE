/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import model.Facture;
import model.Stock;
import service.ServiceFacture;
import service.ServiceStock;
import java.sql.Date;

/**
 *
 * @author user
 */
public class PiDev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Stock s = new Stock (55,"accessoire",50,"en stock");
        ServiceStock ss =new ServiceStock();
        ss.ajouterStock(s);
        
  //String str="2021-07-03";  
        //Date date=Date.valueOf(str);
       // Facture f = new Facture(25,date,"90.990dt");
       // ServiceFacture sf =new ServiceFacture();
        //sf.ajouterFacture(f);
        
        
       // System.out.println(ss.afficherStock());
       // System.out.println(sf.afficherFacture());
         
      // Stock s1 = new Stock(11,"pc",220,"en stock");
       // System.out.println("Update\n");
        //System.out.println(ss.modifierStock(s1));
        //Facture f1 = new Facture(0,"22/08/2002",21);
        //System.out.println("Update\n");
        //System.out.println(sf.modifierFacture(f1));
         
        //System.out.println("Delete");
        //System.out.println(ss.supprimerStock(s1));
        //System.out.println("Delete");
        //System.out.println(sf.supprimerFacture(f1));
    }
    
}
