/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.IPanier_elem;
import interfaces.Iproduit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Panier_elem;
import models.Produit;
import services.ServicePanier_elem;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class SelectedItemMarketController implements Initializable {

    Produit p;
    Iproduit sp = new ServiceProduit();
    IPanier_elem sElem = new ServicePanier_elem();
    FX_LoginController fx = new FX_LoginController();
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView itemIMG;
    @FXML
    private Label prodName;
    @FXML
    private Label prodRef;
    @FXML
    private Label prodPrix;
    @FXML
    private Label prodPromo;
    @FXML
    private Label prodDescription;
    @FXML
    private Spinner<Integer> SpinnerProd;
    @FXML
    private Button btnRetour;
    @FXML
    private Label presenceStockLabel;
    @FXML
    private Button butt;
    @FXML
    private Label id_prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Produit p) {
        this.p = sp.retrieveProductById(p.getId_produit());
        prodName.setText(p.getNom());
        prodRef.setText("Référence :\n" + p.getReference());
        prodPrix.setText(String.valueOf(p.getPrix()));
        prodPromo.setText(String.valueOf(p.getPromotion()));
        Image imn = new Image("file:/" + p.getImage());
        prodDescription.setText("Détails :\n" + p.getDescription());
        itemIMG.setImage(imn);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
        valueFactory.setValue(1);
        SpinnerProd.setValueFactory(valueFactory);
//       pt.setQuantite(this.SpinnerProd.getValue());
    }

    @FXML
    private void switchBackToMarket(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Main.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   public Produit getData() {
        Produit prod = new Produit();
        prod.setId_produit(Integer.parseInt(this.id_prod.getText()));
        prod.setNom(prodName.getText());
        prod.setReference(prodRef.getText());
        prod.setPrix(Double.parseDouble(prodPrix.getText()));
        prod.setPromotion(Double.parseDouble(prodPromo.getText()));
        //prod.setImage(itemIMG);
//       pt.setQuantite(this.SpinnerProd.getValue());
        return p;
    }
      @FXML
    private void ajouter(ActionEvent event) throws IOException {
          
        Produit p = new Produit();
        p = getData();
        Panier_elem p1 = new Panier_elem(fx.p,p,this.SpinnerProd.getValue());
        sElem.AjouterElementPanierQ(p1);
        System.out.println(p1);
        root = FXMLLoader.load(getClass().getResource("./Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

//        public Produit getData() {
//        Produit prod = new Produit();
//        prod.setId_produit(Integer.parseInt(this.id_prod.getText()));
//        prod.setNom(prodName.getText());
//        prod.setReference(prodRef.getText());
//        prod.setPrix(Double.valueOf(prodPrix.getText()));
//        prod.setPromotion(Double.valueOf(prodPromo.getText()));
//        //prod.setImage(this.itemIMG);
////       pt.setQuantite(this.SpinnerProd.getValue());
//        return p;
//    }


    

