/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author 21694
 */
public class Maconnexion {
   
    final String URL="jdbc:mysql://127.0.0.1:3306/levelup";
    final String USERNAME="root";
    final String PWD="";
    
    private Connection cnx;
    //1
    static Maconnexion instance = null;
    
    //constructeur
    //2
    private Maconnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("succès");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    //3
    public static Maconnexion getInstance() {
        
        if(instance == null){
            instance = new Maconnexion();
        }
        return instance;
    }
                
            
}


