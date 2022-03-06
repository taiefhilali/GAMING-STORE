/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import static Show_PostFXMLController.id;
import java.io.IOException;
import java.net.URL;

//import java.sql.Date;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Post;
import models.User;
import services.Servicepost;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class UPDATE_PostFXMLController implements Initializable {

    private TextField idTF;
    @FXML
    private TextField contenuTF;
    @FXML
    private TextField titreTF;
    private TextField userTF;
    @FXML
    private TableView<Post> tablepost;
    @FXML
    private TableColumn<Post, String> CONT;
    @FXML
    private TableColumn<Post, String> TITLE;
    @FXML
    private TableColumn<Post, Date> DATEP;
    @FXML
    private TableColumn<Post, User> USR;

    @FXML
    private Label recherche;
    @FXML
    private TextField search;

    @FXML
    private TextField occc;
    @FXML
    private TextField entocc;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button exit;
    @FXML
    private Label times;
    private Post post;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button cmnt;

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
        Timenow();
        Servicepost spost = new Servicepost();
        List<Post> posts = spost.afficherPost();

//        try
//        {
//          FXMLLoader loader = new FXMLLoader();
//          loader.setLocation(getClass().getResource("Show_PostFXML.fxml"));
//          Stage prStage = new Stage();
//            Parent root;
////        try {
////            root = loader.load();
////            Scene scene = new Scene(root);
////            prStage.setScene(scene);
////           Show_PostFXMLController ac = loader.getController();
////
//////           int id = Integer.parseInt(Show_PostFXMLController.id);
////            System.out.println(id);
////            //   Date myDate = Date.valueOf(datePK.getValue().toString());
////          contenuTF.setText(spost.getContentbyID(id));
////          titreTF.setText(spost.gettitlebyID(id));
////         // userTF.setText(spost.getuserbyID(id).getEmail());
////         //datePK.setValue(spost.getdatebyID(id).toLocalDate());
////        } catch (IOException ex) {
////        }
////            
//          
//        } catch (Exception ex) {
//           ex.printStackTrace();
//        }
//        //////////// recherche avec table view //////////////
//        
//        
//        
//        
//        tablepost.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//       
//               contenuTF.setText(String.valueOf(spost.show().get(tablepost.getSelectionModel().getSelectedIndex()).getContent()
//               ));
//                            
//              // idTF.setText(String.valueOf(spost.show().get(tablepost.getSelectionModel().getSelectedIndex()).getId()));
//               titreTF.setText(spost.show().get(tablepost.getSelectionModel().getSelectedIndex()).getTitle());
//               DATEP.setText(String.valueOf(spost.show().get(tablepost.getSelectionModel().getSelectedIndex()).getDatep().toString() ));
//               userTF.setText(String.valueOf(spost.show().get(tablepost.getSelectionModel().getSelectedIndex()).getId_user().getEmail()));
//          
//
//              
//               
//            };
//        });
//        
        ObservableList<Post> list = null;
        try {
            list = spost.getCoursList();
            /// ID.setCellValueFactory(new PropertyValueFactory<>("id"));
            CONT.setCellValueFactory(new PropertyValueFactory<>("content"));
            TITLE.setCellValueFactory(new PropertyValueFactory<>("title"));
            DATEP.setCellValueFactory(new PropertyValueFactory<>("datep"));
            USR.setCellValueFactory(new PropertyValueFactory<>("id_user"));

            //                    Total.setText(bs.prixtotal().toString) Integer.valueOf(var1
            // HEDHI KENT MNAHYA    Total.setText(bs.prixtotal().toString());
            //setText(as.getPrixbyID(id).toString());
            //Coaching co= new Coaching();
            // nom_occ.setText(k);
            for (Post post : posts) {
                tablepost.getItems().add(new Post(post.getId(), post.getTitle(), post.getContent(), post.getDatep(), post.getId_user()));
            }

            //tablepost.setItems(list);
            tablepost.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() > 1) {
                    onEdit();
                }

            });

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        //////////////recherche//////////::
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Post> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Cours -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Cours.getContent().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                    //} 
                    // else if (Cours.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    //	return true; // Filter matches first name.

                } // else if (String.valueOf(Cours.getDatep()).indexOf(lowerCaseFilter)!=-1)
                //  return true;
                else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Post> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablepost.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablepost.setItems(sortedData);
    }
    //afficher table view        //afficher table view

    public void onEdit() {
        // check the table's selected item and get selected item
        if (tablepost.getSelectionModel().getSelectedItem() != null) {
            Post selectedPost = tablepost.getSelectionModel().getSelectedItem();
            contenuTF.setText(selectedPost.getContent());
            titreTF.setText(selectedPost.getTitle());
            update.setOnAction(action -> {
                Servicepost spost = new Servicepost();
                selectedPost.setContent(contenuTF.getText());
                selectedPost.setTitle(titreTF.getText());
                //post.setId_user(userTF.getText().);

                //post.setDatep(DATEP);
                spost.modifierPost(selectedPost);
                tablepost.refresh();
                System.out.println("ppppppppppppp" + selectedPost);
                showAlert(Alert.AlertType.INFORMATION, ((Node) action.getSource()).getScene().getWindow(),
                        "succès!", "modification post avec succès");
            });
            delete.setOnAction(g -> {
                Servicepost spost = new Servicepost();
                spost.supprimerPost(tablepost.getSelectionModel().getSelectedItem());
                try {
                    root = FXMLLoader.load(getClass().getResource("UPDATE_PostFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(UPDATE_PostFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage = (Stage) ((Node) g.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                tablepost.refresh();
                showAlert(Alert.AlertType.INFORMATION, ((Node) g.getSource()).getScene().getWindow(),
                        "succès!", "suppression de publication avec succès");
                tablepost.getItems().removeAll(tablepost.getSelectionModel().getSelectedItem());

            });

        }
    }

    private void modifierpost(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UPDATE_PostFXML.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Activite.fxml"));
        Stage prStage = new Stage();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);

        Show_PostFXMLController ac = loader.getController();
        Servicepost as = new Servicepost();
        Post p = new Post();

        int id = Integer.parseInt(Show_PostFXMLController.id);
        System.out.println(id);
        p.setContent(contenuTF.getText());
        p.setTitle(titreTF.getText());
        //  p.setDatep(Date.valueOf(datePK.getValue()));
        p.setId_user(new User(userTF.getId()));
        as.modifier(p, id);
        //as.modifier(p,id);
        //as.updateActivite(Integer.valueOf(mod_ida.getText()),mod_nom.getText(),Integer.valueOf(mod_cat.getText()),mod_type.getText(),Integer.valueOf(mod_ide.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText("!!! Modification effectuer avec suucces !!!");
        alert.setContentText("succes");
        alert.showAndWait();

    }

    private void afficherpost(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Show_PostFXML.fxml"));
        //Afficher_ActiviteController ac = loader.getController();
        //Afficher_ActiviteController aac = new Afficher_ActiviteController();
        //aac.tab_act.refresh();
        try {
            Parent root = loader.load();
            Show_PostFXMLController ac = loader.getController();
            idTF.getScene().setRoot(root);
            ac.PostLV.refresh();

        } catch (IOException ex) {
            System.out.println("ggggggg");
        }

    }

    private volatile boolean stop = false;

    private void Timenow() {
        Thread thread = new Thread(() -> {

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    times.setText(timenow);
                });
            }
        }
        );
        thread.start();

    }

    @FXML
    private void entocc(KeyEvent event) {
        Servicepost spost = new Servicepost();
        String k = null;
        if (event.getCode().equals(KeyCode.SPACE)) {
            k = (entocc.getText());
            occc.setText(spost.calculer_nbp(k));
        }
    }

    @FXML
    private void exit(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("do you want to logout ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("you successfully logged out ");
            stop = true;
            stage.close();
        }

    }

