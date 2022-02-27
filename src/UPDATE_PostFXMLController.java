/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class UPDATE_PostFXMLController implements Initializable {

    @FXML
    private TextField idTF;
    @FXML
    private TextField contenuTF;
    @FXML
    private TextField titreTF;
    @FXML
    private ComboBox<?> userTF;
    @FXML
    private DatePicker datePK;
    @FXML
    private Button btnupdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterpost(ActionEvent event) {
    }
    
}
