/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iuser;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ServiceUser;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class DashboardController implements Initializable {

    @FXML
    private PieChart clientSexe;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label numClient;
    @FXML
    private Label numFournisseur;
    @FXML
    private Label numLivreur;
    @FXML
    private Label numAdmin;
    @FXML
    private Label nonactive;
    @FXML
    private Label actif;
    @FXML
    private Label clock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler() {

            @Override
            public void handle(Event event) {
                final Calendar cal = Calendar.getInstance();
                clock.setText(format.format(cal.getTime()));
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Iuser sp = new ServiceUser();
        numAdmin.setText(String.valueOf(sp.CalculerUser("administrateur")));
        numClient.setText(String.valueOf(sp.CalculerUser("client")));
        numFournisseur.setText(String.valueOf(sp.CalculerUser("fournisseur")));
        numLivreur.setText(String.valueOf(sp.CalculerUser("livreur")));
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Homme", 13),
                        new PieChart.Data("Femme", 25)
                );
        clientSexe.getData().addAll(pieChartData);
//        clientSexe.setTitle("Pourcentage");
        actif.setText(String.valueOf(sp.CalculerActive(false)));
        nonactive.setText(String.valueOf(sp.CalculerActive(true)));
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : clientSexe.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    double total = 0;
                    for (PieChart.Data d : clientSexe.getData()) {
                        total += d.getPieValue();
                    }
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    String text = String.format("%.1f%%", 100 * data.getPieValue() / total);
                    caption.setText(text);

                }
            });
        }

        for (final PieChart.Data data : clientSexe.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {

                    }
            );
        }

        // TODO
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
