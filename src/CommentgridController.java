/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import interfaces.Icomment;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Comment;
import services.Servicecomment;
import interfaces.*;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class CommentgridController implements Initializable {
  @FXML
    private GridPane grid1;
    List<Comment> comments = new ArrayList();
    @FXML
    private AnchorPane anchor;
    @FXML
    private ScrollPane scrollpane;
    
  
    
//         private List<Comment> getData() {
//        List<Comment> offres = new ArrayList();
//        Comment comment;
//
//        for (int i = 0; i < 10; i++) {
//            comment = new Comment();
//            comment.setContenu("fffff");
//            comment.setLabel("fffff");
//            comment.setResp(44);
//            
//        }
//        return offres;
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Icomment cmnt;
      cmnt = new Servicecomment();
         comments= cmnt.afficherComment();
          
         
        int col = 0;
        int row = 0;
        try {
            for (int i = 0; i < comments.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxmlc.fxml"));

                AnchorPane anchorpane = fxmlLoader.load();

               FxmlcController itemController = fxmlLoader.getController();
                itemController.setData(comments.get(i));

                if (col == 1) {
                    col = 0;
                    row++;
                }

                grid1.add(anchorpane, col++, row);
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);

                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    }
    
    

