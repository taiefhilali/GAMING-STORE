/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iadministrateur;
import interfaces.Iclient;
import interfaces.Ifournisseur;
import interfaces.Ilivreur;
import interfaces.Iuser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Administrateur;
import models.Client;
import models.Fournisseur;
import models.Livreur;
import models.User;
import services.ServiceAdministrateur;
import services.ServiceClient;
import services.ServiceFournisseur;
import services.ServiceLivreur;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class AddInterfaceController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox role;
    private Stage stage;
    private Scene scene;
    private Parent root;
    Ifournisseur sf = new ServiceFournisseur();
    Ilivreur sl = new ServiceLivreur();
    Iadministrateur sa = new ServiceAdministrateur();
    Date date = Date.valueOf(LocalDate.now());

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

        role.getItems().setAll("administrateur", "client", "fournisseur", "livreur");
        
    }

    @FXML
    private void submitAdd(ActionEvent event) throws IOException {
        if (txtNom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Nom est requis");
            return;
        } else if (txtLastName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Prénom est requis");
            return;
        } else if (txtEmail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "E-mail est requis");
            return;
        } else if (txtPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Mot de passe est requis");
            return;
        } else if (role.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Sélectionner un role");
            return;
        } else {
            Iuser us = new ServiceUser();
            Iclient sc = new ServiceClient();
            if (role.getValue().equals("administrateur")) {
                User a = new User(txtEmail.getText(), txtPassword.getText(), "administrateur", txtNom.getText(), txtLastName.getText(), date);
                Administrateur admin = new Administrateur("", (int) us.ajouterPersonne(a));
                sa.ajouterPersonne(admin);
                go(event);
            }

            if (role.getValue().equals("client")) {
                User c = new User(txtEmail.getText(), txtPassword.getText(), "client", txtNom.getText(), txtLastName.getText(), date);
                Client client = new Client("", (int) us.ajouterPersonne(c));
                sc.ajouterPersonne(client);
                go(event);

            }

            if (role.getValue().equals("fournisseur")) {
                User f = new User(txtEmail.getText(), txtPassword.getText(), "fournisseur", txtNom.getText(), txtLastName.getText(), date);
                Fournisseur fournissuer = new Fournisseur("07227308", "Arvea", (int) us.ajouterPersonne(f));
                sf.ajouterPersonne(fournissuer);
                go(event);
            }
            if (role.getValue().equals("livreur")) {
                User l = new User(txtEmail.getText(), txtPassword.getText(), "livreur", txtNom.getText(), txtLastName.getText(), date);
                Livreur livreur = new Livreur("", "", (int) us.ajouterPersonne(l));
                sl.ajouterPersonne(livreur);
                go(event);
            }

        }

    }

    public void go(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./ListInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ;

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./GestionUsers.fxml"));
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
    private void switchToProfil(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("./Compte.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
