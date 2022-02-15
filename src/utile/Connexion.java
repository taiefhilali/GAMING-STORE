/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Connexion {
     public final String URL="jdbc:mysql://localhost:3306/stock";
      public final String USR="root";
       public final String PWD="";
     private Connection cnx;
 //1
     static Connexion Instance=null;
//2
private Connexion() {
         try {
             cnx=DriverManager.getConnection(URL, USR, PWD);
             System.out.println("succes");
         } catch (SQLException ex) {
            
     ex.printStackTrace();
         }
     //unifie integrite des donnees conflit des donnees
    }
//3
    public static Connexion getInstance() {
        if(Instance==null){
            Instance=new Connexion();}
        return Instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    
}
