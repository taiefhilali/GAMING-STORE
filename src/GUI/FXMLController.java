/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField nomProduitTF;
    @FXML
    private TextField referenceTF;
    @FXML
    private TextField categorieTF;
    @FXML
    private TextField prixTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField fournisseurTF;
    @FXML
    private TextField promotionTF;
    @FXML
    private TableView<?> afficheTV;
    @FXML
    private TableColumn<?, ?> colNomProd;
    @FXML
    private TableColumn<?, ?> colReference;
    @FXML
    private TableColumn<?, ?> colCategorie;
    @FXML
    private TableColumn<?, ?> colPrix;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colFournisseur;
    @FXML
    private TableColumn<?, ?> colPromotion;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSuppression;
    @FXML
    private Button btnModif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleButtonAction3(ActionEvent event) {
    }

    @FXML
    private void handleButtonAction2(ActionEvent event) {
    }
    
}
