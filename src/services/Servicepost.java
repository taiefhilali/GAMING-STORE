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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;
import models.Post;
import models.User;
import utils.MaConnexion;

/**
 *
 * @author msi
 */
public class Servicepost implements Ipost {
 
    //var
    Connection cnx = MaConnexion.getInstance().getCnx();
 

     @Override
    public boolean ajouterPost(Post p) {
         String request = "INSERT INTO `post`(`title`, `content`, `datep`, `id_user`) VALUES ('"+p.getTitle()+"','"+p.getContent()+"','"+p.getDatep()+"',"+p.getId_user().getId()+")";
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

        String req="SELECT * FROM post p inner JOIN user u  where p.id_user=u.id_user ";
      
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                posts.add(new Post(rs.getInt("id"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        new User(rs.getInt("id_user"), rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("role"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("adresse"),
                                rs.getString("tel"),
                                rs.getDate("dns"))
                       ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return posts;
    }

   
    @Override
    public boolean modifierPost(Post p) {
            String req = "UPDATE `post` SET `title`='"+p.getTitle()+"',`content`='"+p.getContent()+"',`datep`='"+p.getDatep()+"', `id_user`='"+p.getId_user().getId()+"' WHERE `id` = "+p.getId()+" ";
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

   
    
  
    public List<Post> afficherTrie()
    {
      List<Post> posts =  new ArrayList<>();
      String sqll="select * from `post` order by datep desc";
      
      try
      {
        Statement st=cnx.prepareStatement(sqll);
          
          ResultSet rs=st.executeQuery(sqll);
                  while(rs.next()){
                      Post a = new Post();
                      a.setId(rs.getInt("id"));
                      a.setTitle(rs.getString("title"));
                      a.setContent(rs.getString("content"));
                      a.setDatep(rs.getDate("datep"));
                      a.setId_user(new User(rs.getInt("id_user")));
                      posts.add(a);
                      System.out.println("ID : "+a.getId()+"\n content : "+a.getContent()+"\n Title : "+a.getTitle()+"\n Date : "+a.getDatep()+"\n id_user: "+a.getId_user()+"\n Id ");
                      //System.out.println("Afficher avec succés !");
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return posts;  

    }

    @Override
    public ArrayList<Post> AfficherTrie() {
        List<Post> posts =  new ArrayList<>();
      String sqll="select * from `post` order by content asc";
      
      try
      {
        Statement st=cnx.prepareStatement(sqll);
          
          ResultSet rs=st.executeQuery(sqll);
                  while(rs.next()){
                      Post a = new Post();
                      a.setId(rs.getInt("id"));
                      a.setTitle(rs.getString("title"));
                      a.setContent(rs.getString("content"));
                      a.setDatep(rs.getDate("datep"));
                      a.setId_user(new User(rs.getInt("id_user")));
                      posts.add(a);
                      System.out.println("ID : "+a.getId()+"\n content : "+a.getContent()+"\n Title : "+a.getTitle()+"\n Date : "+a.getDatep()+"\n id_user: "+a.getId_user()+"\n Id ");
                      //System.out.println("Afficher avec succés !");
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return (ArrayList<Post>) posts;  
    }
   
 
   
///*******************recherche with stream**********************//
   

    public ArrayList<Post> findBytitle(String title) {
       
        List<Post> resultat=afficherPost().stream().filter(post->title.equals(post.getTitle())).collect(Collectors.toList());
        return (ArrayList<Post>) resultat;
    }

       

    
    //************************trie  par date with stream***********************//

    @Override
    public ArrayList<Post> sortByDate() {
            List<Post> posts=afficherPost();
         List<Post> resultat=posts.stream().sorted(Comparator.comparing(Post::getDatep)).collect(Collectors.toList());
         return (ArrayList<Post>) resultat;
    }
    
    
    }
    

