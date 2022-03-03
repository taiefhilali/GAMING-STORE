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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ListInterfaceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    ServiceUser us = new ServiceUser();

    @FXML
    private TableView<User> tableViewId = new TableView<>();

    @FXML
    private TableColumn<User, String> email = new TableColumn<>();
    @FXML
    private TableColumn<User, String> nom = new TableColumn<>();
    @FXML
    private TableColumn<User, String> prenom = new TableColumn<>();
    @FXML
    private TableColumn<User, String> tel = new TableColumn<>();
    @FXML
    private TableColumn<User, String> adresse = new TableColumn<>();

    @FXML
    private TableColumn<User, String> role = new TableColumn<>();
    @FXML
    private TableColumn<User, User> action = new TableColumn<>("Action");

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

        List<User> users = us.afficherPersonnes();
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        action.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );

        action.setCellFactory(param -> new TableCell<User, User>() {

            private final Button deleteButton = new Button("Supprimer");
            private final Button editButton = new Button("Modifier");

            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (user == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(new HBox(editButton, deleteButton));

                deleteButton.setOnAction(
                        event -> {
                            us.supprimerPersonne(user);

                            showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                    "succès!", "suppression d'utilisateur etablie avec succès");
                            getTableView().getItems().remove(user);

                        }
                );

                editButton.setOnAction(
                        event -> {
                            try {

                                switchToEditPage(event, user);

                            } catch (IOException ex) {
                                Logger.getLogger(ListInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                );

            }
        });

        for (User user : users) {
            tableViewId.getItems().add(new User(user.getId(), user.getEmail(), user.getPassword(), user.getRole(), user.getNom(), user.getPrenom(), user.getAdresse(), user.getTel(), user.getDns()));
        }
        // TODO
    }

    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        switchPage(event, "./GestionUsers.fxml", null);
    }

    public void switchToEditPage(ActionEvent event, User user) throws IOException {
        switchPage(event, "./EditInterface.fxml", user);

    }

    public void switchPage(ActionEvent event, String path, User user) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setUserData(user);

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
