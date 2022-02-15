/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class MaConnexion {
    
     //DB
    final String URL = "jdbc:mysql://127.0.0.1:3306/levelup";
    final String USR = "root";
    final String PWD = "";
    
    //var
    private Connection cnx;
    static MaConnexion instance = null;
    
    
    //constructor
    public MaConnexion(){
        try {
            cnx = (Connection) DriverManager.getConnection(URL, USR, PWD);
            System.out.println("connexion établi avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //getter
    public Connection getCnx() {
        return cnx;
    }

    public static MaConnexion getInstance() {
        if(instance == null){
            instance = new MaConnexion();
        }
        return instance;
    }
    
    
}
