/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Iskander
 */
public class MaConnexion {

    // Définir les credentials de la BDD
    final String URL = "jdbc:mysql://127.0.0.1:3306/levelup";
    final String USR = "root";
    final String PWD = "";

    //Variable de la classe ma connexion
    private Connection cnx;

    // Constructeur par défaut
    // Role du concepteur: Design pattern / patron de conception : unifier les accés au BDD?
    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println(" Connexion établie avec succés ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    static MaConnexion instance = null;

    public static MaConnexion getInstance() {
        if (instance == null) {
            instance = new MaConnexion();
        }
        return instance;
    }

}
