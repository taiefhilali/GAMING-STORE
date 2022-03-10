/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iuser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import models.Client;
import models.User;
import services.ServiceUser;
import utils.MaConnexion;
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
    
    @FXML
    private Circle myCircle;
    @FXML
    private Label prenom1;
    @FXML
    private DatePicker date;
    FileChooser fc = new FileChooser();
    File selectedFile;

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
        date.setValue(Session.getDns().toLocalDate());
        //System.out.println(Session.getImage());
        File file = new File(Session.getImage());
        Image im = new Image(file.toURI().toString());

        myCircle.setStroke(Color.SEAGREEN);
        myCircle.setFill(new ImagePattern(im));
//       Image im1 = new Image(Session.getImage(), false);
     
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
          date.setDayCellFactory(param -> new DateCell() {
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isAfter(LocalDate.now().minusYears(5))) {
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                    setDisable(true);

                    //  addEventFilter(MouseEvent.MOUSE_CLICKED, e -> e.consume());
                }
            }
        });

    }


    @FXML
    private void modifier(ActionEvent event) throws IOException {
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher((CharSequence) mail.getText());

        if (mail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "E-mail est requis");
        } else if (!matcher.matches()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse E-mail non valide");
        } else if (name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Prénom est requis");

        } else if (lastname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Nom est requis");

        } else if (date.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Dtae est requise");

        } else if (adr.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse est requise");

        } else if (tele.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Numero de telephone est requis");

        } else if (!tele.getText().matches("[0-9]+") || tele.getText().length() != 8) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Numero de telephone  invalid");
        } else {

            Iuser us = new ServiceUser();
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation de modification");
            alert1.setHeaderText(null);
            alert1.setContentText(" Etes-vous sure de modifier ? ");
            Optional<ButtonType> action = alert1.showAndWait();

            if (action.get() == ButtonType.OK) {
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        " Succés! ", " Compte modifié avec succés! ");

                Date myDate = Date.valueOf(date.getValue().toString());

                if (selectedFile != null) {
                    us.modifierPersonne(new User(Session.getId(), mail.getText(), Session.getPassword(), Session.getRole(), name.getText(),
                            lastname.getText(), adr.getText(), tele.getText(), myDate, selectedFile.getAbsolutePath().replace("\\", "/")));

                    File file = new File(Session.getImage());
                    Image im = new Image(file.toURI().toString());

                    Session.setImage(selectedFile.getAbsolutePath().replace("\\", "/"));

                    myCircle.setStroke(Color.SEAGREEN);
                    myCircle.setFill(new ImagePattern(im));
                       root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


                    // System.out.println(selectedFile.getAbsoluteFile());
                } else {
                    us.modifierPersonne(new User(Session.getId(), mail.getText(), Session.getPassword(), Session.getRole(), name.getText(),
                            lastname.getText(), adr.getText(), tele.getText(), myDate, Session.getImage()));
                }

                Session.setEmail(mail.getText());
                Session.setNom(name.getText());
                Session.setPrenom(lastname.getText());
                Session.setAdresse(adr.getText());
                Session.setTel(tele.getText());
                Session.setDns(myDate);

                root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    private void image(ActionEvent event) {
        selectedFile = fc.showOpenDialog(null);
    }

   

}
