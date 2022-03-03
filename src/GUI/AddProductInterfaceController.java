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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Categorie;
import models.Produit;
import models.User;
import services.ServiceCategorie;
import services.ServiceProduit;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class AddProductInterfaceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nomProduitTF;
    @FXML
    private TextField referenceTF;

    @FXML
    private TextField prixTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField promotionTF;
    @FXML
    private TableColumn<Produit, String> colNomProd;
    @FXML
    private TableColumn<Produit, String> colReference;
    @FXML
    private TableColumn<Produit, Categorie> colCategorie;
    @FXML
    private TableColumn<Produit, Double> colPrix;
    @FXML
    private TableColumn<Produit, String> colDescription;
    @FXML
    private TableColumn<Produit, User> colFournisseur;
    @FXML
    private TableColumn<Produit, Double> colPromotion;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSuppression;
    @FXML
    private Button btnModif;
    @FXML
    private Button btn_retour;
    @FXML

    private TableView<Produit> productsTV;
    Iproduit sp = new ServiceProduit();
    Iuser su = new ServiceUser();
    @FXML
    private ComboBox comboBoxCat;
    @FXML
    private ComboBox comboFourn;
    @FXML
    private TableColumn<Produit, Integer> colIdProd;
    @FXML
    private TextField ChercheTF;
    @FXML
    private TableColumn<Produit, Produit> col_Action = new TableColumn<>("Calculer Promotion");
    @FXML
    private TextField id_Produit;

    /**
     * Initializes the controller class.
     */
    // Création d'une alerte lors du crud
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

        productsTV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (productsTV.getSelectionModel().getSelectedItem() != null) {
                    Produit selectedProduit = productsTV.getSelectionModel().getSelectedItem();
//                    System.out.println(selectedProduit);
                    id_Produit.setText(String.valueOf(selectedProduit.getId_produit()));
                    nomProduitTF.setText(selectedProduit.getNom());
                    referenceTF.setText(selectedProduit.getReference());
                    comboBoxCat.setValue(selectedProduit.getCategorie().getNom_categorie());
                    String p = String.valueOf(selectedProduit.getPrix());
                    prixTF.setText(p);
                    descriptionTF.setText(selectedProduit.getDescription());
                    comboFourn.setValue(selectedProduit.getUser().getEmail());
                    String a = String.valueOf(selectedProduit.getPromotion());
                    promotionTF.setText(a);

                }

            }

        });

        // Affichage du combo box 1
        Icategorie sc = new ServiceCategorie();
        List<Categorie> Categories = sc.afficherCategorie();
        Categories.forEach((c) -> {
            comboBoxCat.getItems().add(c.getNom_categorie());
        });
        // Affichage du combo box 2
        Iuser su = new ServiceUser();
        List<User> listUser = su.afficherPersonnes();
        listUser.forEach((u) -> {
            comboFourn.getItems().add(u.getEmail());
        });
        // 
        List<Produit> listProd = sp.afficherProduit();
        colNomProd.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colFournisseur.setCellValueFactory(new PropertyValueFactory<>("user"));
        colPromotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        colIdProd.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        listProd.forEach((p) -> {
            productsTV.getItems().addAll(new Produit(p.getId_produit(), p.getNom(),
                    p.getReference(), p.getCategorie(), p.getPrix(),
                    p.getDescription(), p.getUser(), p.getPromotion()));
            col_Action.setCellValueFactory(
                    param -> new ReadOnlyObjectWrapper<>(param.getValue())
            );
            col_Action.setCellFactory(param -> new TableCell<Produit, Produit>() {
                private final Button calculProm = new Button("Calculer");

                @Override
                protected void updateItem(Produit p, boolean empty) {
                    super.updateItem(p, empty);
                    if (p == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(new HBox(calculProm));
                    calculProm.setOnAction( //event -> getTableView().getItems().remove(user)
                            event -> {
                                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                        "Promotion", " Le calcul de la promotion est : " + String.valueOf(sp.calculerPromotion(p))
                                );

                            }
                    );
                }
            });
        });
    }

    @FXML

    private void switchTo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("./SelectionInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<Produit> getListeProduit() {
        Produit p = new Produit();
        Categorie c = new Categorie();
        User u = new User();
        ObservableList<Produit> ListeProduits = FXCollections.observableArrayList();
        ListeProduits.add(new Produit(p.getNom(), p.getReference(), new Categorie(c.getId_categorie()), p.getPrix(), p.getDescription(), new User(u.getId()), p.getPromotion()));
        return ListeProduits;
    }

    @FXML
    private void ajouterProduitAction(ActionEvent event) throws IOException {
        Iproduit sp = new ServiceProduit();
        Iuser su = new ServiceUser();
        Icategorie sc = new ServiceCategorie();
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
    }

    @FXML
    private void supprimerProduitAction(ActionEvent event) throws IOException {
        Iproduit sp = new ServiceProduit();
        Iuser su = new ServiceUser();
        Icategorie sc = new ServiceCategorie();

        sp.supprimerProduit(new Produit(Integer.parseInt(id_Produit.getText()), nomProduitTF.getText(),
                referenceTF.getText(),
                sc.retrieveCategorieByNom((String) comboBoxCat.getValue()),
                Double.parseDouble(prixTF.getText()),
                descriptionTF.getText(),
                su.getByEmail((String) comboFourn.getValue()),
                Double.parseDouble(promotionTF.getText()))
        );
        root = FXMLLoader.load(getClass().getResource("./AddProductInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                " Succés de suppression ! ", " Suppression du produit établie avec succés! ");

    }

    @FXML
    private void modifierProduitAction(ActionEvent event) throws IOException {
        Iuser su = new ServiceUser();
        Iproduit sp = new ServiceProduit();
        Icategorie sc = new ServiceCategorie();
        sp.modifierProduit(new Produit(Integer.parseInt(id_Produit.getText()), nomProduitTF.getText(),
                referenceTF.getText(),
                sc.retrieveCategorieByNom((String) comboBoxCat.getValue()),
                Double.parseDouble(prixTF.getText()),
                descriptionTF.getText(),
                su.getByEmail((String) comboFourn.getValue()),
                Double.parseDouble(promotionTF.getText()))
        );

        root = FXMLLoader.load(getClass().getResource("./AddProductInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                " Succés! ", " Modification du produit établie avec succés! ");

    }

    @FXML
    private void chercherProduitTF(ActionEvent event) {
        Iproduit sp = new ServiceProduit();
        List<Produit> Produits = sp.afficherProduit();
        System.out.println(sp.chercherProduitDynamiquement(ChercheTF.getText(), sp.afficherProduit()));
//
//        List<Produit> searchList
//        (String searchWords, List<Produit > listOfStrings) {
//
//        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
//
//            return;
//            listOfStrings.stream().filter(input -> {
//                return searchWordsArray.stream().allMatch(word
//                        -> input.toLowerCase().contains(word.toLowerCase()));
//            }).collect(Collectors.toList());
    }

}
