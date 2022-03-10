/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Statement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import models.Facture;
import services.ServiceFacture;
import javafx.stage.Window;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.control.TextField;
import models.User;
import services.ServiceUser;
import utils.MaConnexion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;






/**
 * FXML Controller class
 *
 * @author user
 */
public class FacFXMLController implements Initializable {
    
    Connection cnx =MaConnexion.getInstance().getCnx();
    ServiceFacture sf = new ServiceFacture();
    ServiceUser su = new ServiceUser();
    double HT=0,tva=0,ttc=0,r=0,netc=0;
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
    private TableColumn<Facture, String> tabP;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox combo;
    @FXML
    private TableColumn<Facture, Date> tabD;
 
    @FXML
    private TextField id;

    private TextField rech;
    int somme = 0;
    @FXML
    private TextField search;
    @FXML
    private TableColumn<Facture, Integer> id_user1;
    

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
            //***********************affichage table view **************
            ObservableList<Facture> list = null;
            list = sf.getCoursList();
            List<User> listUser = su.afficherPersonnes();
            List<Facture> listFacture = sf.afficherFacture();
           
            tabD.setCellValueFactory(new PropertyValueFactory<>("date"));
            tabP.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            id_user1.setCellValueFactory(new PropertyValueFactory<>("user"));
           
            for (User u : listUser) {
                combo.getItems().addAll(u.getEmail());
            }
            
            listFacture.forEach((f) -> {
                tab1.getItems().addAll(new Facture(f.getId_facture(), f.getDate(),
                        f.getPrix_total(),
                        f.getUser()));
            });
            
            // Wrap the ObservableList in a FilteredList (initially display all data).
            
            FilteredList<Facture> filteredData = new FilteredList<>(list,b -> true);
            
            // 2. Set the filter Predicate whenever the filter changes.
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Cours -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (Cours.getPrix_total().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; 
// Filter matches first name.
                        }
                   else if (String.valueOf(Cours.getUser()).indexOf(lowerCaseFilter)!=-1) {
                    return true; // Filter matches first name.

                }  else if (String.valueOf(Cours.getDate()).indexOf(lowerCaseFilter)!=-1)
                  return true;
                else {
                    return false; // Does not match.
                }
            });
        });
            
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Facture> sortedData = new SortedList<>(filteredData);
            
            // 4. Bind the SortedList comparator to the TableView comparator.
            //       Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tab1.comparatorProperty());
            
            // 5. Add sorted (and filtered) data to the table.
            tab1.setItems(sortedData);
        } catch (SQLException ex) {
            Logger.getLogger(FacFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        Date myDate = Date.valueOf(date.getValue().toString());
        boolean dataValid = true;

        if (date.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de saisie !");
                alert.setContentText("Vous navez pas saisie la date");
                alert.show();
                dataValid = false;
            }

        else if (prixTF.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de saisie !");
                alert.setContentText("Vous navez pas saisie le prix total");
                alert.show();

                dataValid = false;
            }

            
               else if (combo.getValue() == null) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur de saisie !");
                    alert.setContentText("Vous navez pas saisie l'id user");
                    alert.show();
                    dataValid = false;
                }
    
            
          else {
                sf.ajouterFacture(new Facture(myDate, prixTF.getText(), su.getByEmail((String) combo.getValue())));

                root = FXMLLoader.load(getClass().getResource("FacFXML.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        "succès!", "ajout effectuer avec succès");

            }
        
    
}

@FXML
        private void modifier(ActionEvent event) throws IOException {
        Date myDate = Date.valueOf(date.getValue().toString());
         boolean dataValid =true;
    int op= JOptionPane.showConfirmDialog(null, "Est-ce que vous confirmez la modification de ces elements","confirmation",JOptionPane.YES_NO_OPTION);
				if (op==0) {
        if (dataValid==true){
            
        
        if (prixTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le prix total");
            alert.show();
            
            dataValid=false;}
       
        if (dataValid==true){
                    
          if (date.getEditor().getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie la date");
            alert.show();
            dataValid=false;
        }
          
          if (dataValid==true){
          if (combo.getValue().toString()==null){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie l'id user");
            alert.show();
            dataValid=false;
    }
           if (dataValid==true){
                   
        sf.modifierFacture(new Facture(Integer.parseInt(id.getText()),myDate, prixTF.getText(), su.getByEmail((String) combo.getValue()) ) );
        root = FXMLLoader.load(getClass().getResource("FacFXML.fxml"));
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
        private void supprimer(ActionEvent event) throws IOException {
        
       int op= JOptionPane.showConfirmDialog(null, "Est-ce que vous confirmez la suppression","confirmation",JOptionPane.YES_NO_OPTION);
if (op==0) {
            sf.supprimerFacture(Integer.parseInt(id.getText()));
            
         root = FXMLLoader.load(getClass().getResource("FacFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                "succès!", "suppression effectuer avec succès");
    }
    }
        
        
        
       
        private void retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

       
//*********************metier calculer **********************
    @FXML
    private List<Facture> calculer(ActionEvent event) {
        List<models.Facture> factures =new ArrayList<models.Facture>();
        String req="SELECT `prix_total` FROM `facture`";
        Statement st = null;
         try {
             st=cnx.createStatement();
              ResultSet rs= st.executeQuery(req);
             while (rs.next()){
            factures.add(new Facture(rs.getString("prix_total")));
         }
             
             for(Facture f:factures){
                 
                int prix=Integer.parseInt(f.getPrix_total());
//                 
                somme += prix;
                 
             }
         } catch (SQLException ex) {
               ex.printStackTrace();
         }
         if (somme>5000){
            r=somme*0.1;
            netc=somme-r;
        }
         System.out.println(String.valueOf(somme));
         System.out.println(String.valueOf(r));
         System.out.println(String.valueOf(netc));
           JOptionPane.showMessageDialog(null,"le Montant est "+somme+"DT\n "+"la remise est 1% "+r+"DT\n "+ "net commercial est "+netc+"DT\n "+"TVA est "+tva+"DT\n"+"TTC est "+ttc+"DT\n","confirmation",JOptionPane.OK_OPTION);
            return factures;
    }
    
    
    //******************API PDF**********************
        @FXML
    private void imprimer(ActionEvent event) throws SQLException{
        
        ServiceFacture sf = new ServiceFacture();
        ObservableList<Facture> list = sf.getCoursList();

        try{
            OutputStream file = new FileOutputStream(new File("C:\\Users\\user\\Desktop\\Levelup\\Fac.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();

          //  Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph pdfTitle = new Paragraph("Liste des factures");
            pdfTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(pdfTitle);
            document.add(new Chunk("\n"));
            PdfPTable table = new PdfPTable(3);
            table.setHeaderRows(1);

            table.addCell("date");
            table.addCell("prix_total");
            table.addCell("fournisseur");

            list.forEach((_item) -> {
                table.addCell(String.valueOf(_item.getDate()));
                table.addCell(String.valueOf(_item.getPrix_total()));
                table.addCell(String.valueOf(_item.getUser()));
            });

            document.add(table);         
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Success");
            alert.setContentText("Success!");
            document.close();

            file.close();

        } catch (Exception ex) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot export data!");
            alert.show();
        }
    }
    
    
    //****************** aller vers interface smsFXML*****************
    @FXML
    private void sms(ActionEvent event) throws IOException  {
        
root = FXMLLoader.load(getClass().getResource("smsFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



         
    } 
}
    

   
