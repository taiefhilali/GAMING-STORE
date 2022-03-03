/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
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
import models.PanProd;
import models.Panier_elem;
import services.ServiceCommande;
import services.ServicePanier_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierController implements Initializable {
     private Stage stage;
    private Scene scene;
    private Parent root;
    
    AcceuilController ac = new AcceuilController();
    ServicePanier_elem sp = new ServicePanier_elem();
    ServiceCommande sp1 = new ServiceCommande();
    static Commande cmd = new Commande();
    static List<Panier_elem> Li = new ArrayList<>();
    static List<Integer> Li1 = new ArrayList<>();
     
    @FXML
    private Label sommeProduits;
    @FXML
    private Label Livraison;
   
    @FXML
    private Label TotalP;
    @FXML
    private Button calcule;
    @FXML
    private Button validerC;
    @FXML
    private TableView<PanProd> tableProduits;
    @FXML
    private TableColumn<PanProd, String> nomTb= new TableColumn<>();
    @FXML
    private TableColumn<PanProd,Double> prixTb  =new TableColumn<>();
    @FXML
    private TableColumn<PanProd,Integer> quantiteTb = new TableColumn<>();
    @FXML
    private TableColumn<PanProd,PanProd> SupprimerTb = new TableColumn<>();
    @FXML
    private Hyperlink Retour;
    
    
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
   
        //Afficher les produits
      
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
        p1 = sp.afficherPanier(1);
        
        nomTb.setCellValueFactory(new PropertyValueFactory<>("nomP"));
        prixTb.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantiteTb.setCellValueFactory(new PropertyValueFactory<>("s"));
         for(Panier_elem p2 : p1){
            tableProduits.getItems().add(new PanProd(p2.getId_elem(),p2.getProduit().getId(),p2.getProduit().getNom(),p2.getProduit().getPrix()));
        }
        SupprimerTb.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        
       SupprimerTb.setCellFactory(param -> new TableCell<PanProd, PanProd>(){
         private final Button deleteButton = new Button("Supprimer");
         
         @Override
            protected void updateItem(PanProd panier_elem, boolean empty) {
                super.updateItem(panier_elem, empty);
                if (panier_elem == null) {
                    setGraphic(null);
                    return;
                }
         setGraphic(new HBox(deleteButton));
         deleteButton.setStyle("-fx-background-color: #FF5317;");
         deleteButton.setOnAction( 
                        event -> {
                            sp.supprimerElementPanier(panier_elem.getId_elem());

                               showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "suppression du produit avec succès");
                            getTableView().getItems().remove(panier_elem);
                        });
        }}
       );
      
      
    }    

    
    
    

    
    //Calcule de Panier
    @FXML
    private void calculer(ActionEvent event) {
        Double Total = 0.0;
       Double Liv = 0.0;
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
        p1 = sp.afficherPanier(1);
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

    //Validation Commande
    @FXML
    private void valider(ActionEvent event) throws IOException {
        Double Total = 0.0;
       Double Liv = 0.0;
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
        p1 = sp.afficherPanier(1);
       for(int i=0 ; i<tableProduits.getItems().size();i++)
       {   Li1.add( (Integer)tableProduits.getItems().get(i).getS().getValue());
           Double p =tableProduits.getItems().get(i).getPrix();
           Integer q = (Integer) tableProduits.getItems().get(i).getS().getValue();
       Total += (p*q); 
       }
       if(Total<200){
           Liv =10.0; 
       }
       Double Tot = Total + Liv;
       LocalDate d = LocalDate.now();
       Date d1 = Date.valueOf(d);
        
        cmd.setDate_commande(d1);
        cmd.setPrix_livraison(Liv);
        cmd.setPrix_produits(Total);
        cmd.setPrix_total(Tot);
        cmd.setClient(ac.c);
        
        this.affectation();
        sp1.ajouterCommande(cmd);
        System.out.println(cmd);
        Parent root = FXMLLoader.load(getClass().getResource("PaiementChoix.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    //Affectation function
    public void affectation(){
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
        p1 = sp.afficherPanier(1);
        Li = p1;
    }
    
    //Retour function
    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
