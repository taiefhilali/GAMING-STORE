/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import interfaces.Icomment;
import interfaces.Ipost;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import models.Comment;
import models.Post;
import services.Servicecomment;
import services.Servicepost;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class PostgridController implements Initializable {
    private javafx.stage.Stage stage,stage1;
    private Scene scene,scene1;
    private Parent root,root1;
    @FXML
    private ScrollPane OffreScroll;
    @FXML
    private GridPane grid1;
    List<Post> posts = new ArrayList();
    @FXML
    private AnchorPane scenepane;
    @FXML
    private Label times;
    @FXML
    private TextField entocc;
    @FXML
    private TextField occc;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
                Timenow();

        Ipost pst = new Servicepost();
        posts = pst.afficherPost();
        int col = 0;
        int row = 0;
        try {
            for (int i = 0; i < posts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("postitem.fxml"));

                AnchorPane anchorpane = fxmlLoader.load();

                PostitemController itemController = fxmlLoader.getController();
                itemController.setData(posts.get(i));
//                System.out.println(post);

                if (col == 2) {
                    col = 0;
                    row++;
                }

                grid1.add(anchorpane, col++, row);
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);

                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onmouseclicked(MouseEvent event) {
      
    }

    private void stat(ActionEvent event) {
          try {
            root = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }

    @FXML
    private void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("do you want to logout ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (javafx.stage.Stage) scenepane.getScene().getWindow();
            System.out.println("you successfully logged out ");
            boolean stop = true;
            stage.close();
        }
    }

    private volatile boolean stop = false;

    private void Timenow() {
        Thread thread = new Thread(() -> {

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    times.setText(timenow);
                });
            }
        }
        );
        thread.start();

    }

    @FXML
    private void entocc(KeyEvent event) {
         Servicepost spost = new Servicepost();
        String k = null;
        if (event.getCode().equals(KeyCode.SPACE)) {
            k = (entocc.getText());
            occc.setText(spost.calculer_nbp(k));
        }
    }

    @FXML
    private void add(ActionEvent event) {
         try {
            root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

}
