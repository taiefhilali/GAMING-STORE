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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Comment;
import models.Post;
import services.Servicepost;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ModifierpostController implements Initializable {

    private TextField contenuTF;
    private TextField titreTF;
    @FXML
    private Button update;
    private javafx.stage.Stage stage;
    private Scene scene;
    private Parent root;
    private int countEvents = 0;
    private Post post;
    @FXML
    private TextField contTF;
    @FXML
    private TextField titTF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void data(MouseEvent event) {
         if (countEvents == 0) { // Step 1
            Node node = (Node) event.getSource();
            javafx.stage.Stage stage = (javafx.stage.Stage) node.getScene().getWindow();
            // Step 2
            Post p = (Post) stage.getUserData();
            post = p;
            // Step 3
            titTF.setText(p.getTitle());
            contTF.setText(p.getContent());
            //txtEmail.setText(u.getEmail());

            countEvents++;

        }
    }

    @FXML
    private void modifpost(ActionEvent event) {
        Servicepost spost = new Servicepost();
        post.setContent(contTF.getText());
        post.setTitle(titTF.getText());
        spost.modifierPost(post);
        try {
            root = FXMLLoader.load(getClass().getResource("postgrid.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {

        }
    }
    
}
