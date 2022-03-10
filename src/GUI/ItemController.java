/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Iproduit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Produit;
import services.ServiceProduit;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class ItemController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Iproduit sp = new ServiceProduit();
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
    @FXML
    private Label IdItem;
    @FXML
    private Label priceLable1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void click(ActionEvent event) throws IOException { // add details button?

    }

    public void setData(Produit produit, MyListener myListener) throws MalformedURLException {
        this.produit = produit;
        this.myListener = myListener;
        this.nameLabel.setText(produit.getNom());
        this.priceLable.setText(String.valueOf(produit.getPrix()) + " DT");
//        this.priceLable.setStrikethrough(true);
        this.IdItem.setText(String.valueOf(produit.getId_produit()));
        this.priceLable1.setText(String.valueOf(sp.returnPrixfinal(produit.getId_produit()) + " DT"));

        Image imn = new Image(
                "file:/" + produit.getImage());
        imgItemProduit.setImage(imn);
        System.out.println("file:/" + produit.getImage());
    }

    @FXML
    private void click(MouseEvent event) {
    }
}
