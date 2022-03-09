/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import models.Comment;
import services.Servicecomment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class FxmlcController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label contenu;
    @FXML
    private Label reqsp;
    private Comment comment;
    @FXML
    private Button supp;
    @FXML
    private Button upd;

    //private Parent root;
    //private Label idpost;
    /**
     *
     * Initializes the controller class.
     */
    private javafx.stage.Stage stage, stage1;
    private Scene scene, scene1;
    private Parent root, root1;

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
    }
    // TODO

    void setData(Comment comment) {

        this.comment = comment;
        label.setText(comment.getLabel());
        contenu.setText(comment.getContenu());
        reqsp.setText(String.valueOf(comment.getResp()));
        System.out.println("mmmm");
        upd.setOnAction(
                event -> {
//                    try { 

try {
//            root = FXMLLoader.load(getClass().getResource("commentgrid.fxml"));
//            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//        }
                        switchToEditPage(event, comment);
                    } catch (IOException ex) {

                    }
                }
        );
        //  idpost.setText(String.valueOf(comment.getId_post()));

    }

    @FXML
   private void delete(ActionEvent event) {

        Servicecomment scomment = new Servicecomment();
        scomment.supprimerComment(comment);

        try {
            root = FXMLLoader.load(getClass().getResource("commentgrid.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }

//    public void pass() {
//        upd.setOnAction(kk -> {
//            Servicecomment scomment = new Servicecomment();
//            try {
//                root = FXMLLoader.load(getClass().getResource("Show_CommentFXML.fxml"));
//            } catch (IOException ex) {
//
//            }
//            showAlert(Alert.AlertType.INFORMATION, ((Node) kk.getSource()).getScene().getWindow(),
//                    "go update!", "modifier votre commentaire");
//
//        });
//    }

    public void switchToEditPage(ActionEvent event, Comment comment) throws IOException {
        switchPage(event, "./modifiercomment.fxml", comment);

    }

    public void switchPage(ActionEvent event, String path, Comment comment) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setUserData(comment);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
    

   

//    @FXML
//    private void delete(ActionEvent event) {
//    }

}
