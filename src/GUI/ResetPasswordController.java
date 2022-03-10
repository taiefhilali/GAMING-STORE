/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import models.User;
import services.ServiceUser;
import utils.MaConnexion;

public class ResetPasswordController implements Initializable {

    @FXML
    private TextField passtxt;

    @FXML
    private TextField cpasstxt;

    @FXML
    private Button reset;
    PreparedStatement ps = null;
    ResultSet rs;
    int randomCode;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String user;
    @FXML
    private Button btnSignin1;

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    void reset(ActionEvent event) throws IOException {
        User u = new User();
        MaConnexion connexion;
        if (passtxt.getText() == null ? cpasstxt.getText() == null : passtxt.getText().equals(cpasstxt.getText())) {
            try {
                Connection con = MaConnexion.getInstance().getCnx();
                String sql = "UPDATE `user` SET password=? WHERE email = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, MD5.crypt(cpasstxt.getText()));
                ps.setString(2, user);
                ps.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        "succès!", "Mot de passe modifé avec succé");
                root = FXMLLoader.load(getClass().getResource("./FX_Login.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Erreur!", "Vérifier les mot de passe");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
