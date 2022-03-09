/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import models.Stock;
import services.ServiceStock;
import utils.MaConnexion;


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
    @FXML
    private TextField search;
// private final ObservableList<Stock> sss=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    //************************* methode alert*************************
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
         try {
              // **********************selection ligne tab ******************
             tab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                 @Override
                 public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                     if (tab.getSelectionModel().getSelectedItem() != null) {
                         Stock selectedStock = tab.getSelectionModel().getSelectedItem();
                         
                         id.setText(String.valueOf(selectedStock.getId()));
                         
                         nomTF.setText(selectedStock.getNom());
                         quantiteTF.setText(String.valueOf(selectedStock.getQuantite()));
                         etatTF.setText(selectedStock.getEtat());
                         
                         
                     }
                 }
             });
             
               //***********************affichage table view **************
             ObservableList<Stock> list = null;
             list = ss.getCoursList();
             List<Stock> listStock = ss.afficherStock();         
             
             tabN.setCellValueFactory(new PropertyValueFactory<>("nom"));
             tabQ.setCellValueFactory(new PropertyValueFactory<>("quantite"));
             tabE.setCellValueFactory(new PropertyValueFactory<>("etat"));
             
             for (Stock s: listStock) {
                 tab.getItems().add(new Stock(s.getId(),s.getNom(), s.getQuantite(), s.getEtat()));
                 
             }
             FilteredList<Stock> filteredData = new FilteredList<>(list,b -> true);
             
             // 2. Set the filter Predicate whenever the filter changes.
             search.textProperty().addListener((observable, oldValue, newValue) -> {
                 filteredData.setPredicate(Cours -> {
                     // If filter text is empty, display all persons.
                     
                     if (newValue == null || newValue.isEmpty()) {
                         return true;
                     }
                     
                     // Compare first name and last name of every person with filter text.
                     String lowerCaseFilter = newValue.toLowerCase();
                     
                     if (Cours.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) { 
                         return true;
// Filter matches first name.
                     }
                     else if (String.valueOf(Cours.getId()).indexOf(lowerCaseFilter)!=-1) {
                         return true; // Filter matches first name.
                     }
                     else if (String.valueOf(Cours.getQuantite()).indexOf(lowerCaseFilter)!=-1) {
                         return true; // Filter matches first name.
                         
                     }  else if (Cours.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1)
                         return true;
                     else {
                         return false; // Does not match.
                     }
                 });
             });
             
             // 3. Wrap the FilteredList in a SortedList.
             SortedList<Stock> sortedData = new SortedList<>(filteredData);
             
             // 4. Bind the SortedList comparator to the TableView comparator.
             //       Otherwise, sorting the TableView would have no effect.
             sortedData.comparatorProperty().bind(tab.comparatorProperty());
             
             // 5. Add sorted (and filtered) data to the table.
             tab.setItems(sortedData);
         } catch (SQLException ex) {
             Logger.getLogger(DocFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

       

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        boolean dataValid =true;
    
        if (dataValid==true){
            
        
        if (nomTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();
            
            dataValid=false;}
       
        if (dataValid==true){
          if (etatTF.getText().length()==0){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie l'etat");
            alert.show();
            dataValid=false;
        }
          
          if (dataValid==true){
          if (quantiteTF.getText().length()==0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie la quantité");
            alert.show();
            dataValid=false;
    }
          if (dataValid==true){
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
        }
        }     
        
    }

    
    
    @FXML
    private void modif(ActionEvent event) throws IOException {
              boolean dataValid =true;
    int op= JOptionPane.showConfirmDialog(null, "Est-ce que vous confirmez la modification de ces elements","confirmation",JOptionPane.YES_NO_OPTION);
				if (op==0) {
        if (dataValid==true){
            
        
        if (nomTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();
            
            dataValid=false;}
       
        if (dataValid==true){
          if (etatTF.getText().length()==0){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie l'etat");
            alert.show();
            dataValid=false;
        }
          
          if (dataValid==true){
          if (quantiteTF.getText().length()==0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie la quantité");
            alert.show();
            dataValid=false;
    }
          if (dataValid==true){
               ss.modifierStock(new Stock (Integer.parseInt(id.getText()), nomTF.getText(), Integer.parseInt(quantiteTF.getText()), etatTF.getText()));
           
            root = FXMLLoader.load(getClass().getResource("DocFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
       stage.setScene(scene);
        stage.show();
          showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "modification effectuer avec succès");
          }
          }
        }
        }
                                }
    }

    
    
    @FXML
    private void supp(ActionEvent event) throws IOException {
        
        int op= JOptionPane.showConfirmDialog(null, "Est-ce que vous confirmez la suppression","confirmation",JOptionPane.YES_NO_OPTION);
if (op==0) {
           ss.supprimerStock (Integer.parseInt(id.getText()));
           root = FXMLLoader.load(getClass().getResource("DocFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "suppression effectuer avec succès");
    }
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

    

