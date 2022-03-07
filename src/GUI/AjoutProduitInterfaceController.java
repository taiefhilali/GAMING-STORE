/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Icategorie;
import interfaces.Iproduit;
import interfaces.Iuser;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Produit;
import services.ServiceCategorie;
import services.ServiceProduit;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class AjoutProduitInterfaceController implements Initializable {

    @FXML
    private TextField id_Produit;
    @FXML
    private TextField nomProduitTF;
    @FXML
    private TextField referenceTF;
    @FXML
    private ComboBox<?> comboBoxCat;
    @FXML
    private TextField prixTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private ComboBox<?> comboFourn;
    @FXML
    private TextField promotionTF;
    @FXML
    private Button btnAjout;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Interface pour le crud d'ajout produit seuelemnt

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void ajouterProduitAction(ActionEvent event) throws IOException {

        Iproduit sp = new ServiceProduit();
        Iuser su = new ServiceUser();
        Icategorie sc = new ServiceCategorie();

        // Affichage d'une alerte de confirmation
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation d'ajout");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de vouloir ajouter ce produit ? ");
        Optional<ButtonType> action = alert1.showAndWait();

        if (action.get() == ButtonType.OK) {
            ///
            // ajout des produits
            sp.ajouterProduit(new Produit(nomProduitTF.getText(),
                    referenceTF.getText(),
                    sc.retrieveCategorieByNom((String) comboBoxCat.getValue()),
                    Double.parseDouble(prixTF.getText()),
                    descriptionTF.getText(),
                    su.getByEmail((String) comboFourn.getValue()),
                    Double.parseDouble(promotionTF.getText())));

            root = FXMLLoader.load(getClass().getResource("./AddProductInterface.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                    " Succés d'ajout ! ", " Ajout du produit établie avec succés! ");
        } else {
            alert1.close();
        }

    }

}
