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
import interfaces.IPanier;
import java.io.File;

import interfaces.Iuser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import models.Panier;
import models.User;
import services.ServiceUser;
import services.servicePanier;
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

    @FXML
    private BorderPane borderPane;
    private HBox hbox;
    private Media media;
    private MediaPlayer mediaPlayer;

    static Panier p = new Panier();
    IPanier spanier = new servicePanier();
    private File file = new File("C:/Users/beldi/Desktop/media.mp4");

    private String Media_URL = file.toURI().toString();
    private Button btnPlay;
    private Button btnPause;

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
    private MediaView mediaView;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        media = new Media(Media_URL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
     //  mediaPlayer.setOnReady(() -> stage.sizeToScene());        
        mediaView.setMediaPlayer(mediaPlayer);

        hbox = new HBox();

        borderPane.setBottom(hbox);
    }

    public void session(String email) {
        Iuser s = new ServiceUser();
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
        Session.setImage(s.getByEmail(email).getImage());
        p = spanier.getElement(Session.getId());
        
    }

    public FX_LoginController() {
        con = MaConnexion.getInstance().getCnx();
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
        mediaPlayer.pause();
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./Inscription.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        mediaPlayer.pause();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        String email = txtUsername.getText();
        User u = new User();
        Iuser su = new ServiceUser();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Entrer email et mot de passe");
        } else {

            String test = sp.authentification(email, password);
            if (test.equals("client")) {
                session(email);
                root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                mediaPlayer.pause();

            } else if (test.equals("administrateur")) {
                session(email);
                root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                mediaPlayer.pause();

            } else if (test.equals("livreur")) {
                session(email);
                root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                mediaPlayer.pause();

            } else if (test.equals("fournisseur")) {
                session(email);
                root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                mediaPlayer.pause();

            } else {
                setLblError(Color.TOMATO, test);
            }
        }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void clicked(MouseEvent event) {
        Status currentStatus = mediaPlayer.getStatus();
        if (currentStatus == Status.PLAYING) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }

    }

    @FXML
    private void exited(MouseEvent event) {

    }

}
