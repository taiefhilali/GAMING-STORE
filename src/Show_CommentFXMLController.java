/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Window;

import javafx.stage.Stage;
import models.Post;
import models.User;
import models.Comment;
import services.Servicecomment;
import services.Servicepost;
import styles.ToggleSwitch;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Show_CommentFXMLController implements Initializable {

    private Stage stage,stage1;
    private Scene scene,scene1;
    private Parent root,root1;

    @FXML
    private TableView<Comment> tablecomm;
    @FXML
    private TableColumn<Comment, String> LABEL;
    @FXML
    private TableColumn<Comment, String> COMMENT;
    @FXML
    private TableColumn<Comment, Integer> HEARTS;
    @FXML
    private TextField labTF;
    @FXML
    private TextField commTF;
    // @FXML
    //private TreeTableColumn<?, ?> idd;
    @FXML
    private TableColumn<Comment, Integer> idd;
    @FXML
    private Button update;
    @FXML
    private Button supp;

    private Button add;
    @FXML
    private TextField topp;
    private Button retu;
    @FXML
    private Button retour;

   Stage prevStage;
    @FXML
    private Button supp1;
 private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
// TODO
        Servicecomment scomment = new Servicecomment();
        List<Comment> comments = scomment.afficherComment();

//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("show_CommentFXML.fxml"));
//            Stage Stage = new Stage();
//            Parent root;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        ObservableList<Comment> list = null;

        try {
            list = scomment.getCmList();
            /// ID.setCellValueFactory(new PropertyValueFactory<>("id"));
            COMMENT.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            LABEL.setCellValueFactory(new PropertyValueFactory<>("label"));
            HEARTS.setCellValueFactory(new PropertyValueFactory<>("resp"));
            idd.setCellValueFactory(new PropertyValueFactory<>("id"));

            //                    Total.setText(bs.prixtotal().toString) Integer.valueOf(var1
            // HEDHI KENT MNAHYA    Total.setText(bs.prixtotal().toString());
            //setText(as.getPrixbyID(id).toString());
            //Coaching co= new Coaching();
            // nom_occ.setText(k);
            for (Comment comment : comments) {
                tablecomm.getItems().add(new Comment(comment.getIdc(), comment.getLabel(), comment.getContenu(), comment.getResp(), comment.getId_post()));
            }

            tablecomm.setItems(list);
            tablecomm.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() > 1) {
                    onEdit();
                }
            });

        } catch (Exception ex) {
        }
    }

// showAlert(Alert.AlertType.INFORMATION, ((Node) gg.getSource()).getScene().getWindow(),
//                                           "return vers les publications");
    public void onEdit() {
        // check the table's selected item and get selected item
        if (tablecomm.getSelectionModel().getSelectedItem() != null) {
            Comment selectedPost = tablecomm.getSelectionModel().getSelectedItem();
            labTF.setText(selectedPost.getLabel());
            commTF.setText(selectedPost.getContenu());
           
            update.setOnAction(action -> {
                
                
//                Servicecomment scomment = new Servicecomment();
//                selectedPost.setContenu(commTF.getText());
//                selectedPost.setLabel(labTF.getText());
//                //post.setId_user(userTF.getText().);
//
//                //post.setDatep(DATEP);
//                scomment.modifierComment(selectedPost);
//                tablecomm.refresh();
////System.out.println("ppppppppppppp"+selectedPost);
//                showAlert(Alert.AlertType.INFORMATION, ((Node) action.getSource()).getScene().getWindow(),
//                        "succès!", "modification post avec succès");
            });
            supp.setOnAction(g -> {
                Servicecomment scomment = new Servicecomment();
                scomment.supprimerComment(tablecomm.getSelectionModel().getSelectedItem());
                try {
                    root = FXMLLoader.load(getClass().getResource("Show_CommentFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(UPDATE_PostFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

                tablecomm.refresh();
                showAlert(Alert.AlertType.INFORMATION, ((Node) g.getSource()).getScene().getWindow(),
                        "succès!", "suppression d'un commentaire avec succès");
                tablecomm.getItems().removeAll(tablecomm.getSelectionModel().getSelectedItem());

            });
            add.setOnAction(gg -> {
                Servicecomment scomment = new Servicecomment();
                try {
                    root = FXMLLoader.load(getClass().getResource("Show_CommentFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(UPDATE_PostFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                showAlert(Alert.AlertType.INFORMATION, ((Node) gg.getSource()).getScene().getWindow(),
                        "succès!", "suppression d'un commentaire avec succès");

            });
//            retour.setOnAction(k-> {
//                Servicecomment scomment = new Servicecomment();
//                try {
//                    root = FXMLLoader.load(getClass().getResource("ADD_CommentFXML.fxml"));
//                } catch (IOException ex) {
//                   
//                }
//                showAlert(Alert.AlertType.INFORMATION, ((Node) k.getSource()).getScene().getWindow(),
//                        "succès!", "AJOUTER un nouveau commentaire ");
//
//            });

        }
    }
//////retooouur////

    
    
    
    
    
    @FXML
    private void top(KeyEvent event) {
           Servicecomment scomment= new Servicecomment();
                  if (event.getCode().equals(KeyCode.SPACE)){
                      topp.setText(scomment.bestpost(21).toString());}  
        
        
    }
//  public void setPrevStage(Stage stage){
//         this.prevStage = stage;
//    }

    @FXML
    private void ret(ActionEvent event) throws IOException {
try {
            root = FXMLLoader.load(getClass().getResource("ADD_CommentFXML.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }     
    }

    @FXML
    private void Stat(ActionEvent event) {
      try {
            root = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

       
    }
}
    
    
    
  
    


