package services;

import interfaces.Iproduit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.Categorie;
import models.User;
import models.Produit;
import utils.MaConnexion;

/**
 *
 * @author Iskander
 */
public class ServiceProduit implements Iproduit {

    // Variable 1
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterProduit(Produit p) {

        String request = "INSERT INTO `produit`(`nom`, `reference`, `id_categorie`, `prix`, `description`,`id_user`) VALUES ('" + p.getNom() + "','" + p.getReference() + "','" + p.getCategorie().getId_categorie() + "','" + p.getPrix() + "','" + p.getDescription() + "','" + p.getUser().getId() + "')";
        try {
            // Définier une var Statement responsable de la connexion
            Statement st = cnx.createStatement();
            //Execution da la fonction statement
            //execute update pour l'éxecution même au cours du modification i lfait maj
            st.executeUpdate(request);
            System.out.println(" * Produit ajoutée avec succés * ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Modification 
    @Override
    public void modifierProduit(Produit p) {
        String request = "UPDATE `produit` SET `nom`='" + p.getNom() + "',`reference`='" + p.getReference() + "',`id_categorie`=" + p.getCategorie().getId_categorie() + ",`prix`=" + p.getPrix() + ",`description`='" + p.getDescription() + "',`id_user`=" + p.getUser().getId() + " WHERE id_produit =" + p.getId_produit() + " ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println(" * Produit modifié avec succés * ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // Crud suppression

    @Override
    public void supprimerProduit(Produit p) {
        String request = "DELETE FROM `produit` WHERE `id_produit` = " + p.getId_produit() + " ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println(" * Produit supprimée avec succés * ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Affichage
    @Override
    public List<Produit> afficherProduit() {
        List<Produit> Produits = new ArrayList<Produit>();
        String query = "SELECT * FROM produit p inner join categorie c on p.id_categorie  = c.id_categorie";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                        Produits.add(new Produit(rs.getInt("id_produit"),
                        rs.getString(2),
                        rs.getString(3),
                        new Categorie(rs.getInt("c.id_categorie"), rs.getString("c.nom_categorie")),
                        rs.getDouble(5),
                        rs.getString(6),
                        new User()
                ));
                //   String request2 = "SELECT * FROM `categorie` WHERE `id_categorie`= " +rs.getInt("id_categorie") + " " ;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Produits;
//    }    // Top 10 des produits selon kol categories ( selon table commande) 
    }

}
