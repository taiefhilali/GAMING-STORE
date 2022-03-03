/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayOutputStream;
import javafx.stage.FileChooser;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import utils.MaConnexion;
import models.Post;
import services.Servicepost;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 *  Parent root = FXMLLoader.load(getClass().getResource("ADD_PostFXML.fxml"));
 *  Parent root = FXMLLoader.load(getClass().getResource("ADD_PostFXML.fxml"));
 * @author msi
 */

public class Show_PostFXMLController implements Initializable {

    @FXML
     ListView<Post> PostLV;
   // ObservableList<Post>pst=FXCollections.observableArrayList();
    public static String id;

    /**
     * Initializes the controller class.
     */
    Servicepost spost=new Servicepost();
    ObservableList<Post>list= FXCollections.observableArrayList();
    @FXML
    private Label recherche;
    @FXML
    private TextField search;
    @FXML
    private Button edit;
private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label img;
   
    private ImageIcon format = null;
    String filename = null;
    byte[] person_image = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //PostLV.setOnMouseCl!icked();
      //PostLV.setItems();
      PostLV.getItems().addAll(spost.afficherPost());
     PostLV.setOnMouseClicked(e->{spost.afficherPost();});
     
     
     edit.setOnAction( 
                        event -> {
                            try {

                                switchToEditPage(event,PostLV.getSelectionModel().getSelectedItem());

                            } catch (Exception ex) {
                               
                            }
                        }
                );
              
            }
        
     
     ///////////recherche dynamique//////////////
     
     // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<Post> filteredData = new FilteredList<>(list, b -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
//		search.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(Cours -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (Cours.getContent().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; // Filter matches first name.
//				} 
//                                else if (Cours.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; // Filter matches first name.
//                              
//				} 
//                                else if (String.valueOf(Cours.getId()).indexOf(lowerCaseFilter)!=-1)
//				     return true;
//                                else  
//				    	 return false; // Does not match.
//			});
//		});
//		
		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Post> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		//sortedData.bind(PostLV.accessibleTextProperty());
		
		// 5. Add sorted (and filtered) data to the table.
//		PostLV.setItems(sortedData);
     
     
     
      
      
    

 public void delete()
    { 
       // Post p =new Post(ge, title, content, datep, id_user)
         spost.supprimerPost(PostLV.getSelectionModel().getSelectedItem());
        
             System.out.println(PostLV.getSelectionModel().getSelectedItem().getId());

}
 
    @FXML
    private void supprimer_post(ActionEvent event) {
        
        //----------------------------- Bouton supprimer -----------------------------------//
    
   try{
        delete();
        PostLV.getItems().removeAll(PostLV.getSelectionModel().getSelectedItem());
        //System.out.println(PostLV);
        PostLV.refresh();
        
          Alert alert =new Alert(AlertType.INFORMATION);//hethika l combo ? lee
        alert.setTitle("SUPPRIMER PUBLICATION!");
        alert.setHeaderText("information!");
        alert.setContentText("PUBLICATION A ETE SUPPRIMEE!");
        alert.showAndWait();
        } catch (Exception ex) {
            System.out.println(ex);
        }
                
    }
public void switchToEditPage(ActionEvent event, Post p) throws IOException {
        switchPage(event, "./UPDATE_PostFXML.fxml", p);

    }
        public void switchPage(ActionEvent event, String path, Post p) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
         
        stage.setUserData(p);
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      

//    @FXML
//    private void modifbutt(ActionEvent event) {
//      
//    }

//    @FXML
//    private void UploadImageActionPerformed(ActionEvent event) {
//         JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//
//        filename = f.getAbsolutePath();
//         ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(img.getWidth(), img.getHeight(),SCALE_DEFAULT));
//
//         img.setGraphic(search);       
//
//
//
//         try {
//            File image = new File(filename);
//            FileInputStream fis = new FileInputStream (image);
//            ByteArrayOutputStream bos= new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//            for(int readNum; (readNum=fis.read(buf))!=-1; ){
//                bos.write(buf,0,readNum);
//            }
//            person_image=bos.toByteArray();
//        }
//
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null,e);
//        }
//
//    }

    @FXML
    private void modifbutt(ActionEvent event) {
    }

    @FXML
    private void UploadImageActionPerformed(ActionEvent event) {
    }
   
    }
    
    

