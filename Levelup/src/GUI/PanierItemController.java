/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.IPanier_elem;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.PanProd;
import models.Panier_elem;
import models.Produit;
import models.ProduitPanier;
import services.ServicePanier_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierItemController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    IPanier_elem sp = new ServicePanier_elem();

    private PanProd produit;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Button Supprimer;
    @FXML
    private Label id_produit;
    @FXML
    private Label id_elem;
    @FXML
    private Label quant;

    /**
     * Initializes the controller class.
     */
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(PanProd pt) {
        Image imn = new Image("file:/" + pt.getImage());
        this.produit = pt;
        this.nameLabel.setText(pt.getNomP());
        this.id_produit.setText(String.valueOf(pt.getIdPr()));
        this.id_elem.setText(String.valueOf(pt.getId_elem()));
        this.priceLable.setText(pt.getPrix().toString());
        this.img.setImage(imn);
        this.quant.setText(String.valueOf(pt.getQuantite()));
    }

    public void setData1(PanProd pt) {
        Image imn = new Image("file:/" + pt.getImage());
        this.produit = pt;
        this.nameLabel.setText(pt.getNomP());
        this.id_produit.setText(String.valueOf(pt.getIdPr()));
        this.id_elem.setText(String.valueOf(pt.getId_elem()));
        this.priceLable.setText(pt.getPrix().toString());
        this.img.setImage(imn);
    }

    public ProduitPanier getData() {
        ProduitPanier pt = new ProduitPanier();
        pt.setIdPr(Integer.parseInt(this.id_produit.getText()));
        pt.setId_elem(Integer.parseInt(this.id_elem.getText()));
        pt.setNomP(this.nameLabel.getText());
        pt.setPrix(Double.valueOf(this.priceLable.getText()));
        pt.setQuantite(Integer.parseInt(this.quant.getText()));
        return pt;
    }

    @FXML
    private void Supprimer(ActionEvent event) throws IOException {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Suppression !!!");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de Supprimer ce produit du panier ? ");
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK) {
            sp.supprimerElementPanier(getData().getId_elem());
            showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                    "succès!", "suppression du produit avec succès");

            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            alert1.close();
        }
    }

}
