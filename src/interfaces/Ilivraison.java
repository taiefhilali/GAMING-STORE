/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Livraison;

/**
 *
 * @author User
 */
public interface Ilivraison {
    public interface ILivraison {
    
   //ajouter
    public boolean ajouterLivraison(Livraison l);
    
    //lister
    public List<Livraison> afficherLivraison(Livraison l);
    
    public boolean modifierLivraison(Livraison l);
    
    public boolean supprimerPersonne(Livraison l);
    }
}
