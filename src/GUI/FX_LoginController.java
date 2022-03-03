/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import interfaces.Iuser;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import models.User;
import services.ServiceUser;
import utils.MaConnexion;
import utils.Session;

/**
 *
 * @author oXCToo
 */
public class FX_LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    /// -- 
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Button btnSignup;
    Iuser sp = new ServiceUser();
    @FXML
    private Button btnSignin1;

    @FXML
    public void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("client")) {
                try {
                    //add you loading or delays - ;-)
                    root = FXMLLoader.load(getClass().getResource("./Compte.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
            if (logIn().equals("administrateur")) {
                try {
                    //add you loading or delays - ;-)

                    root = FXMLLoader.load(getClass().getResource("./Compte.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    public FX_LoginController() {
        con = MaConnexion.getInstance().getCnx();
    }

    //we gonna use string to check for status
    private String logIn() throws IOException {
        String status = "Success";
        String email = txtUsername.getText();
        User u = new User();
        String password = txtPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Entrer email et mot de passe");
            status = "Error";
        } else {
           Iuser s =new ServiceUser();
            //query
            if (sp.authentification(email, u.encrypt(password)).equals("client")) {
                s.getByEmail(email);
                
                Session.setId(s.getByEmail(email).getId());
                Session.setPrenom(s.getByEmail(email).getPrenom());
                Session.setNom(s.getByEmail(email).getNom());
                Session.setEmail(s.getByEmail(email).getEmail());
                Session.setAdresse(s.getByEmail(email).getAdresse());
                Session.setPassword(s.getByEmail(email).getPassword());
                Session.setRole(s.getByEmail(email).getRole());
                Session.setTel(s.getByEmail(email).getTel());
                Session.setDns(s.getByEmail(email).getDns());
                return "client";
            } else if (sp.authentification(email, u.encrypt(password)).equals("administrateur")) {
                Session.setId(s.getByEmail(email).getId());
                Session.setPrenom(s.getByEmail(email).getPrenom());
                Session.setNom(s.getByEmail(email).getNom());
                Session.setEmail(s.getByEmail(email).getEmail());
                Session.setAdresse(s.getByEmail(email).getAdresse());
                Session.setPassword(s.getByEmail(email).getPassword());
                Session.setRole(s.getByEmail(email).getRole());
                Session.setTel(s.getByEmail(email).getTel());
                Session.setDns(s.getByEmail(email).getDns());
                return "administrateur";
            } else if (sp.authentification(email, u.encrypt(password)).equals("livreur")) {
                Session.setId(s.getByEmail(email).getId());
                Session.setPrenom(s.getByEmail(email).getPrenom());
                Session.setNom(s.getByEmail(email).getNom());
                Session.setEmail(s.getByEmail(email).getEmail());
                Session.setAdresse(s.getByEmail(email).getAdresse());
                Session.setPassword(s.getByEmail(email).getPassword());
                Session.setRole(s.getByEmail(email).getRole());
                Session.setTel(s.getByEmail(email).getTel());
                Session.setDns(s.getByEmail(email).getDns());
                return "livreur";
            } else if (sp.authentification(email, u.encrypt(password)).equals("fournisseur")) {
             Session.setId(s.getByEmail(email).getId());
                Session.setPrenom(s.getByEmail(email).getPrenom());
                Session.setNom(s.getByEmail(email).getNom());
                Session.setEmail(s.getByEmail(email).getEmail());
                Session.setAdresse(s.getByEmail(email).getAdresse());
                Session.setPassword(s.getByEmail(email).getPassword());
                Session.setRole(s.getByEmail(email).getRole());
                Session.setTel(s.getByEmail(email).getTel());
                Session.setDns(s.getByEmail(email).getDns());
                return "fournisseur";
            } else {
                setLblError(Color.TOMATO, sp.authentification(email, u.encrypt(password)));
                status = "Error";
            }
        }

        return status;
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    @FXML
    public void forgotPsw(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GetPassword.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        scene.setRoot(root);
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./Inscription.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
