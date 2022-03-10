/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iuser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
public class PasswordUpdateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private PasswordField ancien;
    @FXML
    private PasswordField nouveau;

    /**
     * Initializes the controller class.
     */
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        // TODO
    }

    private void switchToUsers(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./GestionUsers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToProfil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./Compte.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToPassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./PasswordUpdate.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifier(ActionEvent event) {

        User u = new User();
        Iuser su= new ServiceUser();
        String password = ancien.getText();
        if (ancien.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "ancien mot de passe est requis");
        } else if (nouveau.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "nouveau mot de passe est requis");

        } else {

            if (MD5.matches(Session.getPassword(),ancien.getText())) {
               
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Confirmation d'ajout");
                alert1.setHeaderText(null);
                alert1.setContentText(" Etes-vous sure de vouloir modifier votre mot de passe ? ");
                Optional<ButtonType> action = alert1.showAndWait();
                if (action.get() == ButtonType.OK) {
                      Date myDate = Date.valueOf(Session.getDns().toString());
                    su.modifierPersonne(new User(Session.getId(),
                            Session.getEmail(),
                            MD5.crypt(nouveau.getText()),
                            Session.getRole(),
                            Session.getNom(),
                            Session.getPrenom(),
                            Session.getAdresse(),
                            Session.getTel(),
                            Session.getDns(),
                            Session.getImage()));
                }
            } else {
                System.out.println(Session.getPassword()+"\n"+password);
                showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                        
                        "Error!", "ancien mot de passe est incorrecte");
            }
        }

    }

//      private void decoonecter(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("./FX_Login.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//       
//         Session.setId(0);
//        Session.setPrenom(null);
//        Session.setNom(null);
//        Session.setEmail(null);
//        Session.setAdresse(null);
//        Session.setPassword(null);
//        Session.setRole(null);
//        Session.setTel(null);
//        Session.setDns(null);
//        Session.setImage(null);
//    }
}
