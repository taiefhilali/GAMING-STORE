/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.ICommande_elem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Commande;
import model.Commande_elem;
import util.MaConnexion;

/**
 *
 * @author ASUS
 */
public class ServiceCommande_elem implements ICommande_elem{

    //var
    Connection cnx = MaConnexion.getInstance().getCnx();
    
    
    //Ajouter element de commande
    @Override
    public void ajouterElementCommande(Commande_elem e) {
        String req = "INSERT INTO `detail_commande`(`id_produit`, `id_commande`, `Quantite`) VALUES ("+e.getId_produit()+","+e.getId_commande()+","+e.getQuantite()+")";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Element de commande Ajouter avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    //Supprimer element de commande
    @Override
    public void supprimerElementCommande(Commande_elem e) {
            String req = "DELETE FROM `detail_commande` WHERE id_commande = "+e.getId_commande()+" ";

        try {
            Statement st = cnx.createStatement();
           st.executeUpdate(req); 
           System.out.println("Element de Commande Supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Modifier element de panier
    @Override
    public void modifierElementCommande(Commande_elem e) {
        String req = "UPDATE `detail_commande` SET `id_elemC`="+e.getId_elemC()+",`id_produit`="+e.getId_produit()+",`id_commande`="+e.getId_commande()+",`Quantite`="+e.getQuantite()+" WHERE id_commande ="+e.getId_commande()+" And id_produit ="+e.getId_produit()+"";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Element de Commande Modifié avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //Afficher les elements de commande
    @Override
    public List<Commande_elem> AfficherCommande(int id) {
        
        List<Commande_elem> commandes_elem = new ArrayList<Commande_elem>();
        
         String req="SELECT * FROM detail_commande WHERE id_commande ="+id+"";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                commandes_elem.add(new Commande_elem(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commandes_elem;

    }
    
}
