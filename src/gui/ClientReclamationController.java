/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Livraison;
import models.Reclamation;
import models.User;
import services.ServiceLivraison;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ClientReclamationController implements Initializable {

    @FXML
    private TextField idReclamation;
    @FXML
    private TextField idUser;
    @FXML
    private ComboBox<Object> idLivraison;
    @FXML
    private TextArea Description;
    @FXML
    private Button Enregistrer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceLivraison sl = new ServiceLivraison();

   List<Livraison> livFromdatabase= sl.afficherLivraison();
  ObservableList<Object> listLivData = FXCollections.observableArrayList();
        listLivData.addAll(livFromdatabase);
idLivraison.getItems().addAll(listLivData);

    }    

    @FXML
    private void Enregistrer(ActionEvent event) {
     //   int id_reclamation, User user, Livraison livraison, String description
                User u = new User();
                u.setId(Integer.valueOf(idUser.getText()));
                Livraison LivSelected = (Livraison) idLivraison.getSelectionModel().getSelectedItem();

        Reclamation r = new Reclamation(Integer.valueOf(idReclamation.getText()),u,LivSelected,Description.getText());
        ServiceReclamation rs = new ServiceReclamation();
        rs.ajouterReclamation(r);
        idReclamation.setText("");
        Description.setText("");
        
    }
    
}
