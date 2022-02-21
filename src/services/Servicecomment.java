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
import models.User;


import utils.MaConnexion;

/**
 *
 * @author msi
 */
public class Servicecomment implements Icomment {

    //var
    Connection cnx = MaConnexion.getInstance().getCnx();
 
    @Override
    public boolean ajouterComment(Comment c) {
        System.out.println(c);
         String request = "INSERT INTO `comment`(`contenu`,`label`,`resp`,`id_user`,`id_post`) VALUES ('"+c.getContenu()+"','"+c.getLabel()+"',"+c.getResp()+","+c.getId_user().getId()+","+c.getId_post().getId()+")";
        System.out.println(request);
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

        String req="SELECT * FROM `comment`";
        User u=null;
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            
            while(rs.next()){
                String req1="SELECT * FROM `user` where`id_user`="+rs.getInt("id_user")+"";
                 ResultSet rs1 = st.executeQuery(req1);
                 while(rs1.next()){
                 u=new User(rs1.getInt("id_user"), rs1.getString("email"), rs1.getString("password"),
                    rs1.getString("role"), rs1.getString("nom"), rs1.getString("prenom"), rs1.getString("adresse"), rs1.getString("tel"), rs1.getDate("dns"));
                 
                 }
                comments.add(new Comment(rs.getInt("idc"),rs.getString(2),rs.getString(3),rs.getInt(4),u,new Post()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return comments;
    }

    @Override
    public boolean modifierComment(Comment c) {
           String req = "UPDATE `comment` SET `contenu`='"+c.getContenu()+"',`label`='"+c.getLabel()+"',`resp`='"+c.getResp()+"',`id_user`='"+c.getId_user()+"',`id_post`='"+c.getId_post()+"' WHERE `idc` = "+c.getIdc()+" ";
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
