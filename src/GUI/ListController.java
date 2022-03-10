/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.UserItemController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.User;
import services.ServiceUser;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class ListController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private MyListener myListener;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField recherche;
    List<User> users;
    @FXML
    private Button btnrecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser us = new ServiceUser();
 

        users = us.afficherPersonnes();
        us.afficherParPrenom(recherche.getText());

        int column = 0;
        int row = 1;
      
           try {
            for (int i = 0; i < users.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("UserItem.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                UserItemController itemController = fxmlLoader.getController();

                itemController.setData(users.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     

        // TODO
    }




    @FXML
    private void recherche(ActionEvent event) {
    }

    @FXML
    private void btnrecherche(ActionEvent event) {
     
      

//              
//      
//       
//        users = us.afficherParPrenom(recherche.getText());
//
//        int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < users.size(); i++) {
//
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("UserItem.fxml"));
//
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                UserItemController itemController = fxmlLoader.getController();
//
//                itemController.setData(users.get(i));
//
//                if (column == 2) {
//                    column = 0;
//                    row++;
//                }
//
//                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(10));
//
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
