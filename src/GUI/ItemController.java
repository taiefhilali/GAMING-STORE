/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Produit;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
//    private ImageView img;

    private Produit produit;
    private MyListener myListener;
//    private ImageView imgFruit;
    @FXML
    private ImageView imgItemProduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produit);
    }

    public void setData(Produit produit, MyListener myListener) throws MalformedURLException {
        this.produit = produit;
        this.myListener = myListener;
        this.nameLabel.setText(produit.getNom());
        this.priceLable.setText("DT" + String.valueOf(produit.getPrix()));

        Image imn = new Image(
                "file:/" + produit.getImage());
        imgItemProduit.setImage(imn);
        System.out.println("file:/" + produit.getImage());
    }
}
