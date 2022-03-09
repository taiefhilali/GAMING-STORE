/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.ICommande;
import interfaces.ICommande_elem;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.CommandeElem;
import models.Commande_elem;
import services.ServiceCommande;
import services.ServiceCommande_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    @FXML
    private Label id_produit;
    @FXML
    private Button Supprimer;
    @FXML
    private Label id_elem;
    @FXML
    private Spinner<Integer> spinner1;
    @FXML
    private Button Supprimer1;
    static String title = "";
    ICommande_elem sp = new ServiceCommande_elem();
    private Stage stage;
    private Scene scene;
    private Parent root;
     ClientCommandesController  c1 = new ClientCommandesController();
     ICommande sp1 = new ServiceCommande();
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
    public void setData(CommandeElem pt){
          
        this.nameLabel.setText(pt.getNomP());
        this.id_produit.setText(String.valueOf(pt.getId_elem()));
        this.priceLable.setText(String.valueOf(pt.getPrix()));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
         valueFactory.setValue(pt.getQuantite());
         spinner1.setValueFactory(valueFactory);
    }
    @FXML
    private void Supprimer(ActionEvent event) throws IOException {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Suppression !!!");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de Supprimer ce produit du Commande ? ");
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK){
            sp.supprimerElementByID(Integer.parseInt(this.id_produit.getText()));
            Parent root = FXMLLoader.load(getClass().getResource("Modifier.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        List<Commande_elem> p1 = new ArrayList<Commande_elem>();
         p1 = sp.AfficherCommande(c1.c.getId_commande());
          if(p1.size() == 0){
               sp1.SupprimerCommande(c1.c);
               Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Alert !!!");
        alert2.setHeaderText(null);
        alert2.setContentText(" Votre Commande est supprimer : Commande Vide !!!!");
        Optional<ButtonType> action2 = alert2.showAndWait();
         }
        }
        
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Suppression !!!");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de Modifier ce produit du Commande ? ");
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK){
            sp.modifierElementCommande(spinner1.getValue(), Integer.parseInt(this.id_produit.getText()));
            
             List<Commande_elem> p1 = new ArrayList<Commande_elem>();
            p1 = sp.AfficherCommande(c1.c.getId_commande());
            if(p1.size() != 0){
            Double Total = 0.0;
                Double Liv = 0.0;
           for(Commande_elem p2 : p1){
             Total += p2.getProduit().getPrix() * p2.getQuantite();
         }
         if(Total<200 && p1.size() != 0){
                 Liv =10.0; 
                 }
         Double Tot = Total + Liv;
         
            sp1.modifierCommande(c1.c.getId_commande(),Total,Liv,Tot);
            title = "Votre Nouveau Prix Total est !!!!";
            Parent root = FXMLLoader.load(getClass().getResource("Modifier.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        title ="";
            }
            showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "Modification du Commande avec succès");
        }
    }

    
}
