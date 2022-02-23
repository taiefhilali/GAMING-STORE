/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICommande;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Commande;
import utils.MaConnexion;

/**
 *
 * @author ASUS
 */
public class ServiceCommande implements ICommande{

      //var
    Connection cnx = MaConnexion.getInstance().getCnx();
    
    //Ajouter Commande
    @Override
    public void ajouterCommande(Commande c) {
         String request = "INSERT INTO `commande`(`id_client`, `prix_livraison`, `date_commande`) VALUES ("+c.getId_client()+","+c.getPrix_livraison()+",'"+c.getDate_commande()+"')";
        try {
            Statement st = cnx.createStatement();
           st.executeUpdate(request);
            System.out.println("Commande Ajouté avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Supprimer Commande
    @Override
    public void SupprimerCommande(Commande c) {
        String request ="DELETE FROM `commande` WHERE id_commande ="+c.getId_commande()+"";
         try {
            Statement st = cnx.createStatement();
           st.executeUpdate(request);
            System.out.println("Commande Supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Modifier Commande
    @Override
    public void modifierCommande(Commande c) {
        String request ="UPDATE `commande` SET `id_commande`="+c.getId_commande()+",`id_client`="+c.getId_client()+",`prix_livraison`="+c.getPrix_livraison()+",`date_commande`='"+c.getDate_commande()+"' WHERE id_commande ="+c.getId_commande()+"";
      try {
            Statement st = cnx.createStatement();
           st.executeUpdate(request);
            System.out.println("Commande Modifié avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Afficher les Commandes
    @Override
    public List<Commande> AfficherCommande() {
        List<Commande> commandes = new ArrayList<Commande>();
        
         String req="SELECT * FROM commande";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                commandes.add(new Commande(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }
    
}
