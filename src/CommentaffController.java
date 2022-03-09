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
import models.Comment;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class CommentaffController implements Initializable {

    @FXML
    private Label contenu;
    @FXML
    private Label resp;
    @FXML
    private Label label;
    @FXML
    private Label hearts;
    @FXML
    private Label pov;
    @FXML
    private Label content;
    private Comment comment;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    
     void setData(Comment comment) {
        this.comment = comment;
        label.setText(comment.getLabel());
        contenu.setText(comment.getContenu());
        resp.setText(String.valueOf(comment.getResp()));
      
}}
