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
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import models.CommandeElem;
import models.Commande_elem;
import models.PanProd;
import models.Panier_elem;
import services.ServiceCommande;
import services.ServiceCommande_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierController implements Initializable {

    @FXML
    private Label sommeProduits;
    @FXML
    private Label Livraison;
    @FXML
    private Label TotalP;
    @FXML
    private ImageView logoImage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    ModifierItemController mod = new ModifierItemController();
    ICommande_elem sp = new ServiceCommande_elem();
    ICommande sp1 = new ServiceCommande();
    ClientCommandesController  c1 = new ClientCommandesController();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label title;
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
        this.title.setText(mod.title);
        this.calculer1();
         //Animation
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(logoImage);
        rotate.setDuration(Duration.millis(2000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
        
        List<Commande_elem> p1 = new ArrayList<Commande_elem>();
         System.err.println(c1.c);
         /*Double prs = c1.c.getPrix_produits();
         Double prs1 = c1.c.getPrix_livraison();
         Double prs2 = c1.c.getPrix_total();
         
         sommeProduits.setText(prs.toString() + "  DT");
         Livraison.setText(prs1.toString()+ "  DT");
         TotalP.setText(prs2.toString()+ "  DT");*/
         
         p1 = sp.AfficherCommande(c1.c.getId_commande());
          int column = 0 ;
        int row = 1;
         try {
        for(Commande_elem p2 : p1){
           CommandeElem p  = new CommandeElem(p2.getQuantite(),p2.getId_elemC(),p2.getProduit().getNom(),p2.getProduit().getPrix(),p2.getProduit().getImage());  
           FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ModifierItem.fxml"));
                AnchorPane anchorpane = fxmlloader.load();
                ModifierItemController modif = fxmlloader.getController();
                modif.setData(p);
                   
                if(column == 2){
                    column = 0;
                    row++;
                }
                grid.add(anchorpane, column++, row);
                
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(anchorpane, new Insets(10));
                
        }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }    

    public void calculer1(){
        List<Commande_elem> p1 = new ArrayList<Commande_elem>();
         p1 = sp.AfficherCommande(c1.c.getId_commande());
         Double Total = 0.0;
                Double Liv = 0.0;
         for(Commande_elem p2 : p1){
             Total += p2.getProduit().getPrix() * p2.getQuantite();
         }
         if(Total<200 && p1.size() != 0){
                 Liv =10.0; 
                 }
         Double Tot = Total + Liv;
         sommeProduits.setText(Total.toString() + "  DT");
         Livraison.setText(Liv.toString()+ "  DT");
         TotalP.setText(Tot.toString()+ "  DT");
    }

    @FXML
    private void BackToMain(ActionEvent event) throws IOException {
         this.title.setText("");
         Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
