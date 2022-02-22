package services;

import interfaces.Iproduit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

        String request = "INSERT INTO `produit`(`nom`, `reference`, `id_categorie`, `prix`, `description`, `etat`, `id_user`) VALUES ('" + p.getNom() + "','" + p.getReference() + "','" + p.getId_categorie() + "','" + p.getPrix() + "','" + p.getDescription() + "','" + p.getEtat() + "','" + p.getUser().getId()+ "')";
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
        String request = "UPDATE `produit` SET `nom`='" + p.getNom() + "',`reference`='" + p.getReference() + "',`id_categorie`=" + p.getId_categorie() + ",`prix`=" + p.getPrix() + ",`description`='" + p.getDescription() + "',`etat`='" + p.getEtat() + "',`id_user`="  + p.getUser().getId() + " WHERE id =" + p.getId() + " ";
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
        String request = "DELETE FROM `produit` WHERE `id` = " + p.getId() + " ";
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
        String query = "SELECT * FROM produit";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                
               // Produits.add(new Produit(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Produits;
    }
}
