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
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Commande;
import models.Commande_elem;
import models.Panier_elem;
import services.ServiceCommande;
import services.ServiceCommande_elem;
import services.ServicePanier_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaiementChoixController implements Initializable {
     private Stage stage;
    private Scene scene;
    private Parent root;
    
    PanierController pan = new PanierController();
    ServiceCommande sp = new ServiceCommande();
    ServicePanier_elem sp2 = new ServicePanier_elem();
    ServiceCommande_elem sp1 = new ServiceCommande_elem();
    AcceuilController ac = new AcceuilController();
    @FXML
    private Button espece;
    @FXML
    private Button ligne;
    
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
        
        
    }    

    @FXML
    private void AjoutCommande(ActionEvent event) throws IOException {
        List<Panier_elem> l = new ArrayList<>();
        l = pan.Li;
        LocalDate d = LocalDate.now();
        Date d1 = Date.valueOf(d);
        Commande c = new Commande();
        c = sp.GetCommande(ac.c.getId(),d1);
        System.err.println(ac.c.getId());
        int i =0;
        while(i < pan.Li.size()){
            Commande_elem e = new Commande_elem(pan.Li.get(i).getProduit(),c,(int) pan.Li1.get(i));
            sp1.ajouterElementCommande(e);
            i++;
        }
    showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "succès!", "Commande Ajouter avec succès");
       for(Panier_elem e : l){
           sp2.supprimerElementPanier(e);
       }
    
    Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
}
