/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import utils.MaConnexion;
import models.Post;
import services.Servicepost;

/**
 * FXML Controller class
 *
 *  Parent root = FXMLLoader.load(getClass().getResource("ADD_PostFXML.fxml"));
 *  Parent root = FXMLLoader.load(getClass().getResource("ADD_PostFXML.fxml"));
 * @author msi
 */

public class Show_PostFXMLController implements Initializable {

    @FXML
    private ListView<Post> PostLV;
    @FXML
    private ComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    Servicepost spost=new Servicepost();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // PostLV.setOnMouseCl!icked();
      //PostLV.setItems();
      
     PostLV.setOnMouseClicked(e->{spost.afficherPost();});
     
      
      
    
}}
