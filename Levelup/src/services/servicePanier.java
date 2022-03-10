/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IPanier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Client;
import models.Panier;
import utils.MaConnexion;

/**
 *
 * @author ASUS
 */
public class servicePanier implements IPanier{
    //var
    Connection cnx = MaConnexion.getInstance().getCnx();
    
    
    //Ajouter Panier
    @Override
    public void ajouterPanier(Panier p) {
        String request ="INSERT INTO `panier`(`id_user`) VALUES ("+p.getClient().getId()+")";
         try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Panier Ajouter avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Supprimer Panier
    @Override
    public void supprimerPanier(Panier p) {
        String request ="DELETE FROM `panier` WHERE id_panier ="+p.getId_panier()+"";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Panier Supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    @Override
    public Panier getElement(int id) {
         
       Panier Prod = new Panier();
       String req="SELECT * FROM panier pe inner join client p1 on pe.id_user = p1.id_user WHERE pe.id_user ="+id+"";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                Prod.setId_panier(rs.getInt("pe.id_panier"));
                Prod.setClient(new Client(rs.getString("p1.sexe"),rs.getInt("p1.id_user")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Prod;

    }
    
}
