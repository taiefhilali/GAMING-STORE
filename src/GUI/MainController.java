/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author beldi
 */
public class MainController implements Initializable {

    @FXML
    private Circle myCircle1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView top;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File(Session.getImage());
        Image im = new Image(file.toURI().toString());
        myCircle1.setStroke(Color.SEAGREEN);
        myCircle1.setFill(new ImagePattern(im));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Compte.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();
            grid.add(anchorPane, 0, 1); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO
    }

    @FXML
    private void switchToProfil(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Compte.fxml"));
            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();
            grid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToPassword(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PasswordUpdate.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();
            grid.setBackground(Background.EMPTY);
            grid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToUsers(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GestionUsers.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();
            grid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToDashboard(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Dashboard.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToProduct(ActionEvent event) {
                       try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Magasin.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void switchToPanier(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AffichagePanier.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void switchToCommande(ActionEvent event) {
         try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ClientCommandes.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToGestProd(ActionEvent event) {
             try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddProductInterface.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void switchToReclamation(ActionEvent event) {
                 try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ClientReclamation.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToStock(ActionEvent event) {
                    try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("DocFXML.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void switchToFacture(ActionEvent event) {
                try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FacFXML.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @FXML
    private void switchToForum(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../postgrid.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
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

    @FXML
    private void switchToCategorie(ActionEvent event) {
                  try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddCategoryInterface.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToTop(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TopProducts.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToCommandesAdmin(ActionEvent event) {
        try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AdminCommandes.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();
            grid.setBackground(Background.EMPTY);
            grid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToZoneLivreur(ActionEvent event) {
                 try {
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("LivreurReclamationZONE.fxml"));

            AnchorPane anchorPane;
            anchorPane = fxmlLoader.load();

            grid.add(anchorPane, 0, 1);
            //rid.add(anchorPane, 0, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
