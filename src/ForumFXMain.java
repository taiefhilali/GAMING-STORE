/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.Servicepost;

/**
 *
 * @author msi
 */
public class ForumFXMain extends Application {
       
    @Override
    public void start(Stage stage) throws Exception {
        //****UPDATE POST+show******//
  // Parent root = FXMLLoader.load(getClass().getResource("UPDATE_PostFXML.fxml"));
    // Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
    //  Parent root = FXMLLoader.load(getClass().getResource("ADD_CommentFXML.fxml"));
       //  Parent root = FXMLLoader.load(getClass().getResource("Show_PostFXML.fxml"));
      //Parent root = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Show_CommentFXML.fxml"));
         Parent root = FXMLLoader.load(getClass().getResource("GestionPostCommentaire.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
    }
 

