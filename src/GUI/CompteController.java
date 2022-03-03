/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iuser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;
import services.ServiceUser;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class CompteController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextField mail;
    @FXML
    private TextField tele;
    @FXML
    private TextField adr;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label email;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label adresse;
    @FXML
    private Label tel;
    @FXML
    private Label role;
    @FXML
    private Label role1;
      private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mail.setText(Session.getEmail());
        name.setText(Session.getNom());
        lastname.setText(Session.getPrenom());
        adr.setText(Session.getAdresse());
        tele.setText(Session.getTel());
        role1.setText(Session.getRole());
    }

    @FXML
    private void switchToProfil(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("./Compte.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToUsers(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./GestionUsers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Iuser us = new ServiceUser();
//        System.out.println(" "+Session.getId()+" "+  mail.getText()+" "+Session.getPassword()+" "+ Session.getRole()+" "+ name.getText()+" "+
//                lastname.getText()+" "+ adr.getText()+" "+ tele.getText()+" "+ Session.getDns());
        us.modifierPersonne(new User(Session.getId(), mail.getText(), Session.getPassword(), Session.getRole(), name.getText(),
                lastname.getText(), adr.getText(), tele.getText(), Session.getDns()));
        
        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                    "succès!", "Votre Compte est modifoé avec succé");                         
        Session.setEmail(mail.getText());
        Session.setNom(name.getText());
        Session.setPrenom(lastname.getText());
        Session.setAdresse(adr.getText());
        Session.setTel(tele.getText());
        
        root = FXMLLoader.load(getClass().getResource("./Compte.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        
    }

}
