/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Client;
import models.PanProd;
import models.Panier;
import models.Panier_elem;
import models.Produit;
import service.ServiceProduit;
import services.ServicePanier_elem;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AcceuilController implements Initializable {
 private Stage stage;
    private Scene scene;
    private Parent root;
    
   static Client c = new Client("femme",9, "siwar.tabbena@gmail.com","){==[(>~","client","tabbena", "siwar", "Ben arouse/Mourouj 1/Rue 234/2074", "26386558");
    ServiceProduit sp = new ServiceProduit();
    ServicePanier_elem sp1 = new ServicePanier_elem();
    
    @FXML
    private Button Panier;
    @FXML
    private Button Panier1;
    @FXML
    private TableView<Produit> produitsTab;
    @FXML
    private TableColumn<Produit,String> nomTab =new TableColumn<>();
    @FXML
    private TableColumn<Produit,String> refTab =new TableColumn<>();
    @FXML
    private TableColumn<Produit, String> DesTab = new TableColumn<>();
    @FXML
    private TableColumn<Produit, Double> PrixTab = new TableColumn<>();
    @FXML
    private TableColumn<Produit,Produit> ActionTab = new TableColumn<>();

     private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Produit> p1 = new ArrayList<Produit>();
        Client c = new Client("femme",1);
        Panier pp = new Panier(1,c);
        p1 = sp.afficherProduit();
        
        nomTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        refTab.setCellValueFactory(new PropertyValueFactory<>("reference"));
        DesTab.setCellValueFactory(new PropertyValueFactory<>("description"));
        PrixTab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        ActionTab.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        for(Produit p2 : p1){
            produitsTab.getItems().add(new Produit(p2.getId(),p2.getNom(),p2.getReference(),p2.getPrix(),p2.getDescription()));
        }
        ActionTab.setCellFactory(param -> new TableCell<Produit, Produit>(){
         private final Button AjoutButton = new Button("Ajouter");
        @Override
            protected void updateItem(Produit pr, boolean empty) {
                super.updateItem(pr, empty);
                if (pr == null) {
                    setGraphic(null);
                    return;
                }
                 if(sp1.getElement(pp.getId_panier(),pr.getId()).getId_elem() == 0){
                     setGraphic(new HBox(AjoutButton));
                    }
         
         AjoutButton.setStyle("-fx-background-color: #21E350;");
         AjoutButton.setOnAction( 
                        event -> {
                            Panier_elem p = new Panier_elem(pp,pr);
                               sp1.AjouterElementPanier(p);
                               showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "Produit Ajouter avec succès");
                               Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                         scene = new Scene(root);
                        stage.setScene(scene);
                         stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                        });
          
            }});
      
    }
    
  
    
    
    @FXML
    private void Go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Go1(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ClientCommandes.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
