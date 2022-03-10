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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        System.out.println(c.getId_post());
        String request = "INSERT INTO `comment`(`contenu`,`label`,`resp`,`id_post`) VALUES ('" + c.getContenu() + "','" + c.getLabel() + "'," + c.getResp() + "," + c.getId_post().getId() + ")";
        System.out.println(request);
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

    @Override
    public boolean modifierComment(Comment c) {
        String req = "UPDATE `comment` SET `contenu`='" + c.getContenu()+ "',`label`='" + c.getLabel() + "',`resp`=" + c.getResp() + ",`id_post`=" + c.getId_post().getId() + " WHERE `idc` = " + c.getIdc() + " ";
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
    public boolean supprimerComment(Comment c) {
        String req = "DELETE FROM `comment` WHERE `idc` = " + c.getIdc() + " ";

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
    public int Commentsum() {
        String req3 = "SELECT COUNT(*) AS commentCount FROM comment ";
        try {
            Statement st = cnx.prepareStatement(req3);
            ResultSet rs = st.executeQuery(req3);
            while (rs.next()) {
                int count = rs.getInt("commentCount");
                //  int count = rs.getInt(1);
                return count;
                //System.out.println(" LE NOMBRE DES COMMENTAIRES  = "+count+"COMMENTAIRES");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Commentsum();
    }

    @Override
    public List<Comment> afficherComment() {
        List<Comment> comments = new ArrayList<Comment>();
        String query = "SELECT * FROM comment c inner join  post p on p.id  = c.id_post ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                comments.add(new Comment(rs.getInt("idc"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getDate("datep"), new User(rs.getInt("id_user")))
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }

////************************trie nblike with stream***********************//
//
//    public ArrayList<Comment> sortBynblike() {
//            List<Comment> comments=afficherComment();
//         List<Comment> resultat=comments.stream().sorted(Comparator.comparing(Comment::getNblike).reversed()).collect(Collectors.toList());
//         resultat.forEach(System.out::println);
//        return (ArrayList<Comment>) resultat;
//    }
//
//********************best post***************
    public Map<Integer, List<String>> bestpost(int resp) {
        List<Comment> comments = afficherComment();
        Map<Integer, List<String>> Postss = comments.stream()
                .filter(Comment -> Comment.getResp() >= Integer.max(resp, resp))
                // 
                .sorted((a, b) -> a.getResp() - b.getResp())
                .collect(Collectors.groupingBy(Comment::getResp,
                        Collectors.mapping(
                                Comment -> Comment.getContenu(), Collectors.toList()))) // .max(Comparator.comparing(Comment::getResp))
                ;

        return Postss;
    }

    // Function takes two parameter
    public String censor(String text, String word) {

        // Break down sentence by ' ' spaces
        // and store each individual word in
        // a different list
        

        // A new string to store the result
        String result = "";

        // Creating the censor which is an asterisks
        // "*" text of the length of censor word
        String stars = "";
        for (int i = 0; i < word.length(); i++) {
            stars += '*';
        }

        // Iterating through our list
        // of extracted words
        int index = 0;
        String[] word_list = text.split("\\s+");
        for (String i : word_list) {
            if (i.compareTo(word) == 0) // changing the censored word to
            // created asterisks censor
            {
                word_list[index] = stars;
            }
            index++;
        }

        // join the words
        for (String i : word_list) {
            result += i + ' ';
        }

        return result;
    }

    //  ***********
//--------------------------------- getcommttssList() ------------------------------------------------------//
    public ObservableList<Comment> getCmList() throws SQLException {
        ObservableList<Comment> Cmlist = FXCollections.observableArrayList();

        List<Comment> listb = new ArrayList<>();
        Statement st = cnx.createStatement();
        String query = "SELECT * FROM comment c inner JOIN post p  where c.id_post=p.id";

        ResultSet rs;
        rs = st.executeQuery(query);
        Comment comments;
        while (rs.next()) {
            comments = (new Comment(rs.getInt("idc"),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getDate("datep"), new User(rs.getInt("id_user")))));
            //System.out.println(events);
            Cmlist.add(comments);

        }
        return Cmlist;
    }

    //******************listtt
    public ObservableList<Post> getpostsList() throws SQLException {
        ObservableList<Post> postslist = FXCollections.observableArrayList();

        List<Post> listb = new ArrayList<>();
        Statement st = cnx.createStatement();
        String query = "SELECT id FROM post";

        ResultSet rs;
        rs = st.executeQuery(query);
        Post postss;
        while (rs.next()) {
            postss = new Post(rs.getInt("id"));
            //rs.getInt(req)

            //System.out.println(events);
            postslist.add(postss);

        }
        return postslist;
    }

}
