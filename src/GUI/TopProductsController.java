/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.ICommande_elem;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.TopProduits;
import services.ServiceCommande_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TopProductsController implements Initializable {
private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button Retour;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    
    ICommande_elem sp2 = new ServiceCommande_elem();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.setRotate(circle1, true, 360, 10);
         this.setRotate(circle2, true, 180, 18);
          this.setRotate(circle3, true, 145, 24);
          
           List<TopProduits> p1 = new ArrayList<TopProduits>();
           p1 = sp2.TopProduits();
            int column = 0 ;
             int row = 1;
            try {
                for(TopProduits p2 : p1){
               FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("TopItem.fxml"));
                AnchorPane anchorpane = fxmlloader.load();
                 TopItemController panieritem = fxmlloader.getController();
                    panieritem.setData(p2);
                    
                    if(column == 1){
                    column = 0;
                    row++;
                }
                grid.add(anchorpane, column++, row);
                
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(anchorpane, new Insets(10));
            } 
           } catch (IOException ex) {
            Logger.getLogger(TopProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }    

    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void setRotate(Circle c, boolean reverse, int angle, int duration){
        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(18);
        rt.play();
    }
    
}
