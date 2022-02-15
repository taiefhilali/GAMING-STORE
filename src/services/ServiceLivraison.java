/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import interfaces.Ilivraison;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Livraison;
import models.Reclamation;
import utils.Maconnexion;

/**
 *
 * @author 21694
 */
public class ServiceLivraison implements Ilivraison{
    
    //var
    Connection cnx = Maconnexion.getInstance().getCnx();

    public boolean ajouterLivraison(Livraison l) {
        String request = "INSERT INTO `livraison`( `id_commande`, `id_livreur`, `date`, `etat_livraison`) VALUES ("+l.getId_commande()+","+l.getId_livreur()+",'"+l.getDate()+"','"+l.getEtat_livraison()+"')";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(request) == 1)
                return true;
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Livraison> afficherLivraison() {
        List<Livraison> livraison = new ArrayList<Livraison>();

        String req="SELECT * FROM livraison";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                livraison.add(new Livraison(rs.getInt("id_livraison"),rs.getInt("id_commande"),rs.getInt("id_livreur"),rs.getDate("date"),rs.getString("etat_livraison")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return livraison;
    }
    
    public boolean modifierLivraison(Livraison l) {
        String req = "UPDATE `livraison` SET `id_commande`='"+l.getId_commande()+"',`id_livreur`= "+l.getId_livreur()+",`date`='"+l.getDate()+"',`etat_livraison`='"+l.getEtat_livraison()+"' WHERE `id_livraison` = "+l.getId_livraison()+" ";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerLivraison(Livraison l) {
        String req = "DELETE FROM `livraison` WHERE `id_livraison` = "+l.getId_livraison()+" ";

        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ajouterReclamation(Reclamation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}