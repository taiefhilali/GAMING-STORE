/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
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
import models.Comment;
import javafx.stage.Stage;
import services.Servicecomment;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ModifiercommentController implements Initializable {

    @FXML
    private TextField labTF;
    @FXML
    private TextField commTF;
    @FXML
    private Button update;
    private javafx.stage.Stage stage;
    private Scene scene;
    private Parent root;
    private int countEvents = 0;
    private Comment comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void modifier(ActionEvent event) {

        Servicecomment scomment = new Servicecomment();
//                setContenu(commTF.getText());
//                selectedPost.setLabel(labTF.getText());
        //post.setId_user(userTF.getText().);

        //post.setDatep(DATEP);
        comment.setContenu(commTF.getText());
        comment.setLabel(labTF.getText());
        scomment.modifierComment(comment);
        try {
            root = FXMLLoader.load(getClass().getResource("commentgrid.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {

        }

    }

    @FXML
    private void receiveData(MouseEvent event) {

        if (countEvents == 0) { // Step 1
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // Step 2
            Comment c = (Comment) stage.getUserData();
            comment = c;
            // Step 3
            labTF.setText(c.getLabel());
            commTF.setText(c.getContenu());
            //txtEmail.setText(u.getEmail());

            countEvents++;

        }

    }

    private void rett(ActionEvent event) {
          try {
            root = FXMLLoader.load(getClass().getResource("commentgrid.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void retour(ActionEvent event) {
          try {
            root = FXMLLoader.load(getClass().getResource("commentgrid.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

}
