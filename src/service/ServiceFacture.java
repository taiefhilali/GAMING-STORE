/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.Ifacture;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Facture;

import utile.Connexion;

/**
 *
 * @author user
 */
public class ServiceFacture implements Ifacture {
     Connection cnx =Connexion.getInstance().getCnx();

    @Override
    public void ajouterFacture(Facture f) {
        String request= "INSERT INTO `facture`(`id_facture`, `date`, `prix_total`) VALUES( '"+f.getId_facture()+"','"+f.getDate()+"','"+f.getPrix_total()+"')";
             
         try {
              Statement st = cnx.createStatement();
              st.executeUpdate(request);
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
             
    }

    @Override
    public List<Facture> afficherFacture() {
        List<model.Facture> factures =new ArrayList<model.Facture>();
     String query = "SELECT * FROM facture";
      Statement st = null;
         try {
             st=cnx.createStatement();
              ResultSet rs= st.executeQuery(query);
             while (rs.next()){
             factures.add(new model.Facture(rs.getInt("id_facture"), rs.getDate(2), rs.getString(3)));
         }
         } catch (SQLException ex) {
               ex.printStackTrace();
         }
            return factures;
    }

    @Override
    public boolean modifierFacture(Facture f) {
                 String req = "UPDATE `facture` SET `date`='"+f.getDate()+"',`prix_total`='"+f.getPrix_total()+"' WHERE `id_facture` = "+f.getId_facture()+" ";
                
         try {
           Statement  st = cnx.createStatement();
           if (st.executeUpdate(req) == 1)
                return true;
            return false;
         } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
         }
              
    }

    @Override
    public boolean supprimerFacture(Facture f) {
         try {
             String req = "DELETE FROM `facture` WHERE `id_facture` = "+f.getId_facture()+" ";
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
