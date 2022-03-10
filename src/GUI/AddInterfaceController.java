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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
import utils.MaConnexion;
import utils.Session;

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
    private Circle myCircle1;

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
//        File file = new File(Session.getImage());
//        Image im = new Image(file.toURI().toString());
//        myCircle1.setStroke(Color.SEAGREEN);
//        myCircle1.setFill(new ImagePattern(im));
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void submitAdd(ActionEvent event) throws IOException, Exception {
        Iuser su = new ServiceUser();

        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher((CharSequence) txtEmail.getText());
        if (txtNom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Nom est requis");

        } else if (txtLastName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Prénom est requis");

        } else if (!matcher.matches()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse E-mail non valide");
        } else if (txtPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Mot de passe est requis");

        } else if (role.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Sélectionner un role");

        } else if (su.getByEmail(txtEmail.getText()).getId() != 0) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Adresse e-mail existe");
        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation d'ajout");
            alert1.setHeaderText(null);
            alert1.setContentText(" Etes-vous sure d'ajouter ? ");
            Optional<ButtonType> action = alert1.showAndWait();

            if (action.get() == ButtonType.OK) {
                String img = "C:/Users/beldi/Desktop/Levelup/src/images/u.png";
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        " Succés! ", " Utlisiateur ajouté avec succés! ");
                Iuser us = new ServiceUser();
                Iclient sc = new ServiceClient();
                if (role.getValue().equals("administrateur")) {
                    User a = new User(txtEmail.getText(), MD5.crypt(txtPassword.getText()), "administrateur", txtNom.getText(), txtLastName.getText(), date);
                    int i = (int) us.ajouterPersonne(a);
                    Administrateur admin = new Administrateur("", i);

                    sa.ajouterPersonne(admin);

                    Connection cnx = MaConnexion.getInstance().getCnx();

                    String req = "UPDATE `user` SET `image`= '" + img + "' WHERE `id_user` = " + i + " ";

                    Statement st = cnx.createStatement();
                    st.executeUpdate(req);

                    // System.out.println(selectedFile.getAbsoluteFile());
                    root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    go(event);

                }

                if (role.getValue().equals("client")) {
                    User c = new User(txtEmail.getText(), MD5.crypt(txtPassword.getText()), "client", txtNom.getText(), txtLastName.getText(), date);
                    int i = (int) us.ajouterPersonne(c);
                    Client client = new Client("", i);
                    sc.ajouterPersonne(client);

                    Connection cnx = MaConnexion.getInstance().getCnx();

                    String req = "UPDATE `user` SET `image`= '" + img + "' WHERE `id_user` = " + i + " ";

                    Statement st = cnx.createStatement();
                    st.executeUpdate(req);

                    root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    go(event);

                }

                if (role.getValue().equals("fournisseur")) {
                    User f = new User(txtEmail.getText(), MD5.crypt(txtPassword.getText()), "fournisseur", txtNom.getText(), txtLastName.getText(), date);
                    int i = (int) us.ajouterPersonne(f);
                    Fournisseur fournissuer = new Fournisseur("07227308", "Arvea", i);
                    sf.ajouterPersonne(fournissuer);
                    Connection cnx = MaConnexion.getInstance().getCnx();

                    String req = "UPDATE `user` SET `image`= '" + img + "' WHERE `id_user` = " + i + " ";

                    Statement st = cnx.createStatement();
                    st.executeUpdate(req);

                    root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    go(event);
                }
                if (role.getValue().equals("livreur")) {
                    User l = new User(txtEmail.getText(), MD5.crypt(txtPassword.getText()), "livreur", txtNom.getText(), txtLastName.getText(), date);
                    int i = (int) us.ajouterPersonne(l);
                    Livreur livreur = new Livreur("", "", i);
                    sl.ajouterPersonne(livreur);
                    Connection cnx = MaConnexion.getInstance().getCnx();

                    String req = "UPDATE `user` SET `image`= '" + img + "' WHERE `id_user` = " + i + " ";

                    Statement st = cnx.createStatement();
                    st.executeUpdate(req);

                    root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    go(event);
                }

            }

        }

    }

    public void go(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    private void switchToUsers(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("./GestionUsers.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void switchToProfil(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("./Compte.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void switchToPassword(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("./PasswordUpdate.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void decoonecter(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("./FX_Login.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//        Session.setId(0);
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
