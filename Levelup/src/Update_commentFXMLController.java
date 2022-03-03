/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Comment;
import models.Post;
import services.Servicecomment;
import services.Servicepost;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Update_commentFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      Servicecomment  scomment=new Servicecomment();        
         List<Comment> comments= scomment.afficherComment();

        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("UPDATE_PostFXML.fxml"));
          Stage prStage = new Stage();
            Parent root;
      
          
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }}                
                             
             
