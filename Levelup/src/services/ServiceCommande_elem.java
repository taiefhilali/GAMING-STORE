/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import interfaces.ICommande_elem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Commande;
import models.Commande_elem;
import models.Produit;
import models.TopProduits;
import utils.MaConnexion;

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
        String req = "INSERT INTO `detail_commande`(`id`, `id_commande`, `Quantite`) VALUES ("+e.getProduit().getId_produit()+","+e.getCmd().getId_commande()+","+e.getQuantite()+")";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Element de commande Ajouter avec succés");
        } catch (SQLException ex) {
            System.out.println("L'ajout est echouer");
        }
    }

    
    //Supprimer element de commande
    @Override
    public void supprimerElementCommande(Commande_elem e) {
            String req = "DELETE FROM `detail_commande` WHERE id_commande = "+e.getCmd().getId_commande()+" ";

        try {
            Statement st = cnx.createStatement();
           st.executeUpdate(req); 
           System.out.println("Element de Commande Supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void supprimerElementByID(int id) {
            String req = "DELETE FROM `detail_commande` WHERE id_elemC = "+id+" ";

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
    public void modifierElementCommande(Integer e , int id) {
        String req = "UPDATE `detail_commande` SET `Quantite`="+ e +" WHERE id_elemC ="+id+"";
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
        
         String req="SELECT * FROM detail_commande dc inner join produit p on dc.id = p.id_produit inner join commande c on dc.id_commande = c.id_commande WHERE dc.id_commande ="+id+"";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                commandes_elem.add(new Commande_elem(rs.getInt("dc.id_elemC"),new Produit(rs.getInt("p.id_produit"),rs.getString("p.nom"),rs.getDouble("p.prix_final"),rs.getString("p.description"),rs.getString("p.image"))
                        ,new Commande(rs.getInt("c.id_commande"),rs.getFloat("c.prix_livraison"),rs.getDate("c.date_commande")),rs.getInt("dc.Quantite")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commandes_elem;

    }
    
    /****Afficher Top Produits ****/
    @Override
    public List<TopProduits> TopProduits (){
        int n =3;
        List<TopProduits> produits = new ArrayList<TopProduits>();
       

        String req = "select id,count(id) from detail_commande group by id order by count(id) desc";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            int i=0;
            while(rs.next() && i<n ){
                String req1 = "select * from produit where id_produit ="+rs.getInt(1)+"";
                Statement st1 = cnx.createStatement();
                ResultSet rs1 = st1.executeQuery(req1);
                while(rs1.next()){
                produits.add(new TopProduits(rs.getInt(1),rs.getInt(2),
                    new Produit(rs1.getInt("id_produit"),rs1.getString("nom"),rs1.getDouble("prix"),rs1.getString("description"))
));
                i++;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return produits;
    }
    
    
}
