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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import models.Produit;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class MarketController implements Initializable {

    @FXML
    private VBox chosenProductCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
//    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Produit produit;

    private Image image;
    private MyListener myListener;
    @FXML
    private ImageView imgMagasin;

    /**
     * Initializes the controller class.
     */
    private void setChosenProduct(Produit produit) {
        fruitNameLable.setText(produit.getNom());
        fruitPriceLabel.setText("DT" + produit.getPrix());
        
                // meth 1
        Image imn = new Image("file:/D:/Esprit/Semestre 2/PI-Dev/Week 3 Image attribute/Levelup/src/images/" + produit.getImage()
        );
        imgMagasin.setImage(imn);
        System.out.println(imgMagasin);
        System.out.println(imn);
                    // meth 3
                    
                    



                // meth 2
                
//        File file = new File(produit.getImage());
//        Image im1 = new Image(file.toURI().toString());
//        imgMagasin.setImage(im1);
//        chosenProductCard.setStyle("-fx-background-color: #" + ";\n"
//                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Iproduit sp = new ServiceProduit();
        List<Produit> produits = sp.afficherProduit();

        if (produits.size() > 0) {
            setChosenProduct(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosenProduct(produit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
