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
        nameLabel.setText(produit.getNom());
        priceLable.setText("DT" + String.valueOf(produit.getPrix()));
//        D:\Esprit\Semestre 2\PI-Dev\Week 3 Image attribute\Levelup\src\images/
        Image imn = new Image("file:/D:/Esprit/Semestre 2/PI-Dev/Week 3 Image attribute/Levelup/src/images/"
                + produit.getImage(),
                false
        );
//       

//        File tempFile = new File("/D:/Esprit/Semestre 2/PI-Dev/Week 3 Image attribute/Levelup/src/images/");
//        Image imn = new Image(tempFile.toURI().toURL().toString());
//        imgItemProduit.setImage(imn);
//
//        File file = new File(produit.getImage());
//        Image im = new Image(file.toURI().toString());
//        imgItemProduit.setImage(im);
    }
}


