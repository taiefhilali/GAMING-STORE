/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Facture;
import models.Stock;
import services.ServiceStock;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DocFXMLController implements Initializable {

     ServiceStock ss = new ServiceStock();
          
    @FXML
    private Button ajout;
    @FXML
    private Button modif;
    @FXML
    private Button supp;
    @FXML
    private TextField nomTF;
    @FXML
    private TextField quantiteTF;
    @FXML
    private TextField etatTF;
    @FXML
    private TableView<Stock> tab;
    @FXML
    private TableColumn<Stock, Integer> tabID;
    @FXML
    private TableColumn<Stock, String> tabN;
    @FXML
    private TableColumn<Stock, Integer> tabQ;
    @FXML
    private TableColumn<Stock, String> tabE;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField id;

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
     
      public void handle(ActionEvent event) {
                System.out.println("You clicked me!");
            }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tab.getSelectionModel().getSelectedItem() != null) {
                    Stock selectedStock = tab.getSelectionModel().getSelectedItem();

                    id.setText(String.valueOf(selectedStock.getId()));
                
                    nomTF.setText(selectedStock.getNom());  
                    quantiteTF.setText(String.valueOf(selectedStock.getId()));
                     etatTF.setText(selectedStock.getEtat());  
                   
                }
            }
        });
        
        
        
        List<Stock> listStock = ss.afficherStock();
          tabID.setCellValueFactory(new PropertyValueFactory<>("id"));

        tabN.setCellValueFactory(new PropertyValueFactory<>("nom"));
tabQ.setCellValueFactory(new PropertyValueFactory<>("quantite"));
  tabE.setCellValueFactory(new PropertyValueFactory<>("etat"));
  
        for (Stock s: listStock) {
            tab.getItems().add(new Stock(s.getId(),s.getNom(), s.getQuantite(), s.getEtat()));
        }
    }    

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        if (nomTF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();

        } else {
           
            ss.ajouterStock(new Stock(nomTF.getText(),  Integer.parseInt(quantiteTF.getText()),etatTF.getText()));
         
            root = FXMLLoader.load(getClass().getResource("DocFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "ajout effectuer avec succès");
    
            
        }
         
    }

    @FXML
    private void modif(ActionEvent event) throws IOException {
           ss.modifierStock(new Stock (Integer.parseInt(id.getText()), nomTF.getText(), Integer.parseInt(quantiteTF.getText()), etatTF.getText()));
           
            root = FXMLLoader.load(getClass().getResource("DocFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
       stage.setScene(scene);
        stage.show();
          showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "modification effectuer avec succès");
    }

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
           ss.supprimerStock (Integer.parseInt(id.getText()));
           root = FXMLLoader.load(getClass().getResource("DocFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "suppression effectuer avec succès");
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("AccFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    }

    

