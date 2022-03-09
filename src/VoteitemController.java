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
import models.Post;
import models.Vote;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class VoteitemController implements Initializable {

    @FXML
    private Label vottype;
private Vote vote;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
    void setData(Vote vote) {
        this.vote = vote;
        vottype.setText(String.valueOf(vote.getVote_type()));
        
    }
}
