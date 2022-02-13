/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionforum;

import java.sql.Connection;
import java.util.Date;
import models.Comment;
import models.Post;
import services.Servicecomment;
import services.Servicepost;
import util.MaConnexion;

/**
 *
 * @author msi
 */
public class Gestionforum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          Connection cnx = MaConnexion.getInstance().getCnx();
        // Personne
        java.util.Date date=new java.util.Date();
java.sql.Date sqlDate=new java.sql.Date(date.getTime());
java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());

        Post p = new Post("GAMING","ACCESOIRES ET JEUX VIDEOS",sqlDate);
        Post p1 = new Post("TAIEF"," ETCETCETCJEUX VIDEOS",sqlDate);
          //Comment
       Comment c= new Comment("firstcomment", "comm", 12);
            Comment c2= new Comment("secondcomment", "commtwo", 10);
        // SERVICE
        Servicepost sp = new Servicepost();
        Servicecomment sc = new Servicecomment();

        // AJOUT post
         //System.out.println(sp.ajouterPost(p));
        // System.out.println(sp.ajouterPost(p1));
         //afficherpost
        System.out.println(sp.afficherPost());
            //ajoutComment
            //    System.out.println(sc.ajouterComment(c));
              //affichercomment
              // System.out.println(sc.afficherComment());
              
         // MODIFIER
       Post p3 = new Post(4,"hahaha", "videogame",new java.sql.Date(123234042));
        //System.out.println("Update\n");
      //System.out.println(sp.modifierPost(p3));
        //updatecomment
         
         Comment c3= new Comment(1, "THIRDcomment", "HAHAAHA", 13);
        //System.out.println("Update\n");
      //  System.out.println(sc.modifierComment(c3));
        
        
        //deletepost
      
        System.out.println("Deletepost");
        System.out.println(sp.supprimerPost(p3));
        
                //deleteComment
                 System.out.println("Deletecomment");
        System.out.println(sc.supprimerComment(c3));
        
      
       
       
   
     
       

    }
    
    
}
