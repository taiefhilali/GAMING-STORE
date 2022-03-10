/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iuser;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class GetPasswordController implements Initializable {

    @FXML
    private TextField nametxt;
    @FXML
    private Button btnSignin;
    @FXML
    private Button verf;
    @FXML
    private Button btnSignin1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int randomCode;
    @FXML
    private TextField emailtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void send(ActionEvent event) {
        Iuser su = new ServiceUser();
        
        Random rand = new Random();
        randomCode = rand.nextInt(999999);
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher((CharSequence) emailtxt.getText());
        String regex = "[0-9]+";

        if (emailtxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "E-mail est requis");
        } 
        else if (su.getByEmail(emailtxt.getText()).getId() == 0) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Form Error!", "Adresse e-mail n'existe pas");
        }else if (!matcher.matches()) {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "Adresse E-mail non valide");
        } else {
            String to = emailtxt.getText();
            String subject = "Reseting Code";
            String message = "Votre Code est \n" + randomCode;

            try {
                Emailer.sendMail(to, message);
            } catch (Exception ex) {
                Logger.getLogger(GetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void Verify(ActionEvent event) throws IOException {
        if (Integer.valueOf(nametxt.getText()) == randomCode) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent view3 = fxmlLoader.load();
            ResetPasswordController controller = fxmlLoader.getController();
            controller.user = emailtxt.getText();
            Scene scene = new Scene(view3);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } else {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "erreur!", "Code erroné");
        }
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
