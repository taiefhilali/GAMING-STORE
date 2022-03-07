/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class InterfaceCodeQRController implements Initializable {

    @FXML
    private ImageView imageQR;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String filePath = "C:/Users/zaba2/Desktop/Functional APIs Lundi/Levelup/ProduitQR.png";

        // Affichage dans une liste view invisible
        File file = new File(filePath);
        Image im = new Image(file.toURI().toString());
        imageQR.setImage(im);
        imageQR.setFitHeight(150);
        imageQR.setFitWidth(250);

        //File file = new File(filePath);
        // TODO
    }

    @FXML
    private void exitQrScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./AddProductInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
