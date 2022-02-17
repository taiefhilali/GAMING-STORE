/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Reclamation;

/**
 *
 * @author User
 */
public interface Ireclamation {
    public interface IReclamation{
    
   //ajouter
    public boolean ajouterLivraison(Reclamation r);
    
    //lister
    public List<Reclamation> afficherLivraison(Reclamation r);
    
    public boolean modifierLivraison(Reclamation r);
    
    public boolean supprimerPersonne(Reclamation r);
    }
}

