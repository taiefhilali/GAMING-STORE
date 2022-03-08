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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
//    @FXML
    @FXML
    private Button btnadd;
 private javafx.stage.Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    
    Servicepost spost=new Servicepost();
    
    @FXML
    private ComboBox<User> userTF;
    @FXML
    private Button btnadd1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<User> userslist = spost.getusersList();
            try {
                
            } catch (Exception ex) {
            }
            userTF.setItems(userslist);
            

            
            System.out.println(userslist);
            // TODO
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void ajouterpost(ActionEvent event) throws SQLException {
         DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        
        if (contenuTF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();

        } else {
             Date myDate = Date.valueOf(datePK.getValue().toString());
            
        
      //     spost.getusersList();
                if(ValidateFields()){
            if(ValidateFields2()){  
                
           spost.ajouterPost(new Post(contenuTF.getText(),titreTF.getText(),myDate,userTF.getValue()));
Alert alert =new Alert(Alert.AlertType.INFORMATION);//hethika l combo ? lee
        alert.setTitle("AJOUTER PUBLICATION!");
        alert.setHeaderText("information!");
        alert.setContentText("PUBLICATION A ETE AJOUTEE AVEC SUCCES!");
        alert.showAndWait();
            
         
            //Post p = new Post();
           // p.setContent(contenuTF.getText());
           // p.setTitle(titreTF.getText());
           // p.setId_user(new user(userTF));
         
        
        }
        
        }}}

    @FXML
    private void combo(ActionEvent event) throws SQLException {
        
         
    }

    @FXML
    private void ajretourpost(ActionEvent event) {
        
        try {
            root = FXMLLoader.load(getClass().getResource("GestionPostCommentaire.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
        
        
        
        
        
        
    }


    private static class user extends User {

        public user(TextField userTF) {
        }
    }
    
    private boolean ValidateFields() {
          Date myDate = Date.valueOf(datePK.getValue().toString());
          Date today= Date.valueOf(LocalDate.MAX);
        if(contenuTF.getText().isEmpty() | titreTF.getText().isEmpty() | userTF.getValue()==null|datePK.getValue()==null){
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
