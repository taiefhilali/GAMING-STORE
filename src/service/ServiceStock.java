/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.Istock;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Stock;
import utile.Connexion;

/**
 *
 * @author user
 */
public class ServiceStock implements Istock {
     Connection cnx =Connexion.getInstance().getCnx();

    @Override
    public void ajouterStock(Stock s) {
   String request= "INSERT INTO `produit`( `id`,`nom`, `quantite`, `etat`) VALUES( "+s.getId()+",'"+s.getNom()+"',"+s.getQuantite()+",'"+s.getEtat()+"')";
  
         try {
               Statement st = cnx.createStatement();
             st.executeUpdate(request);
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
          
    }

    @Override
    public List<Stock> afficherStock() {
          List<model.Stock> stocks =new ArrayList<model.Stock>();
     String query = "SELECT * FROM produit";
      Statement st = null;
         try {
             st=cnx.createStatement();
             ResultSet rs= st.executeQuery(query);
             while (rs.next()){
             stocks.add(new model.Stock(rs.getInt("id"), rs.getString(2), rs.getInt("quantite"), rs.getString(4)));
         }
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
             return stocks;
    }


    @Override
    public boolean modifierStock(Stock s) {
                 String req = "UPDATE `produit` SET `id`='"+s.getId()+"',`nom`='"+s.getNom()+"',`quantite`='"+s.getQuantite()+"',`etat`="+s.getEtat()+"' WHERE `id` = "+s.getId()+" ";
         try {
             Statement st = cnx.createStatement();
              if (st.executeUpdate(req) == 1)
                return true;
            return false;
         } catch (SQLException ex) {
             ex.printStackTrace();
            return false;
         }
    }

    @Override
    public boolean supprimerStock(Stock s) {
   String req = "DELETE FROM `produit` WHERE `id` = "+s.getId()+" ";
         try {
             Statement st = cnx.createStatement();
             if (st.executeUpdate(req) == 1)
                return true;
            return false;
         } catch (SQLException ex) {
             ex.printStackTrace();
            return false;
         }
    }
    
    
    
}
