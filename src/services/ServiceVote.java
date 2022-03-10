/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import utils.MaConnexion;
import interfaces.Ivote;
import java.util.ArrayList;
import java.util.List;
import models.Post;
import models.User;
import models.Vote;

/**
 *
 * @author msi
 */
public class ServiceVote extends Ivote {
    public List<Vote> afficherVote() {
       
      countVote();

     return afficherVote();
    }
    Connection cnx = MaConnexion.getInstance().getCnx();

    public void upVote(int idPost, int idUser) {
        upvoteDownvote(idPost, idUser, 1);
    }

    public void downVote(int idPost, int idUser) {
        upvoteDownvote(idPost, idUser, 2);
    }

    private TreeMap<Integer, Set<Integer>> posts;

    private Map<Integer, Integer> votes;

    private boolean upvoteDownvote(int idPost, int idUser, int value) {
        
        int count = countVote(idPost, idUser, value) ;
        if (count != 0 ) {
            return false;
        }
        int count1 = countVote(idPost, idUser, value == 1? 2:1);
        if (count1 != 0 ) {
            // req delete
          String reqdelete= "DELETE FROM `post` WHERE `id` = " + idPost + " ";
        }
        String reqInsert = "INSERT INTO `vote` (`idv`, `id`, `id_user`, `vote_type`) VALUES (NULL, "+idPost+" , "+idUser+", "+value+");";
      
        System.out.println(reqInsert);
         try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(reqInsert) == 1)
                return true;
            return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } 
        

        
    }

    private int countVote(int idPost, int idUser, int value) {
        String req = "select count(*) as count_vote from vote where id= " + idPost+ " and id_user = "+ idUser + " and vote_type = "+ value ;
        int count = 0;
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            System.out.println("count " + count);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    
    
    
    public int countVote( int idUser, int value) {
        String req = "select count(*) as count_vote from vote where id_user = "+ idUser + " and vote_type = "+ value ;
        int count = 0;
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            System.out.println("count " + count);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    
    
}
