/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Commande;
import services.ServiceCommande;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ClientCommandesController implements Initializable {
    
    ServiceCommande sp = new ServiceCommande();
    private Stage stage;
    private Scene scene;
    private Parent root;
    
     static Commande c = new Commande();
    
    @FXML
    private TableView<Commande> tableCmd;
    @FXML
    private TableColumn<Commande, Date> datecmd;
    @FXML
    private TableColumn<Commande, Double> prLiv;
    @FXML
    private TableColumn<Commande, Double> TotPr;
    @FXML
    private TableColumn<Commande, Double> PrTot;
    @FXML
    private TableColumn<Commande, Commande> Action;
    @FXML
    private Button Retour;
    @FXML
    private DatePicker datePck;

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
        p1 = sp.AfficherCommandeId(9);
        datecmd.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
        prLiv.setCellValueFactory(new PropertyValueFactory<>("prix_livraison"));
        TotPr.setCellValueFactory(new PropertyValueFactory<>("prix_produits"));
        PrTot.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
       
       
        for(Commande p2 : p1){
            tableCmd.getItems().add(new Commande(p2.getId_commande(),p2.getPrix_livraison(),p2.getPrix_produits(),p2.getPrix_total(),p2.getDate_commande()));
        }
         
        
        Action.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        Action.setCellFactory(param -> new TableCell<Commande, Commande>(){
         private final Button deleteButton = new Button("Supprimer");
         private final Button EditButton = new Button("Afficher");
         
         @Override
            protected void updateItem(Commande cmd, boolean empty) {
                super.updateItem(cmd, empty);
                if (cmd == null) {
                    setGraphic(null);
                    return;
                }
         setGraphic(new HBox(EditButton,deleteButton));
         deleteButton.setStyle("-fx-background-color: #FF5317;");
         EditButton.setStyle("-fx-background-color: #21E350;");
         
         deleteButton.setOnAction( 
                        event -> {
                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation de suppression !!");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure d'annuler cette Commande ? ");
        Optional<ButtonType> action = alert1.showAndWait();
                         if (action.get() == ButtonType.OK) {
                            sp.SupprimerCommande(cmd);
                               showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "suppression du Commande avec succès");
                            getTableView().getItems().remove(cmd);}
                         else {
            alert1.close();
        }
                        });
         
         EditButton.setOnAction(event ->{
             Parent root;
                    try {
                        c = cmd;
                        root = FXMLLoader.load(getClass().getResource("Modifier.fxml"));
                          stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                          scene = new Scene(root);
                          stage.setScene(scene);
                          stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ClientCommandesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
         });
         
        }}
       ); 
        }
           

    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Filter(ActionEvent event) {
        LocalDate mydate = datePck.getValue();
        Date d1 = Date.valueOf(mydate);
        List<Commande> p1 = new ArrayList<Commande>();
        tableCmd.getItems().removeAll();
    }
    
}
