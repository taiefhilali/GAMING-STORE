/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iclient;
import interfaces.Ilivreur;
import interfaces.Iuser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Client;
import models.User;
import netscape.javascript.JSObject;
import services.ServiceClient;
import services.ServiceLivreur;
import services.ServiceUser;
import utils.MaConnexion;

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
    private FileChooser fileChooser;
    @FXML
    private ComboBox sexe;
    @FXML
    private Button annuler;
    private ComboBox role;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button image;
    FileChooser fc = new FileChooser();
    File selectedFile;
    @FXML
    private WebView captcha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        sexe.getItems().setAll("Homme", "Femme");
        WebEngine engine = captcha.getEngine();

        engine.setUserAgent("Mozilla/5.0 (Linux) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.37");
        engine.setJavaScriptEnabled(true);
        engine.load("http://localhost/recaptcha.html");
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("clickController", new WebController());
                }
            }
        }
        );

//date.setValue(LocalDate.now().minusYears(5));
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

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void valider(ActionEvent event) throws IOException, SQLException {
       // System.out.println(WebController.key);
        WebController web =new WebController();
       // System.out.println(web.Verified());

        Iclient sc = new ServiceClient();
        Iuser su = new ServiceUser();
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher((CharSequence) email.getText());
        String regex = "[0-9]+";

        if (email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "E-mail est requis");
        } else if (!matcher.matches()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse E-mail non valide");
        } 
        else if (prenom.getText().isEmpty()) {
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
                    "Error!", "Numero de telephone est requis");

        } else if (!tel.getText().matches("[0-9]+") || tel.getText().length() != 8) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Numero de telephone  invalid");
        } else if (adresse.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse est requise");

        } else if (sexe.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Sexe est requis");
        }
         else if(web.Verified()==false){   
             showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Vous etes un robot");
         }
          else if (su.getByEmail(email.getText()).getId() != 0) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Adresse e-mail existe");
        }
         else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation d'ajout");
            alert1.setHeaderText(null);
            alert1.setContentText(" Etes-vous sure de l'inscription ? ");
            Optional<ButtonType> action = alert1.showAndWait();

            if (action.get() == ButtonType.OK) {
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        " Succés! ", " SUtlisiateur ajouté avec succés! ");

                User u = new User();
                Iuser us = new ServiceUser();
                Date myDate = Date.valueOf(date.getValue().toString());

                User c = new User(email.getText(), MD5.crypt(password.getText()), "client", prenom.getText(), nom.getText(), adresse.getText(),
                        tel.getText(), myDate);
                int i = (int) us.ajouterPersonne(c);

                Client client = new Client((String) sexe.getValue(), i);
                sc.ajouterPersonne(client);
                Connection cnx = MaConnexion.getInstance().getCnx();
                if (selectedFile != null) {
                    String req = "UPDATE `user` SET `image`= '" + selectedFile.getAbsolutePath().replace("\\", "/") + "' WHERE `id_user` = " + i + " ";

                    Statement st = cnx.createStatement();
                    st.executeUpdate(req);

                    // System.out.println(selectedFile.getAbsoluteFile());
                } 
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        " Succés! ", " Inscription etablie avec succés! ");
                root = FXMLLoader.load(getClass().getResource("./FX_Login.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
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

    @FXML
    private void image(ActionEvent event) {
//        FileChooser fc = new FileChooser();
//        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        selectedFile = fc.showOpenDialog(null);

//        if ((selectedFile.getAbsolutePath().contains("\\"))&&(selectedFile.getAbsolutePath().indexOf("\\", location-1)==(location-1))) {
//    stringValue=stringValue.substring(0,location-1);
//}
        // System.out.println(selectedFile.getAbsolutePath().replace("\\", "*"));
        // String s= selectedFile.getAbsolutePath().replace("*", "\\");
        //System.out.println(s);
    }

}
