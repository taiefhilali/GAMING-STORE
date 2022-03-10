/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.mysql.jdbc.PreparedStatement;
import interfaces.Ipost;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        String request = "INSERT INTO `post`(`title`, `content`, `datep`, `id_user`) VALUES ('" + p.getTitle() + "','" + p.getContent() + "','" + p.getDatep() + "'," + p.getId_user().getId() + ")";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(request) == 1) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
 public List<Post> sh() {
        List<Post> posts = new ArrayList<Post>();

        String req = "SELECT * FROM post p inner JOIN user u  where p.id_user=u.id_user ";

        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while (rs.next()) {
                posts.add(new Post(rs.getString(2), rs.getString(3)
                        
                      
                ) );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    @Override
    public List<Post> afficherPost() {
        List<Post> posts = new ArrayList<Post>();

        String req = "SELECT * FROM post p inner JOIN user u  where p.id_user=u.id_user ";

        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while (rs.next()) {
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
        Date d = Date.valueOf(LocalDate.now());
        String req = "UPDATE `post` SET `title`='" + p.getTitle() + "',`content`='" + p.getContent() + "',`datep`='" + d + "', `id_user`='" + p.getId_user().getId() + "' WHERE `id` = " + p.getId() + " ";
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
    public boolean supprimerPost(Post p) {
        String req = "DELETE FROM `post` WHERE `id` = " + p.getId() + " ";

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

    public List<Post> afficherTrie() {
        List<Post> posts = new ArrayList<>();
        String sqll = "select * from `post` order by datep desc";

        try {
            Statement st = cnx.prepareStatement(sqll);

            ResultSet rs = st.executeQuery(sqll);
            while (rs.next()) {
                Post a = new Post();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
                a.setDatep(rs.getDate("datep"));
                a.setId_user(new User(rs.getInt("id_user")));
                posts.add(a);
                System.out.println("ID : " + a.getId() + "\n content : " + a.getContent() + "\n Title : " + a.getTitle() + "\n Date : " + a.getDatep() + "\n id_user: " + a.getId_user() + "\n Id ");
                //System.out.println("Afficher avec succés !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;

    }

    @Override
    public ArrayList<Post> AfficherTrie() {
        List<Post> posts = new ArrayList<>();
        String sqll = "select * from `post` order by content asc";

        try {
            Statement st = cnx.prepareStatement(sqll);

            ResultSet rs = st.executeQuery(sqll);
            while (rs.next()) {
                Post a = new Post();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
                a.setDatep(rs.getDate("datep"));
                a.setId_user(new User(rs.getInt("id_user")));
                posts.add(a);
                System.out.println("ID : " + a.getId() + "\n content : " + a.getContent() + "\n Title : " + a.getTitle() + "\n Date : " + a.getDatep() + "\n id_user: " + a.getId_user() + "\n Id ");
                //System.out.println("Afficher avec succés !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (ArrayList<Post>) posts;
    }

///*******************recherche with stream**********************//
    public ArrayList<Post> findBytitle(String title) {

        List<Post> resultat = afficherPost().stream().filter(post -> title.equals(post.getTitle())).collect(Collectors.toList());
        return (ArrayList<Post>) resultat;
    }

    //************************trie  par date with stream***********************//
    @Override
    public ArrayList<Post> sortByDate() {
        List<Post> posts = afficherPost();
        List<Post> resultat = posts.stream().sorted(Comparator.comparing(Post::getDatep)).collect(Collectors.toList());
        return (ArrayList<Post>) resultat;
    }

    @Override
    public List<Post> show() {

        List<Post> posts = new ArrayList<Post>();

        String req = "SELECT * FROM post p inner JOIN user u  where p.id_user=u.id_user ";

        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                posts.add(new Post(rs.getInt("id"), rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        new User(rs.getString("email"))
                //rs.getInt(req)
                ));
                //rs.getString("email"))
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public String getContentbyID(int id) {
        String req = "select * from post where id='" + id + "' ";

        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                return rs.getString("content");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return "";

    }

//   //----------------------------------- Display Cat_age by ID -----------------------------------------------------------------// 
//  
    public String gettitlebyID(int id) {
        String req = "select * from post where id='" + id + "' ";

        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                return rs.getString("title");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return "";

    }
//    
//    //----------------------------------------  Display  by ID --------------------------------------------------------------//
//     

    public Date getdatebyID(int id) {
        String req = "select * from post where id='" + id + "' ";

        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                return rs.getDate("datep");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return new Date(0);

    }
//     //----------------------------------------  Display by ID --------------------------------------------------------------//
//    

    public User getuserbyID(int id) {
        String req = "select * from post where id='" + id + "' ";

        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                return new User(rs.getInt("user_id"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return new User();

    }

    public void modifier(Post p, int id) {

        String req = "update user set content = ? , titre = ? , datep = ? , id_user = ? where id = ?";

        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(req);

            st.setString(1, p.getContent());
            st.setString(2, p.getTitle());
            st.setDate(3, p.getDatep());
            st.setString(4, p.getId_user().toString());
            st.setInt(5, id);

            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

//------------------------------------ Calculer nbAct -------------------------------------------//
    public String calculer_nbp(String title) {
        String l = null;
        String requete = "SELECT COUNT(*) FROM post where title='" + title + "'";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                l = chaine;
                return l;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l;
    }
 
    //-----------------------
////------------------------------- Liste 2 --------------------------------------------------------------------//
//    public List <Post> liste2()
//    {
//        String sql = "select content,title,datep,id_user from post";
//        
//       List <Post> list = new ArrayList<>(); 
//       try {
//               PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
//
//         ResultSet rs=st.executeQuery();
//       
//       while (rs.next())
//       {
//           list.add(new Post(rs.getString("title"),rs.getString("content"),rs.getDate("date")));
//       }
//       
//       }
//       catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }   
//    return list; 
//    }
    //--------------------------------- getpostttssList() ------------------------------------------------------//
    public ObservableList<Post> getCoursList() throws SQLException {
        ObservableList<Post> Courslist = FXCollections.observableArrayList();

        List<Post> listb = new ArrayList<>();
        Statement st = cnx.createStatement();
        String query = "SELECT * FROM post p inner JOIN user u  where p.id_user=u.id_user";

        ResultSet rs;
        rs = st.executeQuery(query);
        Post postss;
        while (rs.next()) {
            postss = (new Post(rs.getInt("id"), rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    // new User(rs.getString("email"))
                    new User(rs.getInt("id_user"))
            //rs.getInt(req)
            ));
            //System.out.println(events);
            Courslist.add(postss);

        }
        return Courslist;
    }

    //******************listtt
    public ObservableList<User> getusersList() throws SQLException {
        ObservableList<User> userslist = FXCollections.observableArrayList();

        List<User> listb = new ArrayList<>();
        Statement st = cnx.createStatement();
        String query = "SELECT id_user FROM user";

        ResultSet rs;
        rs = st.executeQuery(query);
        User postss;
        while (rs.next()) {
            postss = new User(rs.getInt("id_user"));
            //rs.getInt(req)

            //System.out.println(events);
            userslist.add(postss);

        }
        return userslist;
    }

    public String calculer_nbseance(String title) {
        String l = null;
        String requete = "SELECT COUNT(*) FROM post where title='" + title + "'";
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(l));
                l = chaine;
                return l;
            }
        } catch (SQLException ex) {
        }

        return l;
    }
public List<Post> chercherPost(List<Post> initialList, String input) {
        
         List<Post> prodList;
          prodList = initialList.stream()
                  .map( Post::concat )
                  .filter(pt -> pt.toLowerCase().contains(input.toLowerCase()))
                  .map(pt -> new Post(pt.split(".@.")[0],pt.split(".@.")[1]))
                  .collect( Collectors.toList());
        
        return prodList;
    }

}
