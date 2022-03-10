package services;

import interfaces.Icategorie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Categorie;
import utils.MaConnexion;

/**
 *
 * @author Iskander
 */
public class ServiceCategorie implements Icategorie {

    // var
    String nomcat;

    // Variable 1
    Connection cnx = MaConnexion.getInstance().getCnx();

    // Crud ajout catégorie
    @Override
    public void ajouterCategorie(Categorie c) {
        String request = "INSERT INTO `categorie`(`nom_categorie`) VALUES ('" + c.getNom_categorie() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println(" * Categorie ajoutée avec succés * ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Modification
    @Override
    public void modifierCategorie(Categorie c) {
        String request = "UPDATE `categorie` SET `nom_categorie`='" + c.getNom_categorie() + "' WHERE id_categorie=" + c.getId_categorie() + " ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println(" * Catégorie modifiée avec succés * ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Crud Suppression
    @Override
    public void supprimerCategorie(Categorie c) {
        String request = "DELETE FROM `categorie` WHERE `id_categorie` = " + c.getId_categorie() + " ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println(" * Catégorie supprimée avec succés * ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Affichage
    @Override
    public List<Categorie> afficherCategorie() {
        List<Categorie> Categories = new ArrayList<Categorie>();
        String query = "SELECT * FROM categorie";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Categories.add(new Categorie(rs.getInt("id_categorie"), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Categories;
    }

    @Override
    public String afficherCategorieParNom() {
        String query = "SELECT * FROM categorie";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                nomcat = rs.getString("nom_categorie");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nomcat;
    }

    @Override
    public Categorie retrieveCategorieById(int id) {
        Categorie cat = new Categorie();
        String query = "SELECT * FROM `categorie` WHERE `id_categorie` = " + id + " ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cat.setId_categorie(rs.getInt(1));
                cat.setNom_categorie(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cat;
    }

    @Override
    public Categorie retrieveCategorieByNom(String s) {
        Categorie cat = new Categorie();
        String query = "SELECT * FROM `categorie` WHERE `nom_categorie` = '" + s + "' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cat.setId_categorie(rs.getInt(1));
                cat.setNom_categorie(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cat;
    }

    @Override
    public int validerCategorie(String s) {
        Categorie cat = new Categorie();
        String query = "SELECT * FROM `categorie` WHERE `nom_categorie` = '" + s + "' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
