/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.Comment;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ItemcommentController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
     private Comment comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    public void setData(Comment comment) {
        this.comment = comment;
        nameLabel.setText(comment.getContenu());
        priceLable.setText(comment.getLabel());
      
    }
    @FXML
    private void click(MouseEvent event) {
    }
    


}
