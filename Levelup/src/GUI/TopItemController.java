/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.PanProd;
import models.TopProduits;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TopItemController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label NomP;
    @FXML
    private Label PrixP;
    @FXML
    private Label DescP;
    @FXML
    private Label nbrFois;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void setData(TopProduits pt){
        Image imn = new Image("file:/" + pt.getP().getImage());
        this.NomP.setText(pt.getP().getNom());
        this.PrixP.setText(String.valueOf(pt.getP().getPrix()));
        this.DescP.setText(pt.getP().getDescription());
        this.nbrFois.setText(String.valueOf(pt.getNbrachat()));
        this.img.setImage(imn);
    }
}
