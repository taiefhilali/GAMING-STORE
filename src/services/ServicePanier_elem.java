/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IPanier_elem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Commande_elem;
import models.Panier;
import models.Panier_elem;
import models.Produit;
import utils.MaConnexion;

/**
 *
 * @author ASUS
 */
public class ServicePanier_elem implements IPanier_elem{
    
    //var
    Connection cnx = MaConnexion.getInstance().getCnx();
    
    
    //Ajouter element de panier
    @Override
    public void AjouterElementPanier(Panier_elem p) {
        String request ="INSERT INTO `panier_elem`(`id_panier`, `id`) VALUES ("+p.getPan().getId_panier()+","+p.getProduit().getId()+")";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Element de Panier Ajouter avec succés");
        } catch (SQLException ex) {
            System.out.println("L'ajout est echouer");      
        }
        
    }

    @Override
    public void AjouterElementPanierQ(Panier_elem p) {
        String request ="INSERT INTO `panier_elem`(`id_panier`, `id`, `Quantite`) VALUES ("+p.getPan().getId_panier()+","+p.getProduit().getId()+","+p.getQuantite()+")";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Element de Panier Ajouter avec succés");
        } catch (SQLException ex) {
            System.out.println("L'ajout est echouer");      
        }
        
    }

    
    //Supprimer element de panier 
    @Override
    public void supprimerElementPanier(Panier_elem p) {
        String request ="DELETE FROM `panier_elem` WHERE id ="+p.getProduit().getId()+"";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Element de Panier Supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

     @Override
    public void supprimerElementPanier(int id) {
        String request ="DELETE FROM `panier_elem` WHERE id_elem ="+id+"";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Element de Panier Supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //Afficher les elements de panier
    @Override
    public List<Panier_elem> afficherPanier(int id_panier) {
         
       List<Panier_elem> Panier_elems = new ArrayList<Panier_elem>();
       String req="SELECT * FROM panier_elem pe inner join panier p on pe.id_panier = p.id_panier inner join produit p1 on pe.id = p1.id_produit WHERE pe.id_panier ="+id_panier+"";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
             Panier_elems.add(new Panier_elem(rs.getInt("pe.id_elem"),new Panier(rs.getInt("p.id_panier"))
             ,new Produit(rs.getInt("p1.id_produit"),rs.getString("p1.nom"),rs.getDouble("p1.prix"),rs.getString("p1.description")),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Panier_elems;

    }
    
    
    
     @Override
    public Panier_elem getElement(int id,int id_pr) {
         
       Panier_elem Prod = new Panier_elem();
       String req="SELECT * FROM panier_elem pe inner join panier p on pe.id_panier = p.id_panier inner join produit p1 on pe.id = p1.id_produit WHERE pe.id_panier ="+id+" And pe.id ="+id_pr+"";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                Prod.setId_elem( rs.getInt("pe.id_elem"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Prod;

    }
}
