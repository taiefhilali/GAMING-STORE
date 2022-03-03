/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Livraison;
import models.Reclamation;
import models.User;
import services.ServiceLivraison;
import services.ServiceLivreur;
import services.ServiceReclamation;
import services.ServiceUser;
import utils.JavamailUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class GestionReclmationController implements Initializable {

    @FXML
    private TableView<Object> tableReclamation;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private Button Retour;
    @FXML
    private Button Supprimer;
    @FXML
    private Button warn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Supprimer.setDisable(true);
        warn.setDisable(true);
        
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> livRec= sr.afficherReclamation();
        ObservableList<Object> ListRecData = FXCollections.observableArrayList();
        ListRecData.addAll(livRec);
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));

tableReclamation.setItems(ListRecData);
ObservableList selectedCells = tableReclamation.getSelectionModel().getSelectedCells();
  
  selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
               Reclamation ReclamationSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
                if(ReclamationSelected!=null){
                    Supprimer.setDisable(false);
                    if(ReclamationSelected.isWarn()){
                                        warn.setDisable(true);

                    }else{
                    warn.setDisable(false);}
                }else{ Supprimer.setDisable(true);
                    warn.setDisable(true);

                }
            }      
        });

        
       
    }    

    @FXML
    private void Retour(ActionEvent event) {
        try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("livadmin.fxml"));
           Parent root= loader.load();
            Retour.getScene().setRoot(root);
            } catch (IOException ex) {
            Logger.getLogger(LivadminController.class.getName()).log(Level.SEVERE, null, ex);
     
    }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
                       Reclamation ReclamationSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
        ServiceReclamation sr = new ServiceReclamation();
sr.supprimerReclamation(ReclamationSelected);
miseajour();
    }

    @FXML
    private void warnLiv(ActionEvent event) {
                               Reclamation ReclamationSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
        ServiceReclamation sr = new ServiceReclamation();
        sr.warn(ReclamationSelected);
ServiceLivraison SL= new ServiceLivraison();
        Optional<Livraison> L = SL.afficherLivraison().stream().filter(l->l.getId_livraison()==ReclamationSelected.getLivraison().getId_livraison()).findFirst();
      System.out.print(L.get().getUser());
      ServiceUser us = new ServiceUser();
      User UserToSent = us.afficherPersonnes().stream().filter(e->e.getId()==L.get().getUser().getId()).findFirst().get();

        try {
            JavamailUtil.sendMailaide(UserToSent.getEmail(), "WARNING", "SERVICE RECLAMATION",ReclamationSelected.getDescription() );
        } catch (Exception ex) {
            Logger.getLogger(GestionReclmationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    void miseajour(){
     ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> livRec= sr.afficherReclamation();
        ObservableList<Object> ListRecData = FXCollections.observableArrayList();
        ListRecData.addAll(livRec);
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));

tableReclamation.setItems(ListRecData);}
    
}
