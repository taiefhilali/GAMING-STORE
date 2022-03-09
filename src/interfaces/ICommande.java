/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Date;
import java.util.List;
import models.Commande;
import models.Commande_elem;
import models.Livraison;
import models.Stock;

/**
 *
 * @author ASUS
 */
public interface ICommande {
    
    public void ajouterCommande(Commande c);
    
    public Stock afficherStock(int id);
    
    public List<Livraison> AfficherCommandeIdLiv(int id) ;
    
    public void SupprimerCommande(Commande c);
    
    public void modifierCommande(int id, double prix_produits , double prix_Livraison , double prix_total);
    
    public List<Commande> AfficherCommande();
    
    public List<Commande> AfficherCommandeId(int id);
    
    public List<Commande> Tri(List<Commande> l);
    
    public List<Commande> TrierCommandesParDate();
    
    public Commande GetCommande(int id , Date d);
    
    public List<Commande> AfficherCommandeDate(Date d);
}
