/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class EditInterfaceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user;
    private int countEvents = 0;

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private AnchorPane ap;

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

    @FXML
    private void receiveData(MouseEvent event) {
        if (countEvents == 0) { // Step 1
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // Step 2
            User u = (User) stage.getUserData();
            user = u;
            // Step 3
            txtNom.setText(u.getNom());
            txtLastName.setText(u.getPrenom());
            txtEmail.setText(u.getEmail());
            txtPassword.setText(u.getPassword());
            countEvents++;
        }
    }

    @FXML
    private void update(ActionEvent event) {
        ServiceUser us = new ServiceUser();
        user.setEmail(txtEmail.getText());
        user.setNom(txtNom.getText());
        user.setPrenom(txtLastName.getText());
        user.setPassword(txtPassword.getText());
        us.modifierPersonne(user);

        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "modification d'utilisateur avec succès");

    }

    @FXML
    private void switchToList(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./ListInterface.fxml"));
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

}
