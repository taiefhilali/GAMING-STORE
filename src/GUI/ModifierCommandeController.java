/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Commande;
import models.CommandeElem;
import models.Commande_elem;
import models.PanProd;
import models.Panier_elem;
import services.ServiceCommande;
import services.ServiceCommande_elem;
import services.ServicePanier_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierCommandeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
      ServiceCommande_elem sp = new ServiceCommande_elem();
      ServiceCommande sp1 = new ServiceCommande();
    ClientCommandesController  c1 = new ClientCommandesController();
   
    
    
    @FXML
    private TableView<CommandeElem> tableProduits;
    @FXML
    private TableColumn<CommandeElem, String> nomTb= new TableColumn<>();
    @FXML
    private TableColumn<CommandeElem,Double> prixTb  =new TableColumn<>();
    @FXML
    private TableColumn<CommandeElem,Integer> quantiteTb = new TableColumn<>();
    @FXML
    private TableColumn<CommandeElem,CommandeElem> SupprimerTb = new TableColumn<>();
    @FXML
    private Hyperlink retour;
    @FXML
    private Button validerC;
    @FXML
    private Label sommeProduits;
    @FXML
    private Label Livraison;
    @FXML
    private Label TotalP;
    @FXML
    private Button calcule;

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
         List<Commande_elem> p1 = new ArrayList<Commande_elem>();
         System.err.println(c1.c);
         Double prs = c1.c.getPrix_produits();
         Double prs1 = c1.c.getPrix_livraison();
         Double prs2 = c1.c.getPrix_total();
         
         sommeProduits.setText(prs.toString() + "  DT");
         Livraison.setText(prs1.toString()+ "  DT");
         TotalP.setText(prs2.toString()+ "  DT");
         
         p1 = sp.AfficherCommande(c1.c.getId_commande());
         
         nomTb.setCellValueFactory(new PropertyValueFactory<>("nomP"));
         prixTb.setCellValueFactory(new PropertyValueFactory<>("prix"));
         quantiteTb.setCellValueFactory(new PropertyValueFactory<>("s"));
         
        for(Commande_elem p2 : p1){
            tableProduits.getItems().add(new CommandeElem(p2.getQuantite(),p2.getId_elemC(),p2.getProduit().getNom(),p2.getProduit().getPrix()));
        }
         
          SupprimerTb.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
         
           SupprimerTb.setCellFactory(param -> new TableCell<CommandeElem, CommandeElem>(){
         private final Button deleteButton = new Button("Supprimer");
         private final Button EditButton = new Button("Modifier");
         
         @Override
            protected void updateItem(CommandeElem cmd, boolean empty) {
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
                            sp.supprimerElementByID(cmd.getId_elem());
                               showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "suppression du produit avec succès");
                            getTableView().getItems().remove(cmd);
                        });
         
         EditButton.setOnAction(event ->{
             sp.modifierElementCommande((Integer)cmd.getS().getValue(),cmd.getId_elem());
             
            showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "Modification du produit avec succès");
         });
         
        }}
       ); 
         
    }    

    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientCommandes.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void valider(ActionEvent event) {
        Double Total = 0.0;
       Double Liv = 0.0;
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
       for(int i=0 ; i<tableProduits.getItems().size();i++)
       {
           Double p =tableProduits.getItems().get(i).getPrix();
           Integer q = (Integer) tableProduits.getItems().get(i).getS().getValue();
       Total += (p*q); 
       }
       if(Total<200){
           Liv =10.0; 
       }
       Double Tot = Total + Liv;
       sp1.modifierCommande(c1.c.getId_commande(),Total,Liv,Tot);
        showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "Commande Modifier avec succès");
    }

    @FXML
    private void calculer(ActionEvent event) {
        Double Total = 0.0;
       Double Liv = 0.0;
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
       for(int i=0 ; i<tableProduits.getItems().size();i++)
       {
           Double p =tableProduits.getItems().get(i).getPrix();
           Integer q = (Integer) tableProduits.getItems().get(i).getS().getValue();
       Total += (p*q); 
       }
       if(Total<200){
           Liv =10.0; 
       }
       Double Tot = Total + Liv;
       sommeProduits.setText(Total.toString()+"  DT");
       Livraison.setText(Liv.toString()+"  DT");
       TotalP.setText(Tot.toString()+"  DT");
    }
    
}
