/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.Icategorie;
import interfaces.Iproduit;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Categorie;
import models.Produit;
import services.ServiceCategorie;
import services.ServiceProduit;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class AddCategoryInterfaceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Icategorie sc = new ServiceCategorie();

    @FXML
    private TableView<Categorie> tv_categories;
    @FXML
    private TableColumn<Categorie, Integer> col_idCat;
    @FXML
    private TableColumn<Categorie, String> col_nomCategorie;
    @FXML
    private Button btn_addCategorie;
    @FXML
    private Button btn_deleteCategorie;
    @FXML
    private Button btn_updateCategorie;
    @FXML
    private Button btn_retour;

    @FXML
    private TextField nomCategorieTF;
    @FXML
    private Label label_insererNom;
    @FXML
    private Label label_InsererID;
    @FXML
    private TextField IdCategorieTF;

    /**
     * Initializes the controller class.
     */
    // Crud Affichage des catégories
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Categorie> listCat = sc.afficherCategorie();
        col_idCat.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        col_nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
//        tv_categories.setItems(listcat);
        for (Categorie c : listCat) {
            tv_categories.getItems().add(new Categorie(c.getId_categorie(), c.getNom_categorie()));
        }
    }

    // Passage vers l'écran d'accueil par un bouton de retour
    @FXML
    private void switchTo(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("./SelectionInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // Création d'une alerte lors du crud
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void addCategorie(ActionEvent event) throws IOException {

        sc.ajouterCategorie(new Categorie(nomCategorieTF.getText()));

        root = FXMLLoader.load(getClass().getResource("./AddCategoryInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                " Succés! ", " Ajout de la catégorie établie avec succés! ");

    }

    @FXML
    private void deleteCategorie(ActionEvent event) throws IOException {
        sc.supprimerCategorie(new Categorie(Integer.parseInt(IdCategorieTF.getText())));

        root = FXMLLoader.load(getClass().getResource("./AddCategoryInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                " Succés  de suppression! ", " Suppression de la catégorie établie avec succés! ");

    }

    @FXML
    private void updateCategorie(ActionEvent event) throws IOException {
        sc.modifierCategorie(new Categorie(Integer.parseInt(IdCategorieTF.getText()), nomCategorieTF.getText()));

        root = FXMLLoader.load(getClass().getResource("./AddCategoryInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                " Succés de modification! ", " Modification de la catégorie établie avec succés! ");
    }

    @FXML
    private void switchTo(MouseEvent event) {
    }

}
