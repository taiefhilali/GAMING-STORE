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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Facture;
import services.ServiceFacture;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Stock;
import models.User;
import services.ServiceUser;
 
/**
 * FXML Controller class
 *
 * @author user
 */
public class FacFXMLController implements Initializable {

    
        ServiceFacture sf = new ServiceFacture();
         ServiceUser su = new ServiceUser();
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField prixTF;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Facture> tab1;
    
    @FXML
    private TableColumn<Facture, Integer> tabP;
    @FXML
    private TableColumn<Facture, Integer> tabID;
     private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button ajouter1;
    @FXML
    private ComboBox combo;
    @FXML
    private TableColumn<Facture, Date> tabD;
   // private TableColumn<Facture, Integer> tabID1;
    @FXML
    private TextField id;
    @FXML
    private TableColumn<Facture, Integer> id_user;
    @FXML
    private Button calculer;

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
         tab1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tab1.getSelectionModel().getSelectedItem() != null) {
                    Facture selectedFacture = tab1.getSelectionModel().getSelectedItem();

                    id.setText(String.valueOf(selectedFacture.getId_facture()));
                    date.setValue(selectedFacture.getDate().toLocalDate());
                    prixTF.setText(selectedFacture.getPrix_total());  
                    combo.setValue(selectedFacture.getUser().getEmail());
                   
                }
            }
        });
        
        
        
        List<User> listUser =  su.afficherPersonnes();
          List<Facture> listFacture = sf.afficherFacture();
           tabID.setCellValueFactory(new PropertyValueFactory<>("id_facture"));
          tabD.setCellValueFactory(new PropertyValueFactory<>("date"));
         tabP.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
         id_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        for (Facture f: listFacture) {
            tab1.getItems().add(new Facture(f.getId_facture(),f.getDate(),f.getPrix_total(),f.getUser()));
        }
          for (User u: listUser) {
           combo.getItems().addAll(u.getEmail()) ;
        }
           listFacture.forEach((f) -> {
            tab1.getItems().addAll(new Facture(f.getId_facture(),f.getDate(),
                     f.getPrix_total(),
                   f.getUser()));
           });
                  
    }
       

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
       
            Date myDate = Date.valueOf(date.getValue().toString());
           
            sf.ajouterFacture(new Facture(myDate, prixTF.getText(), su.getByEmail((String) combo.getValue()) ) );
           
             root = FXMLLoader.load(getClass().getResource("FacFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "ajout effectuer avec succès");
        
        
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Date myDate = Date.valueOf(date.getValue().toString());
           
        sf.modifierFacture(new Facture(Integer.parseInt(id.getText()),myDate, prixTF.getText(), su.getByEmail((String) combo.getValue()) ) );
        root = FXMLLoader.load(getClass().getResource("FacFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "modification effectuer avec succès");
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        
           
            sf.supprimerFacture(Integer.parseInt(id.getText()));
         root = FXMLLoader.load(getClass().getResource("FacFXML.fxml"));
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
