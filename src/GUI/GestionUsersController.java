package GUI;

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

/**
 * FXML Controller class
 *
 * @author msi
 */
public class GestionUsersController implements Initializable {

    @FXML
    private Button switchToAddInterface;
private javafx.stage.Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToProfil(ActionEvent event) {
    }

    @FXML
    private void switchToUsers(ActionEvent event) {
    }

    @FXML
    private void switchToAddInterface(ActionEvent event) {
        
            try {  
            root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
    }

//    @FXML
//    private void switchToListInterface(ActionEvent event) {
//    }
    
}}
