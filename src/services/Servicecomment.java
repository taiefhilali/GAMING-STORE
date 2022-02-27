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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
         String request = "INSERT INTO `comment`(`contenu`,`label`,`resp`,`id_user`,`id_post`,`nblike`,`nbdislike`) VALUES ('"+c.getContenu()+"','"+c.getLabel()+"',"+c.getResp()+","+c.getId_user().getId()+","+c.getId_post().getId()+","+c.getNblike()+","+c.getNbdislike()+")";
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
    public boolean modifierComment(Comment c) {
           String req = "UPDATE `comment` SET `contenu`='"+c.getContenu()+"',`label`='"+c.getLabel()+"',`resp`='"+c.getResp()+"',`id_user`='"+c.getId_user()+"',`id_post`='"+c.getId_post()+"',`nblike`='"+c.getNblike()+"',`nbdislike`='"+c.getNbdislike()+"' WHERE `idc` = "+c.getIdc()+" ";
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

    @Override
    public void Commentsum() {
        String req3="SELECT COUNT(*) AS commentCount FROM comment ";
        try
        {  
             Statement st= cnx.prepareStatement(req3);
             ResultSet rs = st.executeQuery(req3);
             while(rs.next())
             {
              int count = rs.getInt("commentCount");
              //  int count = rs.getInt(1);
 
                 System.out.println(" LE NOMBRE DES COMMENTAIRES  = "+count+"COMMENTAIRES");
                 
             }
        }
           catch (SQLException ex)
           {
            ex.printStackTrace();
           }
                    

    }

    @Override
    public List<Comment> afficherComment() {
            List<Comment> comments = new ArrayList<Comment>();
        String query = "SELECT * FROM comment c inner join  post p on p.id  = c.idc join user u on p.id_user = u.id_user ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                comments.add(new Comment(rs.getInt("idc"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        new User(rs.getInt("id_user"), rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("role"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("adresse"),
                                rs.getString("tel"),
                                rs.getDate("dns")),
                       new Post(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getDate("datep"),new User(rs.getInt("id_user"))),
                         rs.getInt(6),
                          rs.getInt(7)
                        
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }

//************************trie nblike with stream***********************//

    public ArrayList<Comment> sortBynblike() {
            List<Comment> comments=afficherComment();
         List<Comment> resultat=comments.stream().sorted(Comparator.comparing(Comment::getNblike).reversed()).collect(Collectors.toList());
         resultat.forEach(System.out::println);
        return (ArrayList<Comment>) resultat;
    }


    @Override
    public Map<Integer, List<String>> bestpost(int nblike) {
        List<Comment> comments=afficherComment();
       Map<Integer, List<String>> Postss =  comments.stream()
                .filter(Comment -> Comment.getNblike()>=Integer.max(nblike, nblike))
                .sorted((a,b)->a.getNblike()- b.getNblike())
                .collect(Collectors.groupingBy(Comment::getNblike,
                    Collectors.mapping(
                       Comment->Comment.getLabel(), Collectors.toList())))
               ;
            //   .sorted(Map.Entry.comparingByKey())
   
                 
       return Postss;
    }

}
