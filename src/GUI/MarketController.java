/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iproduit;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import models.Produit;
import services.ServiceProduit;
import sun.security.provider.ConfigFile;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class MarketController implements Initializable {
//    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Produit produit;
    Iproduit sp = new ServiceProduit();
    private Image image;
    private MyListener myListener;
    private ImageView imgMagasin;
    @FXML
    private TextField chercherMagasin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Iproduit sp = new ServiceProduit();
        List<Produit> produits = sp.afficherProduit();
        try {
            afficherProduitsMagasin(produits);
        } catch (IOException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherProduitsMagasin(List<Produit> produits) throws IOException {

        int column = 0;
        int row = 1;

        for (int i = 0; i < produits.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemProduit.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemController itemController = fxmlLoader.getController();

            itemController.setData(produits.get(i), myListener);

            if (column == 2) {
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
    }

    @FXML
    private void chercherProduitsMag(KeyEvent event) throws IOException {
        grid.getChildren().clear();
        List<Produit> p = sp.afficherProduit();
        System.out.println(p);
        afficherProduitsMagasin(sp.chercherProduitDynamiquement(chercherMagasin.getText(), p));

    }
}
