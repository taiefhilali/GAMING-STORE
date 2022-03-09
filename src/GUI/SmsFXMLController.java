/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceFacture;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SmsFXMLController implements Initializable {

    @FXML
    private TextArea txtmess;
    @FXML
    private TextField txtapi;
    @FXML
    private TextField txtsender;
    @FXML
    private TextField txtnumber;
 private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView ss;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
      
        }

    @FXML
    private void send(ActionEvent event) {
         try {
			// Construct data
			String apiKey = "apikey=" + txtapi.getText();
			String message = "&message=" + txtmess.getText();
			String sender = "&sender=" + txtsender.getText();
			String numbers = "&numbers=" + txtnumber.getText();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
                                JOptionPane.showConfirmDialog( null,"message"+line);
			}
			rd.close();
			
		
		} catch (Exception e) {
			
                         JOptionPane.showConfirmDialog( null,e);
		}
    }

    @FXML
    private void capture(ActionEvent event) {
        try {
             Robot robot=new Robot();
             Rectangle rectangle=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
             BufferedImage image=robot.createScreenCapture(rectangle);
             WritableImage myimage=SwingFXUtils.toFXImage(image, null);
             ss.setImage(myimage);
         } catch (AWTException ex) {
             Logger.getLogger(DocFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         }
   }

    @FXML
    private void retour(ActionEvent event)throws IOException {
        root = FXMLLoader.load(getClass().getResource("FacFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        
    }    
    

