/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.Comment;
import models.Post;
import models.User;
import services.Servicecomment;
import styles.ToggleSwitch;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ADD_CommentFXMLController implements Initializable {

    private TextField labelTF;
    private TextField contenuTF;
    private TextField respTF;

    /**
     * Initializes the controller class.
     */
    Servicecomment scomment =new Servicecomment();
   
    private TextField postT;
    @FXML
    private TextField cntTF;
    @FXML
    private TextField labTF;
    @FXML
    private TextField repTF;
    @FXML
    private ComboBox<Post> pstTF;
    private AnchorPane paneMain;
        private javafx.stage.Stage stage;
    private Scene scene;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            ObservableList<Post> postslist = scomment.getpostsList();
            try {
                
            } catch (Exception ex) {
            }
            pstTF.setItems(postslist);
            

            
            System.out.println(postslist);
            // TODO
            
        } catch (SQLException ex) {
        }
//        ToggleSwitch button =new ToggleSwitch();
//    
//        SimpleBooleanProperty isON=button.switchOnProperty();
//        isON.addListener((observable,oldValue,newValue) ->{
//        
//        
//        
//        
//        if(newValue){
//        
//        button.getScene().getRoot().getStylesheets().add(getClass().getResource("styles.css").toString());
//            System.out.println("adding css dark");
// }else {        button.getScene().getRoot().getStylesheets().remove(getClass().getResource("styles.css").toString());
//            System.out.println("removing css dark");
//
//        }
//        });
//        
////        paneMain.getChildren().add(button); 
//    }    

    }


    @FXML
    private void addcmnt(ActionEvent event) {
       // System.out.println(cntTF.getText().length());
           if (cntTF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();

        } else {     
              //Post p= new Post(Integer.parseInt(pstTF.ge()));
            
              // System.out.println(c.toString());
               scomment.ajouterComment(new Comment(cntTF.getText(), labTF.getText(),Integer.parseInt(repTF.getText()),pstTF.getValue()));
Alert alert =new Alert(Alert.AlertType.INFORMATION);//
        alert.setTitle("AJOUTER PUBLICATION!");
        alert.setHeaderText("information!");
        alert.setContentText("PUBLICATION A ETE AJOUTEE AVEC SUCCES!");
        alert.showAndWait();
               

        }
    }

public void GettextVal(String txtval)
          {
             System.out.println("text value from one controller - "+txtval);
          }

    @FXML
    private void returcmnt(ActionEvent event) {
          try {
            root = FXMLLoader.load(getClass().getResource("GestionPostCommentaire.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }
       
        
}