//    @FXML
//    private void supppost(ActionEvent event) {
//         try{
//        Servicepost spost= new Servicepost();
//         spost.supprimerPost(tablepost.getSelectionModel().getSelectedItem());
//         System.out.println(tablepost.getSelectionModel().getSelectedItem().getId());
//        tablepost.getItems().remove(tablepost.getSelectionModel().getSelectedItem());
//       // tablepost.getItems().remove(tablepost.getSelectionModel().getSelectedItem());
//      // tablepost.getItems().remove(tablepost.getSelectionModel().getSelectedItem());
//        System.out.println("ddddd");
//       // tablepost.refresh();
//        
//          Alert alert =new Alert(Alert.AlertType.INFORMATION);//hethika l combo ? lee
//        alert.setTitle("SUPPRIMER PUBLICATION!");
//        alert.setHeaderText("information!");
//        alert.setContentText("PUBLICATION A ETE SUPPRIMEE!");
//        alert.showAndWait();
//        } catch (Exception ex) {
//            System.out.println("dddd");
//        }
//          
//         
//    }
// 
    @FXML
    private void receiveData(MouseEvent event) {
        int countEvents = 0;
        if (countEvents == 0) { // Step 1
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // Step 2
            Post p = (Post) stage.getUserData();
            post = p;
            System.out.println(node.getUserData());

            // Step 3
            contenuTF.setText(p.getContent());
            titreTF.setText(p.getTitle());
            // userTF.setText(p.getId_user().getEmail());
            DATEP.setText(p.getDatep().toString());

            countEvents++;
        }
    }

    @FXML
    private void commenter(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Show_CommentFXML.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }

}
