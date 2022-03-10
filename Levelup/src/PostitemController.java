/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import models.Comment;
import models.Post;
import org.controlsfx.control.Notifications;
import services.ServiceVote;
import services.Servicecomment;
import services.Servicepost;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class PostitemController implements Initializable {

    @FXML
    private Label cnt;
    @FXML
    private Label tit;
    @FXML
    private Label dat;
    private Parent root;
    private javafx.stage.Stage stage;
    private Scene scene;
    private Button upd;
    @FXML
    private Button upda;
    @FXML
    private RadioButton like;
    @FXML
    private RadioButton dislike;

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
    }
    private Post post;

    void setData(Post post) {
        this.post = post;
        tit.setText(post.getTitle());
        cnt.setText(post.getContent());
        dat.setText(String.valueOf(post.getDatep()));
         upda.setOnAction(
                 
                event -> {
            try {
                System.out.println("jjjjjjjjj");
                switchToEditPage(event, post);
            } catch (IOException ex) {
            }
                }
        );
         like.setOnAction(lk -> {
                ServiceVote svote = new ServiceVote();
                svote.upVote(post.getId(),post.getId_user().getId());
//                try {
//                    root = FXMLLoader.load(getClass().getResource("postgridFXML.fxml"));
//                   // like.isSelected();
//                } catch (IOException ex) {
////                }
//                stage = (javafx.stage.Stage) ((Node) lk.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
                //tablepost.refresh();
//                showAlert(Alert.AlertType.INFORMATION, ((Node) lk.getSource()).getScene().getWindow(),
//                        "upvote pressed!", "Post liked ");
    Notifications notificationBuilder = Notifications.create()
                .title("Post upvoted").text("vote_type==1").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

    

            });
            dislike.setOnAction(dlk -> {
                ServiceVote svote = new ServiceVote();
                svote.downVote(post.getId(),post.getId_user().getId());
//                try {
//                    root = FXMLLoader.load(getClass().getResource("postgridFXML.fxml"));
//                } catch (IOException ex) {
//                }
//                stage = (javafx.stage.Stage) ((Node) dlk.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
               // tablepost.refresh();
//                showAlert(Alert.AlertType.INFORMATION, ((Node) dlk.getSource()).getScene().getWindow(),
//                        "downvote pressed!", "Post disliked");
Notifications notificationBuilder = Notifications.create()
                .title("Post downvoted").text("vote_type==2").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

    

            });
    }

    private void delete(ActionEvent event) {
        

    }

    private void votee(ActionEvent event) {

    }

    public void switchToEditPage(ActionEvent event, Post post) throws IOException {
        switchPage(event, "./modifpost.fxml", post);

    }

    public void switchPage(ActionEvent event, String path, Post post) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        javafx.stage.Stage stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setUserData(post);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void updatep(ActionEvent event) {
    }

    @FXML
    private void deletep(ActionEvent ss) {
        Servicepost spost = new Servicepost();
        //Servicecomment scomment = new Servicecomment();
        spost.supprimerPost(post);

        System.out.println(spost.supprimerPost(post));
        try {
            root = FXMLLoader.load(getClass().getResource("postgrid.fxml"));
            stage = (javafx.stage.Stage) ((Node) ss.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
           
        }

    }

    @FXML
    private void rating(ActionEvent k) {
       
        try {
            root = FXMLLoader.load(getClass().getResource("rating.fxml"));
            stage = (javafx.stage.Stage) ((Node) k.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    private void delete(MouseEvent event) {
        Servicepost spost = new Servicepost();
        //Servicecomment scomment = new Servicecomment();
        
        spost.supprimerPost(post);
       
        try {
            root = FXMLLoader.load(getClass().getResource("postgrid.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        
        }
    }

    @FXML
    private void YAAAS(MouseEvent event) {
       
    }

    @FXML
    private void commenyer(ActionEvent event) {
         try {
            root = FXMLLoader.load(getClass().getResource("ADD_CommentFXML.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }

}
