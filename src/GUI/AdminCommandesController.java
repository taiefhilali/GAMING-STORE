/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.ICommande;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Window;
import models.AdminCommandes;
import models.Commande;
import models.Livraison;
import services.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminCommandesController implements Initializable {

    ICommande sp = new ServiceCommande();
    
    @FXML
    private TableView<AdminCommandes> adminCmd;
    @FXML
    private TableColumn<AdminCommandes, String> nomTab;
    @FXML
    private TableColumn<AdminCommandes, String> prenomTab;
    @FXML
    private TableColumn<AdminCommandes, Date> dateTab;
    @FXML
    private TableColumn<AdminCommandes, Double> prLiv;
    @FXML
    private TableColumn<AdminCommandes, Double> PrTot;
    @FXML
    private TableColumn<AdminCommandes, Double> PrTotal;
    @FXML
    private TableColumn<AdminCommandes, AdminCommandes> Action;
    
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
         List<Commande> p1 = new ArrayList<Commande>();
         p1 = sp.AfficherCommande();
         nomTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prLiv.setCellValueFactory(new PropertyValueFactory<>("prix_Liv"));
        prenomTab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        PrTot.setCellValueFactory(new PropertyValueFactory<>("prix_Produits"));
        dateTab.setCellValueFactory(new PropertyValueFactory<>("d"));
        PrTotal.setCellValueFactory(new PropertyValueFactory<>("prix_Total"));
        
        for(Commande p2 : p1){
             List<Livraison> li = new ArrayList<Livraison>();
          li = sp.AfficherCommandeIdLiv(p2.getId_commande());
          if(li.size() == 0){
            adminCmd.getItems().add(new AdminCommandes(p2.getId_commande(),p2.getClient().getNom()
            ,p2.getClient().getPrenom(),p2.getDate_commande(),p2.getPrix_livraison()
            ,p2.getPrix_produits(),p2.getPrix_total()));
        }
        }
        Action.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        
        Action.setCellFactory(param -> new TableCell<AdminCommandes,AdminCommandes>(){
         private final Button AffectButton = new Button("Affecter");
         
         @Override
            protected void updateItem(AdminCommandes cmd, boolean empty) {
                super.updateItem(cmd, empty);
                if (cmd == null) {
                    setGraphic(null);
                    return;
                }
         setGraphic(new HBox(AffectButton));
         AffectButton.setStyle("-fx-background-color: #21E350;");
         AffectButton.setOnAction( 
                        event -> {
                            
                        });
         
        }}
       ); 
    }
        }
    
    

