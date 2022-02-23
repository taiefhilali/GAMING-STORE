/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Iuser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;
import utils.MaConnexion;

/**
 *
 * @author 21694
 */
public class ServiceUser implements Iuser {

    //var
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public long ajouterPersonne(User p) {
        String request = "INSERT INTO `user`(`email`, `password`, `role`, `nom`, `prenom`, `adresse`, `tel`, `dns`) VALUES ('" + p.getEmail() + "','" + p.getPassword() + "','" + p.getRole() + "','" + p.getNom() + "','" + p.getPrenom() + "','" + p.getAdresse() + "','" + p.getTel() + "','" + p.getDns() + "')";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(request, Statement.RETURN_GENERATED_KEYS) == 1) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<User> afficherPersonnes() {
        List<User> personnes = new ArrayList<User>();
        return personnes;
    }

    @Override
    public boolean modifierPersonne(User p) {

        String req = "UPDATE `user` SET `email`= '" + p.getEmail() + "' , `password`='" + p.getPassword() + "' ,`role`='" + p.getRole() + "', `nom`='" + p.getNom() + "', `prenom`='" + p.getPrenom() + "',`adresse`='" + p.getAdresse() + "',`tel`='" + p.getTel() + "',`dns`='" + p.getDns() + "' WHERE `id_user` = " + p.getId() + " ";

        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    @Override
    public boolean supprimerPersonne(User p) {
        String req = "DELETE FROM `user` WHERE `id_user` = " + p.getId() + " ";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
