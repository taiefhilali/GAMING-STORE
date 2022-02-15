package interfaces;

import java.util.List;
import model.Produit;

/**
 *
 * @author Iskander
 */
public interface iProduit {

    public void ajouterProduit(Produit p);

    public void modifierProduit(Produit p);

    public void supprimerProduit(Produit p);

    public List<Produit> afficherProduit();

}
