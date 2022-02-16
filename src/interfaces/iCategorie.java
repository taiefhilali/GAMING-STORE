package interfaces;

import java.util.List;
import model.Categorie;

/**
 *
 * @author Iskander
 */
public interface iCategorie {

    public void ajouterCategorie(Categorie c);

    public void modifierCategorie(Categorie c);

    public void supprimerCategorie(Categorie c);

    public List<Categorie> afficherCategorie();

}
