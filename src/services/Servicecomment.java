/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Icomment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Comment;
import models.Post;
import util.MaConnexion;

/**
 *
 * @author msi
 */
public class Servicecomment implements Icomment {

    //var
    Connection cnx = MaConnexion.getInstance().getCnx();
 
    @Override
    public boolean ajouterComment(Comment c) {
         String request = "INSERT INTO `comment`(`contenu`, `label`, `resp`) VALUES ('"+c. getContenu()+"','"+c.getLabel()+"',"+c.getResp()+")";
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

    @Override
    public List<Comment> afficherComment() {
           List<Comment> comments = new ArrayList<Comment>();

        String req="SELECT * FROM Comment";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                comments.add(new Comment(rs.getInt("idc"),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return comments;
    }

    @Override
    public boolean modifierComment(Comment c) {
           String req = "UPDATE `comment` SET `contenu`='"+c.getContenu()+"',`label`='"+c.getLabel()+"',`resp`='"+c.getResp()+"' WHERE `idc` = "+c.getIdc()+" ";
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

    @Override
    public boolean supprimerComment(Comment c) {
        String req = "DELETE FROM `comment` WHERE `idc` = "+c.getIdc()+" ";

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
    
}
