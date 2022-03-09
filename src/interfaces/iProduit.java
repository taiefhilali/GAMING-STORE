package interfaces;

import java.util.List;
import models.Produit;

/**
 *
 * @author Iskander
 */
public interface Iproduit {

    public String ajouterProduit(Produit p);

    public void modifierProduit(Produit p);

    public void supprimerProduit(Produit p);

    public List<Produit> afficherProduit();

    public List<Produit> chercherProduitDynamiquement(String s, List<Produit> l);

    public void TrierProduitParPrix();

//  Pour calculer la promotion :   
    public Double calculerPromotion(Produit p);

    public void calculatePromotiononAdd();

    public Produit retrieveProductById(int id);

    public double returnPrixfinal(int id);

}
