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
public class ADD_PostFXMLController implements Initializable {

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
        if(ValidateFields()){
            if(ValidateFields2()){  
        if ((contenuTF.getText().length() == 0)&&(titreTF.getText().length() == 0)&&(userTF.getText().length() == 0)) {
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

        }}
        }
    }

    private static class user extends User {

        public user(TextField userTF) {
        }
    }
    
    private boolean ValidateFields() {
          Date myDate = Date.valueOf(datePK.getValue().toString());
        if(contenuTF.getText().isEmpty() | titreTF.getText().isEmpty() | datePK.getValue().equals(myDate) |  userTF.getText().isEmpty()) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("Please enter into the fields");
        alert.showAndWait();
        return false;
        }
        return true;
    }
    private boolean ValidateFields2() {
        Servicepost bs= new Servicepost();
        if(Integer.parseInt(bs.calculer_nbp(contenuTF.getText())) != 0) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a non existant course name");
       // bs.calculer_nbseance(Add_Nom_Co.getText())
       //alert.setContentText(bs.calculer_nbseance(Add_Nom_Co.getText()));
        alert.showAndWait();
        return false;
        }
        return true;
    }

}
