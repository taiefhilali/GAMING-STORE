package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private Circle myCircle;

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

            countEvents++;
            File file = new File(user.getImage());
            Image im = new Image(file.toURI().toString());
            myCircle.setStroke(Color.SEAGREEN);
            myCircle.setFill(new ImagePattern(im));
        }
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher((CharSequence) txtEmail.getText());

        if (txtEmail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "E-mail est requis");
        } else if (!matcher.matches()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse E-mail non valide");
        } else if (txtLastName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Prénom est requis");

        } else if (txtNom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Nom est requis");

        } else {

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation de modification");
            alert1.setHeaderText(null);
            alert1.setContentText(" Etes-vous sure de modifier ? ");
            Optional<ButtonType> action = alert1.showAndWait();

            if (action.get() == ButtonType.OK) {
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        " Succés! ", " Utlisiateur modifié avec succés! ");
                ServiceUser us = new ServiceUser();
                user.setEmail(txtEmail.getText());
                user.setNom(txtNom.getText());
                user.setPrenom(txtLastName.getText());
                us.modifierPersonne(user);
                root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        }

    }

    @FXML
    private void switchToList(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./List.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    private void decoonecter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./FX_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Session.setId(0);
        Session.setPrenom(null);
        Session.setNom(null);
        Session.setEmail(null);
        Session.setAdresse(null);
        Session.setPassword(null);
        Session.setRole(null);
        Session.setTel(null);
        Session.setDns(null);
        Session.setImage(null);
    }

}
