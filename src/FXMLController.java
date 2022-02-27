/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import interfaces.Ipost;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Post;
import models.User;
import services.Servicepost;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField contenuTF;
    @FXML
    private TextField titreTF;
    @FXML
    private DatePicker datePK;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField userTF;
Servicepost spost=new Servicepost();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterpost(ActionEvent event) {
         DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        
        if (contenuTF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();

        } else {
            
         
            //Post p = new Post();
           // p.setContent(contenuTF.getText());
           // p.setTitle(titreTF.getText());
           // p.setId_user(new user(userTF));
         
          
               Date myDate = Date.valueOf(datePK.getValue().toString());
               
           spost.ajouterPost(new Post(contenuTF.getText(),titreTF.getText(),myDate,new User(Integer.parseInt(userTF.getText()))));

        
        }
    }

    private static class user extends User {

        public user(TextField userTF) {
        }
    }
    
    
}
