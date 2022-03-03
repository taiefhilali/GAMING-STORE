/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iclient;
import interfaces.Ilivreur;
import interfaces.Iuser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Client;
import models.User;
import services.ServiceClient;
import services.ServiceLivreur;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button valider;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker date;
    @FXML
    private TextField tel;
    @FXML
    private TextField adresse;
    @FXML
    private TextField password;
    @FXML
    private ComboBox sexe;
    @FXML
    private Button annuler;
    private ComboBox role;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexe.getItems().setAll("Homme", "Femme");
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void valider(ActionEvent event) throws IOException {
        Iclient sc = new ServiceClient();
        if (email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "E-mail est requis");

        } else if (prenom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Prénom est requis");

        } else if (nom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Nom est requis");

        } else if (date.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Dtae est requise");

        } else if (tel.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Nulero de telephone est requis");

        } else if (adresse.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse est requise");

        } else if (sexe.getValue() == null) {
            showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                    "Succée!", "Sexe est requis");
        } else {
            User u = new User();
            Iuser us = new ServiceUser();
            Date myDate = Date.valueOf(date.getValue().toString());
            User c = new User(email.getText(), u.encrypt(password.getText()), "client",prenom.getText(),nom.getText(),adresse.getText(),tel.getText(), myDate);
            Client client = new Client((String) sexe.getValue(), (int) us.ajouterPersonne(c));
            sc.ajouterPersonne(client);
              showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Inscription Validée");
             root = FXMLLoader.load(getClass().getResource("./FX_Login.fxml"));
             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
            
        }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./FX_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
