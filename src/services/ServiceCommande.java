/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICommande;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.Client;
import models.Commande;
import models.Livraison;
import models.Stock;
import utils.MaConnexion;

/**
 *
 * @author ASUS
 */
public class ServiceCommande implements ICommande{

      //var
    Connection cnx = MaConnexion.getInstance().getCnx();
    
     @Override
    public Stock afficherStock(int id) {
          Stock stocks =new Stock();
     String query = "SELECT * FROM stock where id = "+id+"";
      Statement st = null;
         try {
             st=cnx.createStatement();
             ResultSet rs= st.executeQuery(query);
             while (rs.next()){
           stocks.setId(rs.getInt(1)); 
           stocks.setNom(rs.getString(2));
           stocks.setEtat(rs.getString(4));
           stocks.setQuantite(rs.getInt(3));
             }
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
             return stocks;
    }
    
    //Ajouter Commande
    @Override
    public void ajouterCommande(Commande c) {
         String request = "INSERT INTO `commande`(`id_user`, `prix_livraison`, `date_commande` , `prix_produits` , `prix_total`) VALUES ("+c.getClient().getId()+","+c.getPrix_livraison()+",'"+c.getDate_commande()+"',"+c.getPrix_produits()+","+c.getPrix_total()+")";
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
    public void modifierCommande(int id ,double prix_produits , double prix_Livraison , double prix_total) {
        String request ="UPDATE `commande` SET `prix_livraison`="+prix_Livraison+" ,`prix_produits`="+prix_produits+", `prix_total`= "+prix_total+"  WHERE id_commande ="+id+"";
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
         String req="SELECT * FROM commande c inner join user u on c.id_user = u.id_user inner join client cl on u.id_user = cl.id_user";        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
         
               commandes.add(new Commande(rs.getInt("c.id_commande"),new Client(rs.getString("cl.sexe"),rs.getInt("u.id_user"),rs.getString("u.email"),rs.getString("u.password"),rs.getString("u.role"),
                 rs.getString("u.nom"),rs.getString("u.prenom"),rs.getString("u.adresse"),rs.getString("u.tel"),rs.getDate("u.dns")),rs.getDouble("c.prix_livraison"),rs.getDouble("c.prix_produits"),rs.getDouble("c.prix_total"),rs.getDate("c.date_commande")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }
    
    
    //Afficher les commandes par id_client
    @Override
    public List<Commande> AfficherCommandeId(int id) {
        List<Commande> commandes = new ArrayList<Commande>();
         String req="SELECT * FROM commande c inner join user u on c.id_user = u.id_user inner join client cl on u.id_user = cl.id_user where c.id_user ="+id+"";        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
         
               commandes.add(new Commande(rs.getInt("c.id_commande"),new Client(rs.getString("cl.sexe"),rs.getInt("u.id_user"),rs.getString("u.email"),rs.getString("u.password"),rs.getString("u.role"),
                 rs.getString("u.nom"),rs.getString("u.prenom"),rs.getString("u.adresse"),rs.getString("u.tel"),rs.getDate("u.dns")),rs.getInt("c.prix_livraison"),rs.getDouble("c.prix_produits"),rs.getDouble("c.prix_total"),rs.getDate("c.date_commande")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

     //Afficher les commandes par id_client
    @Override
    public List<Commande> AfficherCommandeDate(Date d) {
        List<Commande> commandes = new ArrayList<Commande>();
         String req="SELECT * FROM commande c inner join user u on c.id_user = u.id_user inner join client cl on u.id_user = cl.id_user where c.date_commande ="+d+"";        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
         
               commandes.add(new Commande(rs.getInt("c.id_commande"),new Client(rs.getString("cl.sexe"),rs.getInt("u.id_user"),rs.getString("u.email"),rs.getString("u.password"),rs.getString("u.role"),
                 rs.getString("u.nom"),rs.getString("u.prenom"),rs.getString("u.adresse"),rs.getString("u.tel"),rs.getDate("u.dns")),rs.getInt("c.prix_livraison"),rs.getDouble("c.prix_produits"),rs.getDouble("c.prix_total"),rs.getDate("c.date_commande")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }
    
    
     @Override
    public Commande GetCommande(int id , Date d) {
        Commande commande = new Commande();
         String req="SELECT * FROM commande c inner join user u on c.id_user = u.id_user inner join client cl on u.id_user = cl.id_user where c.id_user ="+id+" And c.date_commande = '"+d+"'";        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                commande.setId_commande(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }
       //Afficher les commandes par id_client
    @Override
    public List<Livraison> AfficherCommandeIdLiv(int id) {
        List<Livraison> commandes = new ArrayList<Livraison>();
         String req="SELECT * FROM livraison where id_commande ="+id+"";        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                commandes.add(new Livraison(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }
    
    /************Tri par date*********/
    @Override
    public List<Commande> TrierCommandesParDate() {
        List<Commande> commandes = new ArrayList<Commande>();
         String req="SELECT * FROM commande c inner join user u on c.id_user = u.id_user inner join client cl on u.id_user = cl.id_user";        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
         
               commandes.add(new Commande(rs.getInt("c.id_commande"),new Client(rs.getString("cl.sexe"),rs.getInt("u.id_user"),rs.getString("u.email"),rs.getString("u.password"),rs.getString("u.role"),
                 rs.getString("u.nom"),rs.getString("u.prenom"),rs.getString("u.adresse"),rs.getString("u.tel"),rs.getDate("u.dns")),rs.getInt("c.prix_livraison"),rs.getDouble("c.prix_produits"),rs.getDouble("c.prix_total"),rs.getDate("c.date_commande")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Tri(commandes);
    }
    
    public List<Commande> Tri(List<Commande> l){
            return l.stream()
            .sorted((a, b) -> a.getDate_commande().compareTo(b.getDate_commande()))
             .collect(Collectors.toList());
    }
}
