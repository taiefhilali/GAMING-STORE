/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;
import services.ServiceUser;
import utils.Session;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class UserItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Circle imageUser;
    @FXML
    private Label name;
    @FXML
    private Label adresse;
    @FXML
    private Label date;
    @FXML
    private Label tel;
    @FXML
    private Label role;
    @FXML
    private Button update;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private User user;

    public void setData(User user) {
        ServiceUser us = new ServiceUser();
        this.user = user;
        nameLabel.setText(user.getEmail());
        name.setText(user.getNom() + " " + this.user.getPrenom());
        adresse.setText(user.getAdresse());
        date.setText(user.getDns().toString());
        tel.setText(user.getTel());
        role.setText(user.getRole());

        File file = new File(user.getImage());
        Image im = new Image(file.toURI().toString());
        imageUser.setStroke(Color.BLACK);
        imageUser.setFill(new ImagePattern(im));
        delete.setOnAction(
                event -> {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("Confirmation de suppression");
                    alert1.setHeaderText(null);
                    alert1.setContentText(" Etes-vous sure de vouloir supprimer ce produit ? ");
                    Optional<ButtonType> action = alert1.showAndWait();

                    if (action.get() == ButtonType.OK) {
                        // Crud mte3ek
                        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                " Succés de suppression ! ", " Suppression du produit établie avec succés! ");

                        us.supprimerPersonne(user);
                        try {
                            root = FXMLLoader.load(getClass().getResource("./List.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();

                        } catch (IOException ex) {
                            Logger.getLogger(UserItemController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
        );

        update.setOnAction(
                event -> {
                    try {
                        switchToEditPage(event, user);
                    } catch (IOException ex) {
                        Logger.getLogger(UserItemController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    public void switchToEditPage(ActionEvent event, User user) throws IOException {
        switchPage(event, "./EditInterface.fxml", user);

    }

    public void switchPage(ActionEvent event, String path, User user) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setUserData(user);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
