/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Ipost;
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
public class Servicepost implements Ipost {
 
    //var
    Connection cnx = MaConnexion.getInstance().getCnx();
 

     @Override
    public boolean ajouterPost(Post p) {
         String request = "INSERT INTO `post`(`title`, `content`, `datep`) VALUES ('"+p.getTitle()+"','"+p.getContent()+"','"+p.getDatep()+"')";
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
    public List<Post> afficherPost() {
        List<Post> posts = new ArrayList<Post>();

        String req="SELECT * FROM Post";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                posts.add(new Post(rs.getInt("id"),rs.getString(2),rs.getString(3),rs.getDate(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return posts;
    }

   
    @Override
    public boolean modifierPost(Post p) {
            String req = "UPDATE `post` SET `title`='"+p.getTitle()+"',`content`='"+p.getContent()+"',`datep`='"+p.getDatep()+"' WHERE `id` = "+p.getId()+" ";
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
    public boolean supprimerPost(Post p) {
       String req = "DELETE FROM `post` WHERE `id` = "+p.getId()+" ";

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
    

