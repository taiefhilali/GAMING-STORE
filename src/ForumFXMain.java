/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import chatbot.Chatbot;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import services.Servicepost;

/**
 *
 * @author msi
 */
public class ForumFXMain extends Application {
       private JTextField textEnter;
    private JTextArea textchat;
    private TextField t1;
    private TextArea t2;
    private Button button;
    private Label label;
     private MediaPlayer mediaPlayer;
     private TextFlow f;
    private Stage stage2;
    private Stage stage3;
    private Stage stage4;
    private Scene change;
    private Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        //****UPDATE POST+show******//
  // Parent root = FXMLLoader.load(getClass().getResource("UPDATE_PostFXML.fxml"));
     //Parent root = FXMLLoader.load(getClass().getResource("postgrid.fxml"));
   // Parent root = FXMLLoader.load(getClass().getResource("ADD_CommentFXML.fxml"));
       //  Parent root = FXMLLoader.load(getClass().getResource("Show_PostFXML.fxml"));
      //Parent root = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Show_CommentFXML.fxml"));
     //Parent root = FXMLLoader.load(getClass().getResource("Forumacceuil.fxml"));
   //Parent root = FXMLLoader.load(getClass().getResource("rating.fxml"));
   Parent root = FXMLLoader.load(getClass().getResource("postgrid.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();
    }

       
    public static void main(String[] args) {
        launch(args);
    }
 
    }
 

